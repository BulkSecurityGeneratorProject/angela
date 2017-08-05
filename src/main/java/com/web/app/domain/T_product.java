package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A T_product.
 */
@Entity
@Table(name = "t_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_sn")
    private String productSn;

    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "click_count")
    private Integer clickCount;

    @Column(name = "market_price")
    private Double marketPrice;

    @Column(name = "product_price")
    private Double productPrice;

    @Column(name = "brief")
    private String brief;

    @Column(name = "product_details")
    private String productDetails;

    @Column(name = "is_on_sale")
    private Integer isONSale;

    @Column(name = "is_hot")
    private Integer isHot;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "product_unit")
    private String productUnit;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "product_color")
    private Integer productColor;

    @Column(name = "product_area")
    private Integer productArea;

    @Column(name = "material")
    private Integer material;

    @Column(name = "pro_tag")
    private String proTag;

    @Column(name = "jhi_size")
    private String size;

    @Column(name = "sell_count")
    private Integer sellCount;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "last_update")
    private ZonedDateTime lastUpdate;

    @Column(name = "product_status")
    private Integer productStatus;

    @Column(name = "is_delete")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductSn() {
        return productSn;
    }

    public T_product productSn(String productSn) {
        this.productSn = productSn;
        return this;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public T_product categoryId(Integer categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public T_product productName(String productName) {
        this.productName = productName;
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public T_product clickCount(Integer clickCount) {
        this.clickCount = clickCount;
        return this;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public T_product marketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
        return this;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public T_product productPrice(Double productPrice) {
        this.productPrice = productPrice;
        return this;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getBrief() {
        return brief;
    }

    public T_product brief(String brief) {
        this.brief = brief;
        return this;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public T_product productDetails(String productDetails) {
        this.productDetails = productDetails;
        return this;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public Integer getIsONSale() {
        return isONSale;
    }

    public T_product isONSale(Integer isONSale) {
        this.isONSale = isONSale;
        return this;
    }

    public void setIsONSale(Integer isONSale) {
        this.isONSale = isONSale;
    }

    public Integer getIsHot() {
        return isHot;
    }

    public T_product isHot(Integer isHot) {
        this.isHot = isHot;
        return this;
    }

    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public T_product sortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public T_product productUnit(String productUnit) {
        this.productUnit = productUnit;
        return this;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public Integer getStock() {
        return stock;
    }

    public T_product stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getProductColor() {
        return productColor;
    }

    public T_product productColor(Integer productColor) {
        this.productColor = productColor;
        return this;
    }

    public void setProductColor(Integer productColor) {
        this.productColor = productColor;
    }

    public Integer getProductArea() {
        return productArea;
    }

    public T_product productArea(Integer productArea) {
        this.productArea = productArea;
        return this;
    }

    public void setProductArea(Integer productArea) {
        this.productArea = productArea;
    }

    public Integer getMaterial() {
        return material;
    }

    public T_product material(Integer material) {
        this.material = material;
        return this;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public String getProTag() {
        return proTag;
    }

    public T_product proTag(String proTag) {
        this.proTag = proTag;
        return this;
    }

    public void setProTag(String proTag) {
        this.proTag = proTag;
    }

    public String getSize() {
        return size;
    }

    public T_product size(String size) {
        this.size = size;
        return this;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getSellCount() {
        return sellCount;
    }

    public T_product sellCount(Integer sellCount) {
        this.sellCount = sellCount;
        return this;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public T_product createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public T_product createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }

    public T_product lastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public T_product productStatus(Integer productStatus) {
        this.productStatus = productStatus;
        return this;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public T_product isDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        T_product t_product = (T_product) o;
        if (t_product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_product{" +
            "id=" + getId() +
            ", productSn='" + getProductSn() + "'" +
            ", categoryId='" + getCategoryId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", clickCount='" + getClickCount() + "'" +
            ", marketPrice='" + getMarketPrice() + "'" +
            ", productPrice='" + getProductPrice() + "'" +
            ", brief='" + getBrief() + "'" +
            ", productDetails='" + getProductDetails() + "'" +
            ", isONSale='" + getIsONSale() + "'" +
            ", isHot='" + getIsHot() + "'" +
            ", sortOrder='" + getSortOrder() + "'" +
            ", productUnit='" + getProductUnit() + "'" +
            ", stock='" + getStock() + "'" +
            ", productColor='" + getProductColor() + "'" +
            ", productArea='" + getProductArea() + "'" +
            ", material='" + getMaterial() + "'" +
            ", proTag='" + getProTag() + "'" +
            ", size='" + getSize() + "'" +
            ", sellCount='" + getSellCount() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", lastUpdate='" + getLastUpdate() + "'" +
            ", productStatus='" + getProductStatus() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            "}";
    }
}
