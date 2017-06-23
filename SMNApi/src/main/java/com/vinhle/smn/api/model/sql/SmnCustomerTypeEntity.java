package com.vinhle.smn.api.model.sql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Entity
@Table(name = "smn_customer_type")
public class SmnCustomerTypeEntity implements Serializable {
    private Byte customerTypeId;
    private String name;
    private String description;

    @Id
    @Column(name = "customer_type_id")
    public Byte getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Byte customerTypeId) {
        this.customerTypeId = customerTypeId;
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

        SmnCustomerTypeEntity that = (SmnCustomerTypeEntity) o;

        if (customerTypeId != null ? !customerTypeId.equals(that.customerTypeId) : that.customerTypeId != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerTypeId != null ? customerTypeId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
