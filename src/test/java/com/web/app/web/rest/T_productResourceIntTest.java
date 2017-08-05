package com.web.app.web.rest;

import com.web.app.AngelaApp;

import com.web.app.domain.T_product;
import com.web.app.repository.T_productRepository;
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
 * Test class for the T_productResource REST controller.
 *
 * @see T_productResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AngelaApp.class)
public class T_productResourceIntTest {

    private static final String DEFAULT_PRODUCT_SN = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_SN = "BBBBBBBBBB";

    private static final Integer DEFAULT_CATEGORY_ID = 1;
    private static final Integer UPDATED_CATEGORY_ID = 2;

    private static final String DEFAULT_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CLICK_COUNT = 1;
    private static final Integer UPDATED_CLICK_COUNT = 2;

    private static final Double DEFAULT_MARKET_PRICE = 1D;
    private static final Double UPDATED_MARKET_PRICE = 2D;

    private static final Double DEFAULT_PRODUCT_PRICE = 1D;
    private static final Double UPDATED_PRODUCT_PRICE = 2D;

    private static final String DEFAULT_BRIEF = "AAAAAAAAAA";
    private static final String UPDATED_BRIEF = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCT_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_DETAILS = "BBBBBBBBBB";

    private static final Integer DEFAULT_IS_ON_SALE = 1;
    private static final Integer UPDATED_IS_ON_SALE = 2;

    private static final Integer DEFAULT_IS_HOT = 1;
    private static final Integer UPDATED_IS_HOT = 2;

    private static final Integer DEFAULT_SORT_ORDER = 1;
    private static final Integer UPDATED_SORT_ORDER = 2;

    private static final String DEFAULT_PRODUCT_UNIT = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCT_UNIT = "BBBBBBBBBB";

    private static final Integer DEFAULT_STOCK = 1;
    private static final Integer UPDATED_STOCK = 2;

    private static final Integer DEFAULT_PRODUCT_COLOR = 1;
    private static final Integer UPDATED_PRODUCT_COLOR = 2;

    private static final Integer DEFAULT_PRODUCT_AREA = 1;
    private static final Integer UPDATED_PRODUCT_AREA = 2;

    private static final Integer DEFAULT_MATERIAL = 1;
    private static final Integer UPDATED_MATERIAL = 2;

    private static final String DEFAULT_PRO_TAG = "AAAAAAAAAA";
    private static final String UPDATED_PRO_TAG = "BBBBBBBBBB";

    private static final String DEFAULT_SIZE = "AAAAAAAAAA";
    private static final String UPDATED_SIZE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SELL_COUNT = 1;
    private static final Integer UPDATED_SELL_COUNT = 2;

    private static final String DEFAULT_CREATE_USER = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_USER = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_CREATE_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATE_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_LAST_UPDATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_UPDATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_PRODUCT_STATUS = 1;
    private static final Integer UPDATED_PRODUCT_STATUS = 2;

    private static final Integer DEFAULT_IS_DELETE = 1;
    private static final Integer UPDATED_IS_DELETE = 2;

    @Autowired
    private T_productRepository t_productRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restT_productMockMvc;

    private T_product t_product;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        T_productResource t_productResource = new T_productResource(t_productRepository);
        this.restT_productMockMvc = MockMvcBuilders.standaloneSetup(t_productResource)
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
    public static T_product createEntity(EntityManager em) {
        T_product t_product = new T_product()
            .productSn(DEFAULT_PRODUCT_SN)
            .categoryId(DEFAULT_CATEGORY_ID)
            .productName(DEFAULT_PRODUCT_NAME)
            .clickCount(DEFAULT_CLICK_COUNT)
            .marketPrice(DEFAULT_MARKET_PRICE)
            .productPrice(DEFAULT_PRODUCT_PRICE)
            .brief(DEFAULT_BRIEF)
            .productDetails(DEFAULT_PRODUCT_DETAILS)
            .isONSale(DEFAULT_IS_ON_SALE)
            .isHot(DEFAULT_IS_HOT)
            .sortOrder(DEFAULT_SORT_ORDER)
            .productUnit(DEFAULT_PRODUCT_UNIT)
            .stock(DEFAULT_STOCK)
            .productColor(DEFAULT_PRODUCT_COLOR)
            .productArea(DEFAULT_PRODUCT_AREA)
            .material(DEFAULT_MATERIAL)
            .proTag(DEFAULT_PRO_TAG)
            .size(DEFAULT_SIZE)
            .sellCount(DEFAULT_SELL_COUNT)
            .createUser(DEFAULT_CREATE_USER)
            .createDate(DEFAULT_CREATE_DATE)
            .lastUpdate(DEFAULT_LAST_UPDATE)
            .productStatus(DEFAULT_PRODUCT_STATUS)
            .isDelete(DEFAULT_IS_DELETE);
        return t_product;
    }

    @Before
    public void initTest() {
        t_product = createEntity(em);
    }

    @Test
    @Transactional
    public void createT_product() throws Exception {
        int databaseSizeBeforeCreate = t_productRepository.findAll().size();

        // Create the T_product
        restT_productMockMvc.perform(post("/api/t-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_product)))
            .andExpect(status().isCreated());

        // Validate the T_product in the database
        List<T_product> t_productList = t_productRepository.findAll();
        assertThat(t_productList).hasSize(databaseSizeBeforeCreate + 1);
        T_product testT_product = t_productList.get(t_productList.size() - 1);
        assertThat(testT_product.getProductSn()).isEqualTo(DEFAULT_PRODUCT_SN);
        assertThat(testT_product.getCategoryId()).isEqualTo(DEFAULT_CATEGORY_ID);
        assertThat(testT_product.getProductName()).isEqualTo(DEFAULT_PRODUCT_NAME);
        assertThat(testT_product.getClickCount()).isEqualTo(DEFAULT_CLICK_COUNT);
        assertThat(testT_product.getMarketPrice()).isEqualTo(DEFAULT_MARKET_PRICE);
        assertThat(testT_product.getProductPrice()).isEqualTo(DEFAULT_PRODUCT_PRICE);
        assertThat(testT_product.getBrief()).isEqualTo(DEFAULT_BRIEF);
        assertThat(testT_product.getProductDetails()).isEqualTo(DEFAULT_PRODUCT_DETAILS);
        assertThat(testT_product.getIsONSale()).isEqualTo(DEFAULT_IS_ON_SALE);
        assertThat(testT_product.getIsHot()).isEqualTo(DEFAULT_IS_HOT);
        assertThat(testT_product.getSortOrder()).isEqualTo(DEFAULT_SORT_ORDER);
        assertThat(testT_product.getProductUnit()).isEqualTo(DEFAULT_PRODUCT_UNIT);
        assertThat(testT_product.getStock()).isEqualTo(DEFAULT_STOCK);
        assertThat(testT_product.getProductColor()).isEqualTo(DEFAULT_PRODUCT_COLOR);
        assertThat(testT_product.getProductArea()).isEqualTo(DEFAULT_PRODUCT_AREA);
        assertThat(testT_product.getMaterial()).isEqualTo(DEFAULT_MATERIAL);
        assertThat(testT_product.getProTag()).isEqualTo(DEFAULT_PRO_TAG);
        assertThat(testT_product.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testT_product.getSellCount()).isEqualTo(DEFAULT_SELL_COUNT);
        assertThat(testT_product.getCreateUser()).isEqualTo(DEFAULT_CREATE_USER);
        assertThat(testT_product.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testT_product.getLastUpdate()).isEqualTo(DEFAULT_LAST_UPDATE);
        assertThat(testT_product.getProductStatus()).isEqualTo(DEFAULT_PRODUCT_STATUS);
        assertThat(testT_product.getIsDelete()).isEqualTo(DEFAULT_IS_DELETE);
    }

    @Test
    @Transactional
    public void createT_productWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = t_productRepository.findAll().size();

        // Create the T_product with an existing ID
        t_product.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restT_productMockMvc.perform(post("/api/t-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_product)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<T_product> t_productList = t_productRepository.findAll();
        assertThat(t_productList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllT_products() throws Exception {
        // Initialize the database
        t_productRepository.saveAndFlush(t_product);

        // Get all the t_productList
        restT_productMockMvc.perform(get("/api/t-products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(t_product.getId().intValue())))
            .andExpect(jsonPath("$.[*].productSn").value(hasItem(DEFAULT_PRODUCT_SN.toString())))
            .andExpect(jsonPath("$.[*].categoryId").value(hasItem(DEFAULT_CATEGORY_ID)))
            .andExpect(jsonPath("$.[*].productName").value(hasItem(DEFAULT_PRODUCT_NAME.toString())))
            .andExpect(jsonPath("$.[*].clickCount").value(hasItem(DEFAULT_CLICK_COUNT)))
            .andExpect(jsonPath("$.[*].marketPrice").value(hasItem(DEFAULT_MARKET_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].productPrice").value(hasItem(DEFAULT_PRODUCT_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].brief").value(hasItem(DEFAULT_BRIEF.toString())))
            .andExpect(jsonPath("$.[*].productDetails").value(hasItem(DEFAULT_PRODUCT_DETAILS.toString())))
            .andExpect(jsonPath("$.[*].isONSale").value(hasItem(DEFAULT_IS_ON_SALE)))
            .andExpect(jsonPath("$.[*].isHot").value(hasItem(DEFAULT_IS_HOT)))
            .andExpect(jsonPath("$.[*].sortOrder").value(hasItem(DEFAULT_SORT_ORDER)))
            .andExpect(jsonPath("$.[*].productUnit").value(hasItem(DEFAULT_PRODUCT_UNIT.toString())))
            .andExpect(jsonPath("$.[*].stock").value(hasItem(DEFAULT_STOCK)))
            .andExpect(jsonPath("$.[*].productColor").value(hasItem(DEFAULT_PRODUCT_COLOR)))
            .andExpect(jsonPath("$.[*].productArea").value(hasItem(DEFAULT_PRODUCT_AREA)))
            .andExpect(jsonPath("$.[*].material").value(hasItem(DEFAULT_MATERIAL)))
            .andExpect(jsonPath("$.[*].proTag").value(hasItem(DEFAULT_PRO_TAG.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.toString())))
            .andExpect(jsonPath("$.[*].sellCount").value(hasItem(DEFAULT_SELL_COUNT)))
            .andExpect(jsonPath("$.[*].createUser").value(hasItem(DEFAULT_CREATE_USER.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(sameInstant(DEFAULT_CREATE_DATE))))
            .andExpect(jsonPath("$.[*].lastUpdate").value(hasItem(sameInstant(DEFAULT_LAST_UPDATE))))
            .andExpect(jsonPath("$.[*].productStatus").value(hasItem(DEFAULT_PRODUCT_STATUS)))
            .andExpect(jsonPath("$.[*].isDelete").value(hasItem(DEFAULT_IS_DELETE)));
    }

    @Test
    @Transactional
    public void getT_product() throws Exception {
        // Initialize the database
        t_productRepository.saveAndFlush(t_product);

        // Get the t_product
        restT_productMockMvc.perform(get("/api/t-products/{id}", t_product.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(t_product.getId().intValue()))
            .andExpect(jsonPath("$.productSn").value(DEFAULT_PRODUCT_SN.toString()))
            .andExpect(jsonPath("$.categoryId").value(DEFAULT_CATEGORY_ID))
            .andExpect(jsonPath("$.productName").value(DEFAULT_PRODUCT_NAME.toString()))
            .andExpect(jsonPath("$.clickCount").value(DEFAULT_CLICK_COUNT))
            .andExpect(jsonPath("$.marketPrice").value(DEFAULT_MARKET_PRICE.doubleValue()))
            .andExpect(jsonPath("$.productPrice").value(DEFAULT_PRODUCT_PRICE.doubleValue()))
            .andExpect(jsonPath("$.brief").value(DEFAULT_BRIEF.toString()))
            .andExpect(jsonPath("$.productDetails").value(DEFAULT_PRODUCT_DETAILS.toString()))
            .andExpect(jsonPath("$.isONSale").value(DEFAULT_IS_ON_SALE))
            .andExpect(jsonPath("$.isHot").value(DEFAULT_IS_HOT))
            .andExpect(jsonPath("$.sortOrder").value(DEFAULT_SORT_ORDER))
            .andExpect(jsonPath("$.productUnit").value(DEFAULT_PRODUCT_UNIT.toString()))
            .andExpect(jsonPath("$.stock").value(DEFAULT_STOCK))
            .andExpect(jsonPath("$.productColor").value(DEFAULT_PRODUCT_COLOR))
            .andExpect(jsonPath("$.productArea").value(DEFAULT_PRODUCT_AREA))
            .andExpect(jsonPath("$.material").value(DEFAULT_MATERIAL))
            .andExpect(jsonPath("$.proTag").value(DEFAULT_PRO_TAG.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.toString()))
            .andExpect(jsonPath("$.sellCount").value(DEFAULT_SELL_COUNT))
            .andExpect(jsonPath("$.createUser").value(DEFAULT_CREATE_USER.toString()))
            .andExpect(jsonPath("$.createDate").value(sameInstant(DEFAULT_CREATE_DATE)))
            .andExpect(jsonPath("$.lastUpdate").value(sameInstant(DEFAULT_LAST_UPDATE)))
            .andExpect(jsonPath("$.productStatus").value(DEFAULT_PRODUCT_STATUS))
            .andExpect(jsonPath("$.isDelete").value(DEFAULT_IS_DELETE));
    }

    @Test
    @Transactional
    public void getNonExistingT_product() throws Exception {
        // Get the t_product
        restT_productMockMvc.perform(get("/api/t-products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateT_product() throws Exception {
        // Initialize the database
        t_productRepository.saveAndFlush(t_product);
        int databaseSizeBeforeUpdate = t_productRepository.findAll().size();

        // Update the t_product
        T_product updatedT_product = t_productRepository.findOne(t_product.getId());
        updatedT_product
            .productSn(UPDATED_PRODUCT_SN)
            .categoryId(UPDATED_CATEGORY_ID)
            .productName(UPDATED_PRODUCT_NAME)
            .clickCount(UPDATED_CLICK_COUNT)
            .marketPrice(UPDATED_MARKET_PRICE)
            .productPrice(UPDATED_PRODUCT_PRICE)
            .brief(UPDATED_BRIEF)
            .productDetails(UPDATED_PRODUCT_DETAILS)
            .isONSale(UPDATED_IS_ON_SALE)
            .isHot(UPDATED_IS_HOT)
            .sortOrder(UPDATED_SORT_ORDER)
            .productUnit(UPDATED_PRODUCT_UNIT)
            .stock(UPDATED_STOCK)
            .productColor(UPDATED_PRODUCT_COLOR)
            .productArea(UPDATED_PRODUCT_AREA)
            .material(UPDATED_MATERIAL)
            .proTag(UPDATED_PRO_TAG)
            .size(UPDATED_SIZE)
            .sellCount(UPDATED_SELL_COUNT)
            .createUser(UPDATED_CREATE_USER)
            .createDate(UPDATED_CREATE_DATE)
            .lastUpdate(UPDATED_LAST_UPDATE)
            .productStatus(UPDATED_PRODUCT_STATUS)
            .isDelete(UPDATED_IS_DELETE);

        restT_productMockMvc.perform(put("/api/t-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedT_product)))
            .andExpect(status().isOk());

        // Validate the T_product in the database
        List<T_product> t_productList = t_productRepository.findAll();
        assertThat(t_productList).hasSize(databaseSizeBeforeUpdate);
        T_product testT_product = t_productList.get(t_productList.size() - 1);
        assertThat(testT_product.getProductSn()).isEqualTo(UPDATED_PRODUCT_SN);
        assertThat(testT_product.getCategoryId()).isEqualTo(UPDATED_CATEGORY_ID);
        assertThat(testT_product.getProductName()).isEqualTo(UPDATED_PRODUCT_NAME);
        assertThat(testT_product.getClickCount()).isEqualTo(UPDATED_CLICK_COUNT);
        assertThat(testT_product.getMarketPrice()).isEqualTo(UPDATED_MARKET_PRICE);
        assertThat(testT_product.getProductPrice()).isEqualTo(UPDATED_PRODUCT_PRICE);
        assertThat(testT_product.getBrief()).isEqualTo(UPDATED_BRIEF);
        assertThat(testT_product.getProductDetails()).isEqualTo(UPDATED_PRODUCT_DETAILS);
        assertThat(testT_product.getIsONSale()).isEqualTo(UPDATED_IS_ON_SALE);
        assertThat(testT_product.getIsHot()).isEqualTo(UPDATED_IS_HOT);
        assertThat(testT_product.getSortOrder()).isEqualTo(UPDATED_SORT_ORDER);
        assertThat(testT_product.getProductUnit()).isEqualTo(UPDATED_PRODUCT_UNIT);
        assertThat(testT_product.getStock()).isEqualTo(UPDATED_STOCK);
        assertThat(testT_product.getProductColor()).isEqualTo(UPDATED_PRODUCT_COLOR);
        assertThat(testT_product.getProductArea()).isEqualTo(UPDATED_PRODUCT_AREA);
        assertThat(testT_product.getMaterial()).isEqualTo(UPDATED_MATERIAL);
        assertThat(testT_product.getProTag()).isEqualTo(UPDATED_PRO_TAG);
        assertThat(testT_product.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testT_product.getSellCount()).isEqualTo(UPDATED_SELL_COUNT);
        assertThat(testT_product.getCreateUser()).isEqualTo(UPDATED_CREATE_USER);
        assertThat(testT_product.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testT_product.getLastUpdate()).isEqualTo(UPDATED_LAST_UPDATE);
        assertThat(testT_product.getProductStatus()).isEqualTo(UPDATED_PRODUCT_STATUS);
        assertThat(testT_product.getIsDelete()).isEqualTo(UPDATED_IS_DELETE);
    }

    @Test
    @Transactional
    public void updateNonExistingT_product() throws Exception {
        int databaseSizeBeforeUpdate = t_productRepository.findAll().size();

        // Create the T_product

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restT_productMockMvc.perform(put("/api/t-products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(t_product)))
            .andExpect(status().isCreated());

        // Validate the T_product in the database
        List<T_product> t_productList = t_productRepository.findAll();
        assertThat(t_productList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteT_product() throws Exception {
        // Initialize the database
        t_productRepository.saveAndFlush(t_product);
        int databaseSizeBeforeDelete = t_productRepository.findAll().size();

        // Get the t_product
        restT_productMockMvc.perform(delete("/api/t-products/{id}", t_product.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<T_product> t_productList = t_productRepository.findAll();
        assertThat(t_productList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(T_product.class);
        T_product t_product1 = new T_product();
        t_product1.setId(1L);
        T_product t_product2 = new T_product();
        t_product2.setId(t_product1.getId());
        assertThat(t_product1).isEqualTo(t_product2);
        t_product2.setId(2L);
        assertThat(t_product1).isNotEqualTo(t_product2);
        t_product1.setId(null);
        assertThat(t_product1).isNotEqualTo(t_product2);
    }
}
