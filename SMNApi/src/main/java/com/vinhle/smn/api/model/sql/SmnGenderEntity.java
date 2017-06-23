package com.vinhle.smn.api.model.sql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Entity
@Table(name = "smn_gender")
public class SmnGenderEntity implements Serializable {
    private Byte genderId;
    private String description;
    private String name;

    @Id
    @Column(name = "gender_id")
    public Byte getGenderId() {
        return genderId;
    }

    public void setGenderId(Byte genderId) {
        this.genderId = genderId;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmnGenderEntity that = (SmnGenderEntity) o;

        if (genderId != null ? !genderId.equals(that.genderId) : that.genderId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genderId != null ? genderId.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
