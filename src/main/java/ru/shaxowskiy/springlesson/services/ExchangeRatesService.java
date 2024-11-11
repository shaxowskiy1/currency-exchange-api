package ru.shaxowskiy.springlesson.services;

import ru.shaxowskiy.springlesson.models.ExchangeRates;

import java.util.List;

public interface ExchangeRatesService {
    void createExchangeRate(ExchangeRates exchangeRates);
    ExchangeRates findById(Integer id);
    ExchangeRates findByName(String name);
    List<ExchangeRates> findAll();
    void deleteById(Integer id);
}
