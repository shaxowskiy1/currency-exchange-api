package ru.shaxowskiy.springlesson.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.shaxowskiy.springlesson.models.ExchangeRates;
import ru.shaxowskiy.springlesson.repositories.ExchangeRatesRepository;

import java.util.List;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService{

    private ExchangeRatesRepository exchangeRatesRepository;

    @Autowired
    public ExchangeRatesServiceImpl(ExchangeRatesRepository exchangeRatesRepository) {
        this.exchangeRatesRepository = exchangeRatesRepository;
    }

    public List<ExchangeRates> findAll(){
        return exchangeRatesRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        exchangeRatesRepository.delete(id);
    }

    @Override
    public void createExchangeRate(ExchangeRates exchangeRates) {
        ExchangeRates newExchangeRates = new ExchangeRates(exchangeRates.getBaseCurrency(), exchangeRates.getTargetCurrency(), exchangeRates.getRate());

        exchangeRatesRepository.create(newExchangeRates);
    }

    @Override
    public ExchangeRates findById(Integer id) {
        return exchangeRatesRepository.findById(id);
    }

    public ExchangeRates findByName(String name){
        return exchangeRatesRepository.findByName(name);
    }
}
