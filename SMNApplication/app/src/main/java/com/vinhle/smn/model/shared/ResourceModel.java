package com.vinhle.smn.model.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinhle.searchablespinner.BaseSearchableModel;

/**
 * Created by VinhLe on 5/12/2017.
 */
// Default use for province, ward, distinct
public class ResourceModel extends BaseSearchableModel {

    private Integer id;
    private String name;
    private String type;
    private String sign;
    private String icon;

    @Override
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("sign")
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @JsonProperty("icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    @Override
    @JsonIgnore
    public String getDisplayText() {
        if (type == null || type.isEmpty()) return name;
        return type + " " + name;
    }

    @Override
    @JsonIgnore
    public String getSubTitle() {
        return null;
    }

    @Override
    public String toString() {
        return "ResourceModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", sign='" + sign + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    /*----------------------------------- Constructor $Setting ---------------------------------------------*/
    public ResourceModel() {
        this(null);
    }

    public ResourceModel(Integer id) {
        this(id, (String) null);
    }

    public ResourceModel(Integer id, String name) {
        this(id, name, (String) null);
    }

    public ResourceModel(Integer id, String name, String type) {
        this(id, name, type, (String) null, (String) null);
    }

    public ResourceModel(Integer id, String name, String type, String sign) {
        this(id, name, type, sign, (String) null);
    }

    public ResourceModel(Integer id, String name, String type, String sign, String icon) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sign = sign;
        this.icon = icon;
    }
}
