package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A T_pictures.
 */
@Entity
@Table(name = "t_pictures")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_pictures implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "image_url_small")
    private String imageUrlSmall;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "create_user")
    private String createUser;

    @Column(name = "image_type")
    private Integer imageType;

    @Column(name = "is_delete")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public T_pictures productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public T_pictures imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrlSmall() {
        return imageUrlSmall;
    }

    public T_pictures imageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
        return this;
    }

    public void setImageUrlSmall(String imageUrlSmall) {
        this.imageUrlSmall = imageUrlSmall;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public T_pictures createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public T_pictures createUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getImageType() {
        return imageType;
    }

    public T_pictures imageType(Integer imageType) {
        this.imageType = imageType;
        return this;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public T_pictures isDelete(Integer isDelete) {
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
        T_pictures t_pictures = (T_pictures) o;
        if (t_pictures.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_pictures.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_pictures{" +
            "id=" + getId() +
            ", productId='" + getProductId() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", imageUrlSmall='" + getImageUrlSmall() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            ", imageType='" + getImageType() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            "}";
    }
}
