package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A T_category.
 */
@Entity
@Table(name = "t_category")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "depth")
    private Integer depth;

    @Column(name = "status")
    private Integer status;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "is_delete")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatName() {
        return catName;
    }

    public T_category catName(String catName) {
        this.catName = catName;
        return this;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getParentId() {
        return parentId;
    }

    public T_category parentId(Integer parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getDepth() {
        return depth;
    }

    public T_category depth(Integer depth) {
        this.depth = depth;
        return this;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Integer getStatus() {
        return status;
    }

    public T_category status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public T_category priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public T_category isDelete(Integer isDelete) {
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
        T_category t_category = (T_category) o;
        if (t_category.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_category{" +
            "id=" + getId() +
            ", catName='" + getCatName() + "'" +
            ", parentId='" + getParentId() + "'" +
            ", depth='" + getDepth() + "'" +
            ", status='" + getStatus() + "'" +
            ", priority='" + getPriority() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            "}";
    }
}
