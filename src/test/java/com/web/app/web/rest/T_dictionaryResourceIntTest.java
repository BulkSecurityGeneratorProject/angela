package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_dictionary;
import com.web.app.repository.T_dictionaryRepository;
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
 * Test class for the T_dictionaryResource REST controller.
 *
 * @see T_dictionaryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_dictionaryResourceIntTest {

    private static final String DEFAULT_DICT_ID = "AAAAAAAAAA";
    private static final String UPDATED_DICT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DICT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DICT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DICT_KEY = "AAAAAAAAAA";
    private static final String UPDATED_DICT_KEY = "BBBBBBBBBB";

    private static final String DEFAULT_DICT_VAL = "AAAAAAAAAA";
    private static final String UPDATED_DICT_VAL = "BBBBBBBBBB";

    private static final String DEFAULT_DICT_DES = "AAAAAAAAAA";
    private static final String UPDATED_DICT_DES = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_IS_DELETE = 1;
    private static final Integer UPDATED_IS_DELETE = 2;

    @Autowired
    private T_dictionaryRepository t_dictionaryRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_dictionaryMockMvc;

    private T_dictionary t_dictionary;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_dictionaryResource t_dictionaryResource = new T_dictionaryResource(t_dictionaryRepository);
        this.restT_dictionaryMockMvc = MockMvcBuilders.standaloneSetup(t_dictionaryResource)
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
    public static T_dictionary createEntity(EntityManager em) {
        T_dictionary t_dictionary = new T_dictionary()
            .dictId(DEFAULT_DICT_ID)
            .dictName(DEFAULT_DICT_NAME)
            .dictKey(DEFAULT_DICT_KEY)
            .dictVal(DEFAULT_DICT_VAL)
            .dictDes(DEFAULT_DICT_DES)
            .createDate(DEFAULT_CREATE_DATE)
            .isDelete(DEFAULT_IS_DELETE);
        return t_dictionary;
    }

    @Before
    public void initTest() {
        t_dictionary = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_dictionary() throws Exception {
        int databaseSizeBeforeCreate = t_dictionaryRepository.findAll().size();

        // Create the T_dictionary
        restT_dictionaryMockMvc.perform(post("/api/t-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_dictionary)))
            .andExpect(status().isCreated());

        // Validate the T_dictionary in the database
        List<T_dictionary> t_dictionaryList = t_dictionaryRepository.findAll();
        assertThat(t_dictionaryList).hasSize(databaseSizeBeforeCreate + 1);
        T_dictionary testT_dictionary = t_dictionaryList.get(t_dictionaryList.size() - 1);
        assertThat(testT_dictionary.getDictId()).isEqualTo(DEFAULT_DICT_ID);
        assertThat(testT_dictionary.getDictName()).isEqualTo(DEFAULT_DICT_NAME);
        assertThat(testT_dictionary.getDictKey()).isEqualTo(DEFAULT_DICT_KEY);
        assertThat(testT_dictionary.getDictVal()).isEqualTo(DEFAULT_DICT_VAL);
        assertThat(testT_dictionary.getDictDes()).isEqualTo(DEFAULT_DICT_DES);
        assertThat(testT_dictionary.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testT_dictionary.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
    }

    @Test
    @Transactional
    public void createT_dictionaryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_dictionaryRepository.findAll().size();

        // Create the T_dictionary with an existing ID
        t_dictionary.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_dictionaryMockMvc.perform(post("/api/t-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_dictionary)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_dictionary> t_dictionaryList = t_dictionaryRepository.findAll();
        assertThat(t_dictionaryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_dictionaries() throws Exception {
        // Initialize the database
        t_dictionaryRepository.saveAndFlush(t_dictionary);

        // Get all the t_dictionaryList
        restT_dictionaryMockMvc.perform(get("/api/t-dictionaries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_dictionary.getId().intValue())))
            .andExpect(jsonPath("$.[*].dictId").value(hasItem(DEFAULT_DICT_ID.toString())))
            .andExpect(jsonPath("$.[*].dictName").value(hasItem(DEFAULT_DICT_NAME.toString())))
            .andExpect(jsonPath("$.[*].dictKey").value(hasItem(DEFAULT_DICT_KEY.toString())))
            .andExpect(jsonPath("$.[*].dictVal").value(hasItem(DEFAULT_DICT_VAL.toString())))
            .andExpect(jsonPath("$.[*].dictDes").value(hasItem(DEFAULT_DICT_DES.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE)));
    }

    @Test
    @Transactional
    public void getT_dictionary() throws Exception {
        // Initialize the database
        t_dictionaryRepository.saveAndFlush(t_dictionary);

        // Get the t_dictionary
        restT_dictionaryMockMvc.perform(get("/api/t-dictionaries/{id}", t_dictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_dictionary.getId().intValue()))
            .andExpect(jsonPath("$.dictId").value(DEFAULT_DICT_ID.toString()))
            .andExpect(jsonPath("$.dictName").value(DEFAULT_DICT_NAME.toString()))
            .andExpect(jsonPath("$.dictKey").value(DEFAULT_DICT_KEY.toString()))
            .andExpect(jsonPath("$.dictVal").value(DEFAULT_DICT_VAL.toString()))
            .andExpect(jsonPath("$.dictDes").value(DEFAULT_DICT_DES.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE));
    }

    @Test
    @Transactional
    public void getNonExistingT_dictionary() throws Exception {
        // Get the t_dictionary
        restT_dictionaryMockMvc.perform(get("/api/t-dictionaries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_dictionary() throws Exception {
        // Initialize the database
        t_dictionaryRepository.saveAndFlush(t_dictionary);
        int databaseSizeBeforeUpdate = t_dictionaryRepository.findAll().size();

        // Update the t_dictionary
        T_dictionary updatedT_dictionary = t_dictionaryRepository.findOne(t_dictionary.getId());
        updatedT_dictionary
            .dictId(UPDATED_DICT_ID)
            .dictName(UPDATED_DICT_NAME)
            .dictKey(UPDATED_DICT_KEY)
            .dictVal(UPDATED_DICT_VAL)
            .dictDes(UPDATED_DICT_DES)
            .createDate(UPDATED_CREATE_DATE)
            .isDelete(UPDATED_IS_DELETE);

        restT_dictionaryMockMvc.perform(put("/api/t-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_dictionary)))
            .andExpect(status().isOk());

        // Validate the T_dictionary in the database
        List<T_dictionary> t_dictionaryList = t_dictionaryRepository.findAll();
        assertThat(t_dictionaryList).hasSize(databaseSizeBeforeUpdate);
        T_dictionary testT_dictionary = t_dictionaryList.get(t_dictionaryList.size() - 1);
        assertThat(testT_dictionary.getDictId()).isEqualTo(UPDATED_DICT_ID);
        assertThat(testT_dictionary.getDictName()).isEqualTo(UPDATED_DICT_NAME);
        assertThat(testT_dictionary.getDictKey()).isEqualTo(UPDATED_DICT_KEY);
        assertThat(testT_dictionary.getDictVal()).isEqualTo(UPDATED_DICT_VAL);
        assertThat(testT_dictionary.getDictDes()).isEqualTo(UPDATED_DICT_DES);
        assertThat(testT_dictionary.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testT_dictionary.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
    }

    @Test
    @Transactional
    public void updateNonExistingT_dictionary() throws Exception {
        int databaseSizeBeforeUpdate = t_dictionaryRepository.findAll().size();

        // Create the T_dictionary

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_dictionaryMockMvc.perform(put("/api/t-dictionaries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_dictionary)))
            .andExpect(status().isCreated());

        // Validate the T_dictionary in the database
        List<T_dictionary> t_dictionaryList = t_dictionaryRepository.findAll();
        assertThat(t_dictionaryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_dictionary() throws Exception {
        // Initialize the database
        t_dictionaryRepository.saveAndFlush(t_dictionary);
        int databaseSizeBeforeDelete = t_dictionaryRepository.findAll().size();

        // Get the t_dictionary
        restT_dictionaryMockMvc.perform(delete("/api/t-dictionaries/{id}", t_dictionary.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_dictionary> t_dictionaryList = t_dictionaryRepository.findAll();
        assertThat(t_dictionaryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_dictionary.class);
        T_dictionary t_dictionary1 = new T_dictionary();
        t_dictionary1.setId(1L);
        T_dictionary t_dictionary2 = new T_dictionary();
        t_dictionary2.setId(t_dictionary1.getId());
        assertThat(t_dictionary1).isEqualTo(t_dictionary2);
        t_dictionary2.setId(2L);
        assertThat(t_dictionary1).isNotEqualTo(t_dictionary2);
        t_dictionary1.setId(null);
        assertThat(t_dictionary1).isNotEqualTo(t_dictionary2);
    }
}
