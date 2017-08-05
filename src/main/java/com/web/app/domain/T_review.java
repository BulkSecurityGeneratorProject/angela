package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A T_review.
 */
@Entity
@Table(name = "t_review")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_review implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "content")
    private String content;

    @Column(name = "createdate")
    private ZonedDateTime createdate;

    @Column(name = "is_useful")
    private Integer isUseful;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "is_delete")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public T_review userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public T_review productId(String productId) {
        this.productId = productId;
        return this;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getContent() {
        return content;
    }

    public T_review content(String content) {
        this.content = content;
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public T_review createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public Integer getIsUseful() {
        return isUseful;
    }

    public T_review isUseful(Integer isUseful) {
        this.isUseful = isUseful;
        return this;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
    }

    public String getRemarks() {
        return remarks;
    }

    public T_review remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public T_review isDelete(Integer isDelete) {
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
        T_review t_review = (T_review) o;
        if (t_review.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_review.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_review{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", productId='" + getProductId() + "'" +
            ", content='" + getContent() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", isUseful='" + getIsUseful() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            "}";
    }
}
