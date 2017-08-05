package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A T_case_info.
 */
@Entity
@Table(name = "t_case_info")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_case_info implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "brief")
    private String brief;

    @Column(name = "description")
    private String description;

    @Column(name = "createdate")
    private ZonedDateTime createdate;

    @Column(name = "is_useful")
    private Integer isUseful;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "is_delete")
    private Integer isDelete;

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

    public String getTitle() {
        return title;
    }

    public T_case_info title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public T_case_info brief(String brief) {
        this.brief = brief;
        return this;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getDescription() {
        return description;
    }

    public T_case_info description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getCreatedate() {
        return createdate;
    }

    public T_case_info createdate(ZonedDateTime createdate) {
        this.createdate = createdate;
        return this;
    }

    public void setCreatedate(ZonedDateTime createdate) {
        this.createdate = createdate;
    }

    public Integer getIsUseful() {
        return isUseful;
    }

    public T_case_info isUseful(Integer isUseful) {
        this.isUseful = isUseful;
        return this;
    }

    public void setIsUseful(Integer isUseful) {
        this.isUseful = isUseful;
    }

    public String getRemarks() {
        return remarks;
    }

    public T_case_info remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public T_case_info isDelete(Integer isDelete) {
        this.isDelete = isDelete;
        return this;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public T_case_info createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public T_case_info createUser(String createUser) {
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
        T_case_info t_case_info = (T_case_info) o;
        if (t_case_info.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_case_info.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_case_info{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", brief='" + getBrief() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdate='" + getCreatedate() + "'" +
            ", isUseful='" + getIsUseful() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createUser='" + getCreateUser() + "'" +
            "}";
    }
}
