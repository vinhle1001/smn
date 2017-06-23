package com.vinhle.smn.api.model.sql;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 6/9/2017.
 */
@Entity
@Table(name = "smn_import")
@DynamicInsert
public class SmnImportEntity implements Serializable{
    private Integer importId;
    private Integer supplierId;
    private Integer agencyId;
    private Long importCost;
    private Long importDebt;
    private String description;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Byte isActive;

    @Id
    @Column(name = "import_id")
    @GeneratedValue
    public Integer getImportId() {
        return importId;
    }

    public void setImportId(Integer importId) {
        this.importId = importId;
    }

    @Basic
    @Column(name = "supplier_id")
    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Basic
    @Column(name = "agency_id")
    public Integer getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    @Basic
    @Column(name = "import_cost")
    public Long getImportCost() {
        return importCost;
    }

    public void setImportCost(Long importCost) {
        this.importCost = importCost;
    }

    @Basic
    @Column(name = "import_debt")
    public Long getImportDebt() {
        return importDebt;
    }

    public void setImportDebt(Long importDebt) {
        this.importDebt = importDebt;
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

        SmnImportEntity that = (SmnImportEntity) o;

        if (importId != null ? !importId.equals(that.importId) : that.importId != null) return false;
        if (supplierId != null ? !supplierId.equals(that.supplierId) : that.supplierId != null) return false;
        if (agencyId != null ? !agencyId.equals(that.agencyId) : that.agencyId != null) return false;
        if (importCost != null ? !importCost.equals(that.importCost) : that.importCost != null) return false;
        if (importDebt != null ? !importDebt.equals(that.importDebt) : that.importDebt != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = importId != null ? importId.hashCode() : 0;
        result = 31 * result + (supplierId != null ? supplierId.hashCode() : 0);
        result = 31 * result + (agencyId != null ? agencyId.hashCode() : 0);
        result = 31 * result + (importCost != null ? importCost.hashCode() : 0);
        result = 31 * result + (importDebt != null ? importDebt.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        return result;
    }
}
