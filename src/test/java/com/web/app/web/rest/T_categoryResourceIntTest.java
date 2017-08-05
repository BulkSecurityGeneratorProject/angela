package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_category;
import com.web.app.repository.T_categoryRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the T_categoryResource REST controller.
 *
 * @see T_categoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_categoryResourceIntTest {

    private static final String DEFAULT_CAT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CAT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PARENT_ID = 1;
    private static final Integer UPDATED_PARENT_ID = 2;

    private static final Integer DEFAULT_DEPTH = 1;
    private static final Integer UPDATED_DEPTH = 2;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final Integer DEFAULT_IS_DELETE = 1;
    private static final Integer UPDATED_IS_DELETE = 2;

    @Autowired
    private T_categoryRepository t_categoryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_categoryMockMvc;

    private T_category t_category;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_categoryResource t_categoryResource = new T_categoryResource(t_categoryRepository);
        this.restT_categoryMockMvc = MockMvcBuilders.standaloneSetup(t_categoryResource)
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
    public static T_category createEntity(EntityManager em) {
        T_category t_category = new T_category()
            .catName(DEFAULT_CAT_NAME)
            .parentId(DEFAULT_PARENT_ID)
            .depth(DEFAULT_DEPTH)
            .status(DEFAULT_STATUS)
            .priority(DEFAULT_PRIORITY)
            .isDelete(DEFAULT_IS_DELETE);
        return t_category;
    }

    @Before
    public void initTest() {
        t_category = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_category() throws Exception {
        int databaseSizeBeforeCreate = t_categoryRepository.findAll().size();

        // Create the T_category
        restT_categoryMockMvc.perform(post("/api/t-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_category)))
            .andExpect(status().isCreated());

        // Validate the T_category in the database
        List<T_category> t_categoryList = t_categoryRepository.findAll();
        assertThat(t_categoryList).hasSize(databaseSizeBeforeCreate + 1);
        T_category testT_category = t_categoryList.get(t_categoryList.size() - 1);
        assertThat(testT_category.getCatName()).isEqualTo(DEFAULT_CAT_NAME);
        assertThat(testT_category.getParentId()).isEqualTo(DEFAULT_PARENT_ID);
        assertThat(testT_category.getDepth()).isEqualTo(DEFAULT_DEPTH);
        assertThat(testT_category.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testT_category.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testT_category.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
    }

    @Test
    @Transactional
    public void createT_categoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_categoryRepository.findAll().size();

        // Create the T_category with an existing ID
        t_category.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_categoryMockMvc.perform(post("/api/t-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_category)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_category> t_categoryList = t_categoryRepository.findAll();
        assertThat(t_categoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_categories() throws Exception {
        // Initialize the database
        t_categoryRepository.saveAndFlush(t_category);

        // Get all the t_categoryList
        restT_categoryMockMvc.perform(get("/api/t-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_category.getId().intValue())))
            .andExpect(jsonPath("$.[*].catName").value(hasItem(DEFAULT_CAT_NAME.toString())))
            .andExpect(jsonPath("$.[*].parentId").value(hasItem(DEFAULT_PARENT_ID)))
            .andExpect(jsonPath("$.[*].depth").value(hasItem(DEFAULT_DEPTH)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE)));
    }

    @Test
    @Transactional
    public void getT_category() throws Exception {
        // Initialize the database
        t_categoryRepository.saveAndFlush(t_category);

        // Get the t_category
        restT_categoryMockMvc.perform(get("/api/t-categories/{id}", t_category.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_category.getId().intValue()))
            .andExpect(jsonPath("$.catName").value(DEFAULT_CAT_NAME.toString()))
            .andExpect(jsonPath("$.parentId").value(DEFAULT_PARENT_ID))
            .andExpect(jsonPath("$.depth").value(DEFAULT_DEPTH))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE));
    }

    @Test
    @Transactional
    public void getNonExistingT_category() throws Exception {
        // Get the t_category
        restT_categoryMockMvc.perform(get("/api/t-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_category() throws Exception {
        // Initialize the database
        t_categoryRepository.saveAndFlush(t_category);
        int databaseSizeBeforeUpdate = t_categoryRepository.findAll().size();

        // Update the t_category
        T_category updatedT_category = t_categoryRepository.findOne(t_category.getId());
        updatedT_category
            .catName(UPDATED_CAT_NAME)
            .parentId(UPDATED_PARENT_ID)
            .depth(UPDATED_DEPTH)
            .status(UPDATED_STATUS)
            .priority(UPDATED_PRIORITY)
            .isDelete(UPDATED_IS_DELETE);

        restT_categoryMockMvc.perform(put("/api/t-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_category)))
            .andExpect(status().isOk());

        // Validate the T_category in the database
        List<T_category> t_categoryList = t_categoryRepository.findAll();
        assertThat(t_categoryList).hasSize(databaseSizeBeforeUpdate);
        T_category testT_category = t_categoryList.get(t_categoryList.size() - 1);
        assertThat(testT_category.getCatName()).isEqualTo(UPDATED_CAT_NAME);
        assertThat(testT_category.getParentId()).isEqualTo(UPDATED_PARENT_ID);
        assertThat(testT_category.getDepth()).isEqualTo(UPDATED_DEPTH);
        assertThat(testT_category.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testT_category.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testT_category.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
    }

    @Test
    @Transactional
    public void updateNonExistingT_category() throws Exception {
        int databaseSizeBeforeUpdate = t_categoryRepository.findAll().size();

        // Create the T_category

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_categoryMockMvc.perform(put("/api/t-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_category)))
            .andExpect(status().isCreated());

        // Validate the T_category in the database
        List<T_category> t_categoryList = t_categoryRepository.findAll();
        assertThat(t_categoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_category() throws Exception {
        // Initialize the database
        t_categoryRepository.saveAndFlush(t_category);
        int databaseSizeBeforeDelete = t_categoryRepository.findAll().size();

        // Get the t_category
        restT_categoryMockMvc.perform(delete("/api/t-categories/{id}", t_category.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_category> t_categoryList = t_categoryRepository.findAll();
        assertThat(t_categoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_category.class);
        T_category t_category1 = new T_category();
        t_category1.setId(1L);
        T_category t_category2 = new T_category();
        t_category2.setId(t_category1.getId());
        assertThat(t_category1).isEqualTo(t_category2);
        t_category2.setId(2L);
        assertThat(t_category1).isNotEqualTo(t_category2);
        t_category1.setId(null);
        assertThat(t_category1).isNotEqualTo(t_category2);
    }
}
