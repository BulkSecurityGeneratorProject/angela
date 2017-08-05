package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_review;
import com.web.app.repository.T_reviewRepository;
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
 * Test class for the T_reviewResource REST controller.
 *
 * @see T_reviewResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_reviewResourceIntTest {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IS_USEFUL = 1;
    private static final Integer UPDATED_IS_USEFUL = 2;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_DELETE = 1;
    private static final Integer UPDATED_IS_DELETE = 2;

    @Autowired
    private T_reviewRepository t_reviewRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_reviewMockMvc;

    private T_review t_review;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_reviewResource t_reviewResource = new T_reviewResource(t_reviewRepository);
        this.restT_reviewMockMvc = MockMvcBuilders.standaloneSetup(t_reviewResource)
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
    public static T_review createEntity(EntityManager em) {
        T_review t_review = new T_review()
            .userId(DEFAULT_USER_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .content(DEFAULT_CONTENT)
            .createdate(DEFAULT_CREATEDATE)
            .isUseful(DEFAULT_IS_USEFUL)
            .remarks(DEFAULT_REMARKS)
            .isDelete(DEFAULT_IS_DELETE);
        return t_review;
    }

    @Before
    public void initTest() {
        t_review = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_review() throws Exception {
        int databaseSizeBeforeCreate = t_reviewRepository.findAll().size();

        // Create the T_review
        restT_reviewMockMvc.perform(post("/api/t-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_review)))
            .andExpect(status().isCreated());

        // Validate the T_review in the database
        List<T_review> t_reviewList = t_reviewRepository.findAll();
        assertThat(t_reviewList).hasSize(databaseSizeBeforeCreate + 1);
        T_review testT_review = t_reviewList.get(t_reviewList.size() - 1);
        assertThat(testT_review.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testT_review.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testT_review.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testT_review.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testT_review.getIsUseful()).isEqualTo(DEFAULT_IS_USEFUL);
        assertThat(testT_review.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testT_review.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
    }

    @Test
    @Transactional
    public void createT_reviewWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_reviewRepository.findAll().size();

        // Create the T_review with an existing ID
        t_review.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_reviewMockMvc.perform(post("/api/t-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_review)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_review> t_reviewList = t_reviewRepository.findAll();
        assertThat(t_reviewList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_reviews() throws Exception {
        // Initialize the database
        t_reviewRepository.saveAndFlush(t_review);

        // Get all the t_reviewList
        restT_reviewMockMvc.perform(get("/api/t-reviews?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_review.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.toString())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].isUseful").value(hasItem(DEFAULT_IS_USEFUL)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE)));
    }

    @Test
    @Transactional
    public void getT_review() throws Exception {
        // Initialize the database
        t_reviewRepository.saveAndFlush(t_review);

        // Get the t_review
        restT_reviewMockMvc.perform(get("/api/t-reviews/{id}", t_review.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_review.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.toString()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.isUseful").value(DEFAULT_IS_USEFUL))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE));
    }

    @Test
    @Transactional
    public void getNonExistingT_review() throws Exception {
        // Get the t_review
        restT_reviewMockMvc.perform(get("/api/t-reviews/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_review() throws Exception {
        // Initialize the database
        t_reviewRepository.saveAndFlush(t_review);
        int databaseSizeBeforeUpdate = t_reviewRepository.findAll().size();

        // Update the t_review
        T_review updatedT_review = t_reviewRepository.findOne(t_review.getId());
        updatedT_review
            .userId(UPDATED_USER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .content(UPDATED_CONTENT)
            .createdate(UPDATED_CREATEDATE)
            .isUseful(UPDATED_IS_USEFUL)
            .remarks(UPDATED_REMARKS)
            .isDelete(UPDATED_IS_DELETE);

        restT_reviewMockMvc.perform(put("/api/t-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_review)))
            .andExpect(status().isOk());

        // Validate the T_review in the database
        List<T_review> t_reviewList = t_reviewRepository.findAll();
        assertThat(t_reviewList).hasSize(databaseSizeBeforeUpdate);
        T_review testT_review = t_reviewList.get(t_reviewList.size() - 1);
        assertThat(testT_review.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testT_review.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testT_review.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testT_review.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testT_review.getIsUseful()).isEqualTo(UPDATED_IS_USEFUL);
        assertThat(testT_review.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testT_review.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
    }

    @Test
    @Transactional
    public void updateNonExistingT_review() throws Exception {
        int databaseSizeBeforeUpdate = t_reviewRepository.findAll().size();

        // Create the T_review

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_reviewMockMvc.perform(put("/api/t-reviews")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_review)))
            .andExpect(status().isCreated());

        // Validate the T_review in the database
        List<T_review> t_reviewList = t_reviewRepository.findAll();
        assertThat(t_reviewList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_review() throws Exception {
        // Initialize the database
        t_reviewRepository.saveAndFlush(t_review);
        int databaseSizeBeforeDelete = t_reviewRepository.findAll().size();

        // Get the t_review
        restT_reviewMockMvc.perform(delete("/api/t-reviews/{id}", t_review.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_review> t_reviewList = t_reviewRepository.findAll();
        assertThat(t_reviewList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_review.class);
        T_review t_review1 = new T_review();
        t_review1.setId(1L);
        T_review t_review2 = new T_review();
        t_review2.setId(t_review1.getId());
        assertThat(t_review1).isEqualTo(t_review2);
        t_review2.setId(2L);
        assertThat(t_review1).isNotEqualTo(t_review2);
        t_review1.setId(null);
        assertThat(t_review1).isNotEqualTo(t_review2);
    }
}
