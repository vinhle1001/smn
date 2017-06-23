package com.vinhle.smn.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * Created by VinhLe on 5/24/2017.
 */

public class RangeDateRequest extends BaseRequest {

    private Timestamp timeStart;
    private Timestamp timeEnd;

    @JsonProperty("time_start")
    public Timestamp getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Timestamp timeStart) {
        this.timeStart = timeStart;
    }

    @JsonProperty("time_end")
    public Timestamp getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Timestamp timeEnd) {
        this.timeEnd = timeEnd;
    }
}
