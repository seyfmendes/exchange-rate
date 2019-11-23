package com.openpayd.rate.model.dto;

import java.io.Serializable;

public class QueryModel implements Serializable {

    String from;
    String to;
    double amount;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "QueryModel{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
}
