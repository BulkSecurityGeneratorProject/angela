package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_pictures;
import com.web.app.repository.T_picturesRepository;
import com.web.app.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.web.app.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the T_picturesResource REST controller.
 *
 * @see T_picturesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_picturesResourceIntTest {

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL = "BBBBBBBBBB";

    private static final String DEFAULT_IMAGE_URL_SMALL = "AAAAAAAAAA";
    private static final String UPDATED_IMAGE_URL_SMALL = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final Integer DEFAULT_IMAGE_TYPE = 1;
    private static final Integer UPDATED_IMAGE_TYPE = 2;

    private static final Integer DEFAULT_IS_DELETE = 1;
    private static final Integer UPDATED_IS_DELETE = 2;

    @Autowired
    private T_picturesRepository t_picturesRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_picturesMockMvc;

    private T_pictures t_pictures;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_picturesResource t_picturesResource = new T_picturesResource(t_picturesRepository);
        this.restT_picturesMockMvc = MockMvcBuilders.standaloneSetup(t_picturesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static T_pictures createEntity(EntityManager em) {
        T_pictures t_pictures = new T_pictures()
            .productId(DEFAULT_PRODUCT_ID)
            .imageUrl(DEFAULT_IMAGE_URL)
            .imageUrlSmall(DEFAULT_IMAGE_URL_SMALL)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER)
            .imageType(DEFAULT_IMAGE_TYPE)
            .isDelete(DEFAULT_IS_DELETE);
        return t_pictures;
    }

    @Before
    public void initTest() {
        t_pictures = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_pictures() throws Exception {
        int databaseSizeBeforeCreate = t_picturesRepository.findAll().size();

        // Create the T_pictures
        restT_picturesMockMvc.perform(post("/api/t-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_pictures)))
            .andExpect(status().isCreated());

        // Validate the T_pictures in the database
        List<T_pictures> t_picturesList = t_picturesRepository.findAll();
        assertThat(t_picturesList).hasSize(databaseSizeBeforeCreate + 1);
        T_pictures testT_pictures = t_picturesList.get(t_picturesList.size() - 1);
        assertThat(testT_pictures.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testT_pictures.getImageUrl()).isEqualTo(DEFAULT_IMAGE_URL);
        assertThat(testT_pictures.getImageUrlSmall()).isEqualTo(DEFAULT_IMAGE_URL_SMALL);
        assertThat(testT_pictures.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testT_pictures.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testT_pictures.getImageType()).isEqualTo(DEFAULT_IMAGE_TYPE);
        assertThat(testT_pictures.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
    }

    @Test
    @Transactional
    public void createT_picturesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_picturesRepository.findAll().size();

        // Create the T_pictures with an existing ID
        t_pictures.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_picturesMockMvc.perform(post("/api/t-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_pictures)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_pictures> t_picturesList = t_picturesRepository.findAll();
        assertThat(t_picturesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_pictures() throws Exception {
        // Initialize the database
        t_picturesRepository.saveAndFlush(t_pictures);

        // Get all the t_picturesList
        restT_picturesMockMvc.perform(get("/api/t-pictures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_pictures.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())))
            .andExpect(jsonPath("$.[*].imageUrl").value(hasItem(DEFAULT_IMAGE_URL.toString())))
            .andExpect(jsonPath("$.[*].imageUrlSmall").value(hasItem(DEFAULT_IMAGE_URL_SMALL.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.toString())))
            .andExpect(jsonPath("$.[*].imageType").value(hasItem(DEFAULT_IMAGE_TYPE)))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE)));
    }

    @Test
    @Transactional
    public void getT_pictures() throws Exception {
        // Initialize the database
        t_picturesRepository.saveAndFlush(t_pictures);

        // Get the t_pictures
        restT_picturesMockMvc.perform(get("/api/t-pictures/{id}", t_pictures.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_pictures.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()))
            .andExpect(jsonPath("$.imageUrl").value(DEFAULT_IMAGE_URL.toString()))
            .andExpect(jsonPath("$.imageUrlSmall").value(DEFAULT_IMAGE_URL_SMALL.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.toString()))
            .andExpect(jsonPath("$.imageType").value(DEFAULT_IMAGE_TYPE))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE));
    }

    @Test
    @Transactional
    public void getNonExistingT_pictures() throws Exception {
        // Get the t_pictures
        restT_picturesMockMvc.perform(get("/api/t-pictures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_pictures() throws Exception {
        // Initialize the database
        t_picturesRepository.saveAndFlush(t_pictures);
        int databaseSizeBeforeUpdate = t_picturesRepository.findAll().size();

        // Update the t_pictures
        T_pictures updatedT_pictures = t_picturesRepository.findOne(t_pictures.getId());
        updatedT_pictures
            .productId(UPDATED_PRODUCT_ID)
            .imageUrl(UPDATED_IMAGE_URL)
            .imageUrlSmall(UPDATED_IMAGE_URL_SMALL)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER)
            .imageType(UPDATED_IMAGE_TYPE)
            .isDelete(UPDATED_IS_DELETE);

        restT_picturesMockMvc.perform(put("/api/t-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_pictures)))
            .andExpect(status().isOk());

        // Validate the T_pictures in the database
        List<T_pictures> t_picturesList = t_picturesRepository.findAll();
        assertThat(t_picturesList).hasSize(databaseSizeBeforeUpdate);
        T_pictures testT_pictures = t_picturesList.get(t_picturesList.size() - 1);
        assertThat(testT_pictures.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testT_pictures.getImageUrl()).isEqualTo(UPDATED_IMAGE_URL);
        assertThat(testT_pictures.getImageUrlSmall()).isEqualTo(UPDATED_IMAGE_URL_SMALL);
        assertThat(testT_pictures.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testT_pictures.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testT_pictures.getImageType()).isEqualTo(UPDATED_IMAGE_TYPE);
        assertThat(testT_pictures.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
    }

    @Test
    @Transactional
    public void updateNonExistingT_pictures() throws Exception {
        int databaseSizeBeforeUpdate = t_picturesRepository.findAll().size();

        // Create the T_pictures

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_picturesMockMvc.perform(put("/api/t-pictures")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_pictures)))
            .andExpect(status().isCreated());

        // Validate the T_pictures in the database
        List<T_pictures> t_picturesList = t_picturesRepository.findAll();
        assertThat(t_picturesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_pictures() throws Exception {
        // Initialize the database
        t_picturesRepository.saveAndFlush(t_pictures);
        int databaseSizeBeforeDelete = t_picturesRepository.findAll().size();

        // Get the t_pictures
        restT_picturesMockMvc.perform(delete("/api/t-pictures/{id}", t_pictures.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_pictures> t_picturesList = t_picturesRepository.findAll();
        assertThat(t_picturesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_pictures.class);
        T_pictures t_pictures1 = new T_pictures();
        t_pictures1.setId(1L);
        T_pictures t_pictures2 = new T_pictures();
        t_pictures2.setId(t_pictures1.getId());
        assertThat(t_pictures1).isEqualTo(t_pictures2);
        t_pictures2.setId(2L);
        assertThat(t_pictures1).isNotEqualTo(t_pictures2);
        t_pictures1.setId(null);
        assertThat(t_pictures1).isNotEqualTo(t_pictures2);
    }
}
