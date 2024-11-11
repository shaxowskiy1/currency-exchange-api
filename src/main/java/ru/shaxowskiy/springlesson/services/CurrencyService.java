package ru.shaxowskiy.springlesson.services;

import ru.shaxowskiy.springlesson.models.Currency;

import java.util.List;

public interface CurrencyService {
    void createCurrency(Currency currency);
    Currency findById(Integer id);
    Currency findByName(String name);
    List<Currency> findAll();
    void deleteById(Integer id);
    void updateCurrency(Currency currency);
}
