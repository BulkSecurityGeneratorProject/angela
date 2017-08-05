package com.web.app.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A T_dictionary.
 */
@Entity
@Table(name = "t_dictionary")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class T_dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dict_id")
    private String dictId;

    @Column(name = "dict_name")
    private String dictName;

    @Column(name = "dict_key")
    private String dictKey;

    @Column(name = "dict_val")
    private String dictVal;

    @Column(name = "dict_des")
    private String dictDes;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Column(name = "is_delete")
    private Integer isDelete;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDictId() {
        return dictId;
    }

    public T_dictionary dictId(String dictId) {
        this.dictId = dictId;
        return this;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public T_dictionary dictName(String dictName) {
        this.dictName = dictName;
        return this;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictKey() {
        return dictKey;
    }

    public T_dictionary dictKey(String dictKey) {
        this.dictKey = dictKey;
        return this;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictVal() {
        return dictVal;
    }

    public T_dictionary dictVal(String dictVal) {
        this.dictVal = dictVal;
        return this;
    }

    public void setDictVal(String dictVal) {
        this.dictVal = dictVal;
    }

    public String getDictDes() {
        return dictDes;
    }

    public T_dictionary dictDes(String dictDes) {
        this.dictDes = dictDes;
        return this;
    }

    public void setDictDes(String dictDes) {
        this.dictDes = dictDes;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public T_dictionary createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public T_dictionary isDelete(Integer isDelete) {
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
        T_dictionary t_dictionary = (T_dictionary) o;
        if (t_dictionary.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), t_dictionary.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "T_dictionary{" +
            "id=" + getId() +
            ", dictId='" + getDictId() + "'" +
            ", dictName='" + getDictName() + "'" +
            ", dictKey='" + getDictKey() + "'" +
            ", dictVal='" + getDictVal() + "'" +
            ", dictDes='" + getDictDes() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", isDelete='" + getIsDelete() + "'" +
            "}";
    }
}
