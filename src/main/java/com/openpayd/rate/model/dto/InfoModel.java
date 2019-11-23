package com.openpayd.rate.model.dto;

import java.io.Serializable;

public class InfoModel implements Serializable {
    Long timestamp;
    double rate;

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "InfoModel{" +
                "timestamp=" + timestamp +
                ", rate=" + rate +
                '}';
    }
}
