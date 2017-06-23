package com.vinhle.smn.api.model.sql;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 6/3/2017.
 */
@Entity
@Table(name = "smn_log")
@DynamicInsert
public class SmnLogEntity implements Serializable {
    private Integer logId;
    private String logType;
    private String logSource;
    private String logUrl;
    private String deviceId;
    private String contentRequest;
    private String contentResponse;
    private Float timeSpan;
    private String description;
    private Integer createdBy;
    private Timestamp createdDate;

    @Id
    @Column(name = "log_id")
    @GeneratedValue
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    @Basic
    @Column(name = "log_type")
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Basic
    @Column(name = "log_source")
    public String getLogSource() {
        return logSource;
    }

    public void setLogSource(String logSource) {
        this.logSource = logSource;
    }

    @Basic
    @Column(name = "log_url")
    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl;
    }

    @Basic
    @Column(name = "device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Basic
    @Column(name = "content_request")
    public String getContentRequest() {
        return contentRequest;
    }

    public void setContentRequest(String contentRequest) {
        this.contentRequest = contentRequest;
    }

    @Basic
    @Column(name = "content_response")
    public String getContentResponse() {
        return contentResponse;
    }

    public void setContentResponse(String contentResponse) {
        this.contentResponse = contentResponse;
    }

    @Basic
    @Column(name = "time_span")
    public Float getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(Float timeSpan) {
        this.timeSpan = timeSpan;
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
    @Column(name = "created_by")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmnLogEntity that = (SmnLogEntity) o;

        if (logId != null ? !logId.equals(that.logId) : that.logId != null) return false;
        if (logType != null ? !logType.equals(that.logType) : that.logType != null) return false;
        if (logSource != null ? !logSource.equals(that.logSource) : that.logSource != null) return false;
        if (logUrl != null ? !logUrl.equals(that.logUrl) : that.logUrl != null) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        if (contentRequest != null ? !contentRequest.equals(that.contentRequest) : that.contentRequest != null)
            return false;
        if (contentResponse != null ? !contentResponse.equals(that.contentResponse) : that.contentResponse != null)
            return false;
        if (timeSpan != null ? !timeSpan.equals(that.timeSpan) : that.timeSpan != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logId != null ? logId.hashCode() : 0;
        result = 31 * result + (logType != null ? logType.hashCode() : 0);
        result = 31 * result + (logSource != null ? logSource.hashCode() : 0);
        result = 31 * result + (logUrl != null ? logUrl.hashCode() : 0);
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (contentRequest != null ? contentRequest.hashCode() : 0);
        result = 31 * result + (contentResponse != null ? contentResponse.hashCode() : 0);
        result = 31 * result + (timeSpan != null ? timeSpan.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        return result;
    }
}
