package com.vinhle.smn.api.model.sql;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Entity
@Table(name = "smn_internal_transfer")
@DynamicInsert
public class SmnInternalTransferEntity implements Serializable {
    private Integer internalTransferId;
    private Integer agencyFromId;
    private Integer agencyToId;
    private String description;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Byte isActive;

    @Id
    @Column(name = "internal_transfer_id")
    @GeneratedValue
    public Integer getInternalTransferId() {
        return internalTransferId;
    }

    public void setInternalTransferId(Integer internalTransferId) {
        this.internalTransferId = internalTransferId;
    }

    @Basic
    @Column(name = "agency_from_id")
    public Integer getAgencyFromId() {
        return agencyFromId;
    }

    public void setAgencyFromId(Integer agencyFromId) {
        this.agencyFromId = agencyFromId;
    }

    @Basic
    @Column(name = "agency_to_id")
    public Integer getAgencyToId() {
        return agencyToId;
    }

    public void setAgencyToId(Integer agencyToId) {
        this.agencyToId = agencyToId;
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
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_by")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "modified_date")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
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

        SmnInternalTransferEntity that = (SmnInternalTransferEntity) o;

        if (internalTransferId != null ? !internalTransferId.equals(that.internalTransferId) : that.internalTransferId != null)
            return false;
        if (agencyFromId != null ? !agencyFromId.equals(that.agencyFromId) : that.agencyFromId != null) return false;
        if (agencyToId != null ? !agencyToId.equals(that.agencyToId) : that.agencyToId != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = internalTransferId != null ? internalTransferId.hashCode() : 0;
        result = 31 * result + (agencyFromId != null ? agencyFromId.hashCode() : 0);
        result = 31 * result + (agencyToId != null ? agencyToId.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
