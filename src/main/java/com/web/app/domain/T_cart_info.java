package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A T_cart_info.
 */
@Entity
@Table(name = "t_cart_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_cart_info implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_count")
    private String productCount;

    @Column(name = "delivery_date")
    private String deliveryDate;

    @Column(name = "createdate")
    private ZonedDateTime createdate;

    @Column(name = "message")
    private String message;

    @Column(name = "asi_sage_no")
    private Integer asiSageNo;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "create_user")
    private String createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public T_cart_info productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCount() {
        return productCount;
    }

    public T_cart_info productCount(String productCount) {
        this.productCount = productCount;
        return this;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public T_cart_info deliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public T_cart_info createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public String getMessage() {
        return message;
    }

    public T_cart_info message(String message) {
        this.message = message;
        return this;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAsiSageNo() {
        return asiSageNo;
    }

    public T_cart_info asiSageNo(Integer asiSageNo) {
        this.asiSageNo = asiSageNo;
        return this;
    }

    public void setAsiSageNo(Integer asiSageNo) {
        this.asiSageNo = asiSageNo;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public T_cart_info createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public T_cart_info createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        T_cart_info t_cart_info = (T_cart_info) o;
        if (t_cart_info.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_cart_info.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_cart_info{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", productCount='" + getProductCount() + "'" +
            ", deliveryDate='" + getDeliveryDate() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", message='" + getMessage() + "'" +
            ", asiSageNo='" + getAsiSageNo() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
