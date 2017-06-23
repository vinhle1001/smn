package com.vinhle.smn.api.model.sql;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Entity
@Table(name = "smn_tele_provider")
public class SmnTeleProviderEntity implements Serializable {
    private Integer providerId;
    private String type;
    private String startWith;
    private Byte isActive;

    @Id
    @Column(name = "provider_id")
    public Integer getProviderId() {
        return providerId;
    }

    public void setProviderId(Integer providerId) {
        this.providerId = providerId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "start_with")
    public String getStartWith() {
        return startWith;
    }

    public void setStartWith(String startWith) {
        this.startWith = startWith;
    }

    @Basic
    @Column(name = "is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmnTeleProviderEntity that = (SmnTeleProviderEntity) o;

        if (providerId != null ? !providerId.equals(that.providerId) : that.providerId != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (startWith != null ? !startWith.equals(that.startWith) : that.startWith != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = providerId != null ? providerId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (startWith != null ? startWith.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
