package com.openpayd.rate.model.dto;

import java.io.Serializable;

public class ConversionResultModel implements Serializable {

    boolean success;
    QueryModel query;
    InfoModel info;
    boolean historical;
    String date;
    double result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public QueryModel getQuery() {
        return query;
    }

    public void setQuery(QueryModel query) {
        this.query = query;
    }

    public InfoModel getInfo() {
        return info;
    }

    public void setInfo(InfoModel info) {
        this.info = info;
    }

    public boolean isHistorical() {
        return historical;
    }

    public void setHistorical(boolean historical) {
        this.historical = historical;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "ConversionResultModel{" +
                "success=" + success +
                ", query=" + query +
                ", info=" + info +
                ", historical=" + historical +
                ", date='" + date + '\'' +
                ", result=" + result +
                '}';
    }
}
