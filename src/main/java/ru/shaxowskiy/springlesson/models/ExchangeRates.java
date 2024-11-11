package ru.shaxowskiy.springlesson.models;


import java.math.BigDecimal;

public class ExchangeRates {

    private int id;

    private BigDecimal rate;

    private Currency baseCurrency;

    private Currency targetCurrency;

    public ExchangeRates() {
    }

    public ExchangeRates(Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "id=" + id +
                ", rate=" + rate +
                ", baseCurrency=" + baseCurrency +
                ", targetCurrency=" + targetCurrency +
                '}';
    }
}
