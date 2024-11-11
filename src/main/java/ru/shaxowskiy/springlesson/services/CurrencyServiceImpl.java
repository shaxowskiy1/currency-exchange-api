package ru.shaxowskiy.springlesson.services;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.springlesson.models.Currency;
import ru.shaxowskiy.springlesson.repositories.CurrencyRepository;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    private CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void createCurrency(Currency currency) {
        Currency newCurrency = setMeaningInCurrency(currency.getCode(), currency.getFullname(), currency.getSign());

        currencyRepository.create(newCurrency);
    }

    @Override
    public Currency findById(Integer id) {
        return currencyRepository.findById(id);
    }

    @Override
    public Currency findByName(String name) {
        return currencyRepository.findByName(name);
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        currencyRepository.delete(id);
    }

    @Override
    public void updateCurrency(Currency currency) {
        Currency newCurrency = setMeaningInCurrency(currency.getCode(), currency.getFullname(), currency.getSign());

        currencyRepository.update(newCurrency);
    }


    private static @NotNull Currency setMeaningInCurrency(String code, String fullname, String sign) {
        Currency currency = new Currency();
        currency.setCode(code);
        currency.setFullname(fullname);
        currency.setSign(sign);
        return currency;
    }
}
