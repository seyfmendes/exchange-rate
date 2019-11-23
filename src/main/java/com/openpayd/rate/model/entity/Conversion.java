package com.openpayd.rate.model.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "conversion")
public class Conversion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_conversion")
    @SequenceGenerator(name = "seq_conversion", sequenceName = "seq_conversion", allocationSize = 1)
    @Column(name = "transaction_id")
    Long transactionId;

    @Column(name = "source_currency")
    String sourceCurrency;
    @Column(name = "target_currency")
    String targetCurrency;
    @Column(name = "source_amount", precision = 19, scale = 4)
    double sourceAmount;
    @Column(name = "target_amount", precision = 19, scale = 4)
    double targetAmount;
    @Column(name = "transaction_date")
    LocalDate transactionDate;

    public Conversion() {
    }

    public Conversion(String sourceCurrency, String targetCurrency, double sourceAmount, double targetAmount, LocalDate transactionDate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.sourceAmount = sourceAmount;
        this.targetAmount = targetAmount;
        this.transactionDate = transactionDate;
    }

    public String getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(String sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }
}
