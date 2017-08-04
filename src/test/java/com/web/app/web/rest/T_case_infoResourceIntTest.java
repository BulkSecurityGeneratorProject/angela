package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_case_info;
import com.web.app.repository.T_case_infoRepository;
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
 * Test class for the T_case_infoResource REST controller.
 *
 * @see T_case_infoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_case_infoResourceIntTest {

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_BRIEF = "AAAAAAAAAA";
    private static final String UPDATED_BRIEF = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IS_USEFUL = 1;
    private static final Integer UPDATED_IS_USEFUL = 2;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_DELETE = 1;
    private static final Integer UPDATED_IS_DELETE = 2;

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    @Autowired
    private T_case_infoRepository t_case_infoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_case_infoMockMvc;

    private T_case_info t_case_info;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_case_infoResource t_case_infoResource = new T_case_infoResource(t_case_infoRepository);
        this.restT_case_infoMockMvc = MockMvcBuilders.standaloneSetup(t_case_infoResource)
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
    public static T_case_info createEntity(EntityManager em) {
        T_case_info t_case_info = new T_case_info()
            .title(DEFAULT_TITLE)
            .brief(DEFAULT_BRIEF)
            .description(DEFAULT_DESCRIPTION)
            .createdate(DEFAULT_CREATEDATE)
            .isUseful(DEFAULT_IS_USEFUL)
            .remarks(DEFAULT_REMARKS)
            .isDelete(DEFAULT_IS_DELETE)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER);
        return t_case_info;
    }

    @Before
    public void initTest() {
        t_case_info = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_case_info() throws Exception {
        int databaseSizeBeforeCreate = t_case_infoRepository.findAll().size();

        // Create the T_case_info
        restT_case_infoMockMvc.perform(post("/api/t-case-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_case_info)))
            .andExpect(status().isCreated());

        // Validate the T_case_info in the database
        List<T_case_info> t_case_infoList = t_case_infoRepository.findAll();
        assertThat(t_case_infoList).hasSize(databaseSizeBeforeCreate + 1);
        T_case_info testT_case_info = t_case_infoList.get(t_case_infoList.size() - 1);
        assertThat(testT_case_info.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testT_case_info.getBrief()).isEqualTo(DEFAULT_BRIEF);
        assertThat(testT_case_info.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testT_case_info.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testT_case_info.getIsUseful()).isEqualTo(DEFAULT_IS_USEFUL);
        assertThat(testT_case_info.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testT_case_info.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
        assertThat(testT_case_info.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testT_case_info.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    public void createT_case_infoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_case_infoRepository.findAll().size();

        // Create the T_case_info with an existing ID
        t_case_info.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_case_infoMockMvc.perform(post("/api/t-case-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_case_info)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_case_info> t_case_infoList = t_case_infoRepository.findAll();
        assertThat(t_case_infoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_case_infos() throws Exception {
        // Initialize the database
        t_case_infoRepository.saveAndFlush(t_case_info);

        // Get all the t_case_infoList
        restT_case_infoMockMvc.perform(get("/api/t-case-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_case_info.getId().intValue())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].brief").value(hasItem(DEFAULT_BRIEF.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].isUseful").value(hasItem(DEFAULT_IS_USEFUL)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.toString())));
    }

    @Test
    @Transactional
    public void getT_case_info() throws Exception {
        // Initialize the database
        t_case_infoRepository.saveAndFlush(t_case_info);

        // Get the t_case_info
        restT_case_infoMockMvc.perform(get("/api/t-case-infos/{id}", t_case_info.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_case_info.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.brief").value(DEFAULT_BRIEF.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.isUseful").value(DEFAULT_IS_USEFUL))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingT_case_info() throws Exception {
        // Get the t_case_info
        restT_case_infoMockMvc.perform(get("/api/t-case-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_case_info() throws Exception {
        // Initialize the database
        t_case_infoRepository.saveAndFlush(t_case_info);
        int databaseSizeBeforeUpdate = t_case_infoRepository.findAll().size();

        // Update the t_case_info
        T_case_info updatedT_case_info = t_case_infoRepository.findOne(t_case_info.getId());
        updatedT_case_info
            .title(UPDATED_TITLE)
            .brief(UPDATED_BRIEF)
            .description(UPDATED_DESCRIPTION)
            .createdate(UPDATED_CREATEDATE)
            .isUseful(UPDATED_IS_USEFUL)
            .remarks(UPDATED_REMARKS)
            .isDelete(UPDATED_IS_DELETE)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER);

        restT_case_infoMockMvc.perform(put("/api/t-case-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_case_info)))
            .andExpect(status().isOk());

        // Validate the T_case_info in the database
        List<T_case_info> t_case_infoList = t_case_infoRepository.findAll();
        assertThat(t_case_infoList).hasSize(databaseSizeBeforeUpdate);
        T_case_info testT_case_info = t_case_infoList.get(t_case_infoList.size() - 1);
        assertThat(testT_case_info.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testT_case_info.getBrief()).isEqualTo(UPDATED_BRIEF);
        assertThat(testT_case_info.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testT_case_info.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testT_case_info.getIsUseful()).isEqualTo(UPDATED_IS_USEFUL);
        assertThat(testT_case_info.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testT_case_info.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
        assertThat(testT_case_info.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testT_case_info.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingT_case_info() throws Exception {
        int databaseSizeBeforeUpdate = t_case_infoRepository.findAll().size();

        // Create the T_case_info

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_case_infoMockMvc.perform(put("/api/t-case-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_case_info)))
            .andExpect(status().isCreated());

        // Validate the T_case_info in the database
        List<T_case_info> t_case_infoList = t_case_infoRepository.findAll();
        assertThat(t_case_infoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_case_info() throws Exception {
        // Initialize the database
        t_case_infoRepository.saveAndFlush(t_case_info);
        int databaseSizeBeforeDelete = t_case_infoRepository.findAll().size();

        // Get the t_case_info
        restT_case_infoMockMvc.perform(delete("/api/t-case-infos/{id}", t_case_info.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_case_info> t_case_infoList = t_case_infoRepository.findAll();
        assertThat(t_case_infoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_case_info.class);
        T_case_info t_case_info1 = new T_case_info();
        t_case_info1.setId(1L);
        T_case_info t_case_info2 = new T_case_info();
        t_case_info2.setId(t_case_info1.getId());
        assertThat(t_case_info1).isEqualTo(t_case_info2);
        t_case_info2.setId(2L);
        assertThat(t_case_info1).isNotEqualTo(t_case_info2);
        t_case_info1.setId(null);
        assertThat(t_case_info1).isNotEqualTo(t_case_info2);
    }
}
