package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_cart_info;
import com.web.app.repository.T_cart_infoRepository;
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
 * Test class for the T_cart_infoResource REST controller.
 *
 * @see T_cart_infoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_cart_infoResourceIntTest {

    private static final String DEFAULT_PRODUCT_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_COUNT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_COUNT = "BBBBBBBBBB";

    private static final String DEFAULT_DELIVERY_DATE = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERY_DATE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATEDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATEDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_ASI_SAGE_NO = 1;
    private static final Integer UPDATED_ASI_SAGE_NO = 2;

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    @Autowired
    private T_cart_infoRepository t_cart_infoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_cart_infoMockMvc;

    private T_cart_info t_cart_info;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_cart_infoResource t_cart_infoResource = new T_cart_infoResource(t_cart_infoRepository);
        this.restT_cart_infoMockMvc = MockMvcBuilders.standaloneSetup(t_cart_infoResource)
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
    public static T_cart_info createEntity(EntityManager em) {
        T_cart_info t_cart_info = new T_cart_info()
            .productId(DEFAULT_PRODUCT_ID)
            .productCount(DEFAULT_PRODUCT_COUNT)
            .deliveryDate(DEFAULT_DELIVERY_DATE)
            .createdate(DEFAULT_CREATEDATE)
            .message(DEFAULT_MESSAGE)
            .asiSageNo(DEFAULT_ASI_SAGE_NO)
            .createDate(DEFAULT_CREATE_DATE)
            .createUser(DEFAULT_CREATE_USER);
        return t_cart_info;
    }

    @Before
    public void initTest() {
        t_cart_info = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_cart_info() throws Exception {
        int databaseSizeBeforeCreate = t_cart_infoRepository.findAll().size();

        // Create the T_cart_info
        restT_cart_infoMockMvc.perform(post("/api/t-cart-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_cart_info)))
            .andExpect(status().isCreated());

        // Validate the T_cart_info in the database
        List<T_cart_info> t_cart_infoList = t_cart_infoRepository.findAll();
        assertThat(t_cart_infoList).hasSize(databaseSizeBeforeCreate + 1);
        T_cart_info testT_cart_info = t_cart_infoList.get(t_cart_infoList.size() - 1);
        assertThat(testT_cart_info.getProductId()).isEqualTo(DEFAULT_PRODUCT_ID);
        assertThat(testT_cart_info.getProductCount()).isEqualTo(DEFAULT_PRODUCT_COUNT);
        assertThat(testT_cart_info.getDeliveryDate()).isEqualTo(DEFAULT_DELIVERY_DATE);
        assertThat(testT_cart_info.getCreatedate()).isEqualTo(DEFAULT_CREATEDATE);
        assertThat(testT_cart_info.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testT_cart_info.getAsiSageNo()).isEqualTo(DEFAULT_ASI_SAGE_NO);
        assertThat(testT_cart_info.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testT_cart_info.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
    }

    @Test
    @Transactional
    public void createT_cart_infoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_cart_infoRepository.findAll().size();

        // Create the T_cart_info with an existing ID
        t_cart_info.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_cart_infoMockMvc.perform(post("/api/t-cart-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_cart_info)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_cart_info> t_cart_infoList = t_cart_infoRepository.findAll();
        assertThat(t_cart_infoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_cart_infos() throws Exception {
        // Initialize the database
        t_cart_infoRepository.saveAndFlush(t_cart_info);

        // Get all the t_cart_infoList
        restT_cart_infoMockMvc.perform(get("/api/t-cart-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_cart_info.getId().intValue())))
            .andExpect(jsonPath("$.[*].productId").value(hasItem(DEFAULT_PRODUCT_ID.toString())))
            .andExpect(jsonPath("$.[*].productCount").value(hasItem(DEFAULT_PRODUCT_COUNT.toString())))
            .andExpect(jsonPath("$.[*].deliveryDate").value(hasItem(DEFAULT_DELIVERY_DATE.toString())))
            .andExpect(jsonPath("$.[*].createdate").value(hasItem(sameInstant(DEFAULT_CREATEDATE))))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE.toString())))
            .andExpect(jsonPath("$.[*].asiSageNo").value(hasItem(DEFAULT_ASI_SAGE_NO)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.toString())));
    }

    @Test
    @Transactional
    public void getT_cart_info() throws Exception {
        // Initialize the database
        t_cart_infoRepository.saveAndFlush(t_cart_info);

        // Get the t_cart_info
        restT_cart_infoMockMvc.perform(get("/api/t-cart-infos/{id}", t_cart_info.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_cart_info.getId().intValue()))
            .andExpect(jsonPath("$.productId").value(DEFAULT_PRODUCT_ID.toString()))
            .andExpect(jsonPath("$.productCount").value(DEFAULT_PRODUCT_COUNT.toString()))
            .andExpect(jsonPath("$.deliveryDate").value(DEFAULT_DELIVERY_DATE.toString()))
            .andExpect(jsonPath("$.createdate").value(sameInstant(DEFAULT_CREATEDATE)))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE.toString()))
            .andExpect(jsonPath("$.asiSageNo").value(DEFAULT_ASI_SAGE_NO))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingT_cart_info() throws Exception {
        // Get the t_cart_info
        restT_cart_infoMockMvc.perform(get("/api/t-cart-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_cart_info() throws Exception {
        // Initialize the database
        t_cart_infoRepository.saveAndFlush(t_cart_info);
        int databaseSizeBeforeUpdate = t_cart_infoRepository.findAll().size();

        // Update the t_cart_info
        T_cart_info updatedT_cart_info = t_cart_infoRepository.findOne(t_cart_info.getId());
        updatedT_cart_info
            .productId(UPDATED_PRODUCT_ID)
            .productCount(UPDATED_PRODUCT_COUNT)
            .deliveryDate(UPDATED_DELIVERY_DATE)
            .createdate(UPDATED_CREATEDATE)
            .message(UPDATED_MESSAGE)
            .asiSageNo(UPDATED_ASI_SAGE_NO)
            .createDate(UPDATED_CREATE_DATE)
            .createUser(UPDATED_CREATE_USER);

        restT_cart_infoMockMvc.perform(put("/api/t-cart-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_cart_info)))
            .andExpect(status().isOk());

        // Validate the T_cart_info in the database
        List<T_cart_info> t_cart_infoList = t_cart_infoRepository.findAll();
        assertThat(t_cart_infoList).hasSize(databaseSizeBeforeUpdate);
        T_cart_info testT_cart_info = t_cart_infoList.get(t_cart_infoList.size() - 1);
        assertThat(testT_cart_info.getProductId()).isEqualTo(UPDATED_PRODUCT_ID);
        assertThat(testT_cart_info.getProductCount()).isEqualTo(UPDATED_PRODUCT_COUNT);
        assertThat(testT_cart_info.getDeliveryDate()).isEqualTo(UPDATED_DELIVERY_DATE);
        assertThat(testT_cart_info.getCreatedate()).isEqualTo(UPDATED_CREATEDATE);
        assertThat(testT_cart_info.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testT_cart_info.getAsiSageNo()).isEqualTo(UPDATED_ASI_SAGE_NO);
        assertThat(testT_cart_info.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testT_cart_info.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingT_cart_info() throws Exception {
        int databaseSizeBeforeUpdate = t_cart_infoRepository.findAll().size();

        // Create the T_cart_info

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_cart_infoMockMvc.perform(put("/api/t-cart-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_cart_info)))
            .andExpect(status().isCreated());

        // Validate the T_cart_info in the database
        List<T_cart_info> t_cart_infoList = t_cart_infoRepository.findAll();
        assertThat(t_cart_infoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_cart_info() throws Exception {
        // Initialize the database
        t_cart_infoRepository.saveAndFlush(t_cart_info);
        int databaseSizeBeforeDelete = t_cart_infoRepository.findAll().size();

        // Get the t_cart_info
        restT_cart_infoMockMvc.perform(delete("/api/t-cart-infos/{id}", t_cart_info.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_cart_info> t_cart_infoList = t_cart_infoRepository.findAll();
        assertThat(t_cart_infoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_cart_info.class);
        T_cart_info t_cart_info1 = new T_cart_info();
        t_cart_info1.setId(1L);
        T_cart_info t_cart_info2 = new T_cart_info();
        t_cart_info2.setId(t_cart_info1.getId());
        assertThat(t_cart_info1).isEqualTo(t_cart_info2);
        t_cart_info2.setId(2L);
        assertThat(t_cart_info1).isNotEqualTo(t_cart_info2);
        t_cart_info1.setId(null);
        assertThat(t_cart_info1).isNotEqualTo(t_cart_info2);
    }
}
