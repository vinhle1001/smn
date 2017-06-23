package com.vinhle.smn.api.model.sql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Entity
@Table(name = "smn_account_type")
public class SmnAccountTypeEntity implements Serializable {
    private Byte accountTypeId;
    private String name;
    private String description;

    @Id
    @Column(name = "account_type_id")
    public Byte getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(Byte accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmnAccountTypeEntity that = (SmnAccountTypeEntity) o;

        if (accountTypeId != null ? !accountTypeId.equals(that.accountTypeId) : that.accountTypeId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = accountTypeId != null ? accountTypeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
