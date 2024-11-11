package ru.shaxowskiy.springlesson.dto;

import ru.shaxowskiy.springlesson.models.Currency;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ExchangeDTO {

    private int id;

    private BigDecimal rate;

    private Currency baseCurrency;

    private Currency targetCurrency;

    private BigDecimal amount;

    private BigDecimal convertedAmount;

    public ExchangeDTO() {
    }

    public ExchangeDTO(Currency baseCurrency, Currency targetCurrency, BigDecimal rate) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getConvertedAmount() {
        convertedAmount = amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
        return convertedAmount;
    }

    public void setConvertedAmount(BigDecimal convertedAmount) {
        this.convertedAmount = convertedAmount;
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
