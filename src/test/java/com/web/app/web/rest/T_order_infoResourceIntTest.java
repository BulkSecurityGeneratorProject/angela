package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_order_info;
import com.web.app.repository.T_order_infoRepository;
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
 * Test class for the T_order_infoResource REST controller.
 *
 * @see T_order_infoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_order_infoResourceIntTest {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_COUNT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_COUNT = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String DEFAULT_CONNECT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CONNECT_STATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    @Autowired
    private T_order_infoRepository t_order_infoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_order_infoMockMvc;

    private T_order_info t_order_info;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_order_infoResource t_order_infoResource = new T_order_infoResource(t_order_infoRepository);
        this.restT_order_infoMockMvc = MockMvcBuilders.standaloneSetup(t_order_infoResource)
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
    public static T_order_info createEntity(EntityManager em) {
        T_order_info t_order_info = new T_order_info()
            .orderId(DEFAULT_ORDER_ID)
            .productId(DEFAULT_PRODUCT_ID)
            .productCount(DEFAULT_PRODUCT_COUNT)
            .status(DEFAULT_STATUS)
            .connectStatus(DEFAULT_CONNECT_STATUS)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER);
        return t_order_info;
    }

    @Before
    public void initTest() {
        t_order_info = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_order_info() throws Exception {
        int databaseSizeBeforeCreate = t_order_infoRepository.findAll().size();

        // Create the T_order_info
        restT_order_infoMockMvc.perform(post("/api/t-order-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_order_info)))
            .andExpect(status().isCreated());

        // Validate the T_order_info in the database
        List<T_order_info> t_order_infoList = t_order_infoRepository.findAll();
        assertThat(t_order_infoList).hasSize(databaseSizeBeforeCreate + 1);
        T_order_info testT_order_info = t_order_infoList.get(t_order_infoList.size() - 1);
        assertThat(testT_order_info.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testT_order_info.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testT_order_info.getProductCount()).isEqualTo(DEFAULT_PRODUCT_COUNT);
        assertThat(testT_order_info.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testT_order_info.getConnectStatus()).isEqualTo(DEFAULT_CONNECT_STATUS);
        assertThat(testT_order_info.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testT_order_info.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    public void createT_order_infoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_order_infoRepository.findAll().size();

        // Create the T_order_info with an existing ID
        t_order_info.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_order_infoMockMvc.perform(post("/api/t-order-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_order_info)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_order_info> t_order_infoList = t_order_infoRepository.findAll();
        assertThat(t_order_infoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_order_infos() throws Exception {
        // Initialize the database
        t_order_infoRepository.saveAndFlush(t_order_info);

        // Get all the t_order_infoList
        restT_order_infoMockMvc.perform(get("/api/t-order-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_order_info.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID.toString())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())))
            .andExpect(jsonPath("$.[*].productCount").value(hasItem(DEFAULT_PRODUCT_COUNT.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].connectStatus").value(hasItem(DEFAULT_CONNECT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.toString())));
    }

    @Test
    @Transactional
    public void getT_order_info() throws Exception {
        // Initialize the database
        t_order_infoRepository.saveAndFlush(t_order_info);

        // Get the t_order_info
        restT_order_infoMockMvc.perform(get("/api/t-order-infos/{id}", t_order_info.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_order_info.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID.toString()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()))
            .andExpect(jsonPath("$.productCount").value(DEFAULT_PRODUCT_COUNT.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.connectStatus").value(DEFAULT_CONNECT_STATUS.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingT_order_info() throws Exception {
        // Get the t_order_info
        restT_order_infoMockMvc.perform(get("/api/t-order-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_order_info() throws Exception {
        // Initialize the database
        t_order_infoRepository.saveAndFlush(t_order_info);
        int databaseSizeBeforeUpdate = t_order_infoRepository.findAll().size();

        // Update the t_order_info
        T_order_info updatedT_order_info = t_order_infoRepository.findOne(t_order_info.getId());
        updatedT_order_info
            .orderId(UPDATED_ORDER_ID)
            .productId(UPDATED_PRODUCT_ID)
            .productCount(UPDATED_PRODUCT_COUNT)
            .status(UPDATED_STATUS)
            .connectStatus(UPDATED_CONNECT_STATUS)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER);

        restT_order_infoMockMvc.perform(put("/api/t-order-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_order_info)))
            .andExpect(status().isOk());

        // Validate the T_order_info in the database
        List<T_order_info> t_order_infoList = t_order_infoRepository.findAll();
        assertThat(t_order_infoList).hasSize(databaseSizeBeforeUpdate);
        T_order_info testT_order_info = t_order_infoList.get(t_order_infoList.size() - 1);
        assertThat(testT_order_info.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testT_order_info.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testT_order_info.getProductCount()).isEqualTo(UPDATED_PRODUCT_COUNT);
        assertThat(testT_order_info.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testT_order_info.getConnectStatus()).isEqualTo(UPDATED_CONNECT_STATUS);
        assertThat(testT_order_info.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testT_order_info.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingT_order_info() throws Exception {
        int databaseSizeBeforeUpdate = t_order_infoRepository.findAll().size();

        // Create the T_order_info

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_order_infoMockMvc.perform(put("/api/t-order-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_order_info)))
            .andExpect(status().isCreated());

        // Validate the T_order_info in the database
        List<T_order_info> t_order_infoList = t_order_infoRepository.findAll();
        assertThat(t_order_infoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_order_info() throws Exception {
        // Initialize the database
        t_order_infoRepository.saveAndFlush(t_order_info);
        int databaseSizeBeforeDelete = t_order_infoRepository.findAll().size();

        // Get the t_order_info
        restT_order_infoMockMvc.perform(delete("/api/t-order-infos/{id}", t_order_info.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_order_info> t_order_infoList = t_order_infoRepository.findAll();
        assertThat(t_order_infoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_order_info.class);
        T_order_info t_order_info1 = new T_order_info();
        t_order_info1.setId(1L);
        T_order_info t_order_info2 = new T_order_info();
        t_order_info2.setId(t_order_info1.getId());
        assertThat(t_order_info1).isEqualTo(t_order_info2);
        t_order_info2.setId(2L);
        assertThat(t_order_info1).isNotEqualTo(t_order_info2);
        t_order_info1.setId(null);
        assertThat(t_order_info1).isNotEqualTo(t_order_info2);
    }
}
