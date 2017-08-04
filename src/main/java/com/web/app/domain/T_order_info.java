package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A T_order_info.
 */
@Entity
@Table(name = "t_order_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_order_info implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_count")
    private String productCount;

    @Column(name = "status")
    private Integer status;

    @Column(name = "connect_status")
    private String connectStatus;

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

    public String getOrderId() {
        return orderId;
    }

    public T_order_info orderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public T_order_info productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCount() {
        return productCount;
    }

    public T_order_info productCount(String productCount) {
        this.productCount = productCount;
        return this;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public Integer getStatus() {
        return status;
    }

    public T_order_info status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConnectStatus() {
        return connectStatus;
    }

    public T_order_info connectStatus(String connectStatus) {
        this.connectStatus = connectStatus;
        return this;
    }

    public void setConnectStatus(String connectStatus) {
        this.connectStatus = connectStatus;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public T_order_info createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public T_order_info createUser(String createUser) {
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
        T_order_info t_order_info = (T_order_info) o;
        if (t_order_info.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_order_info.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_order_info{" +
            "id=" + getId() +
            ", orderId='" + getOrderId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", productCount='" + getProductCount() + "'" +
            ", status='" + getStatus() + "'" +
            ", connectStatus='" + getConnectStatus() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
