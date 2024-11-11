package ru.shaxowskiy.springlesson.util;

import ru.shaxowskiy.springlesson.dto.ExchangeDTO;
import ru.shaxowskiy.springlesson.models.ExchangeRates;

public class MappingDTO {

    public static ExchangeDTO mapToExchangeDTO(ExchangeRates exchangeRates){
        ExchangeDTO exchangeDTO = new ExchangeDTO();
        exchangeDTO.setId(exchangeDTO.getId());
        exchangeDTO.setBaseCurrency(exchangeRates.getBaseCurrency());
        exchangeDTO.setTargetCurrency(exchangeRates.getTargetCurrency());
        exchangeDTO.setRate(exchangeRates.getRate());

        return exchangeDTO;
    }

    public static ExchangeRates mapToExchangeRatesEntity(ExchangeDTO exchangeDTO){
        ExchangeRates exchangeRates = new ExchangeRates();
        exchangeRates.setId(exchangeDTO.getId());
        exchangeRates.setBaseCurrency(exchangeRates.getBaseCurrency());
        exchangeRates.setTargetCurrency(exchangeRates.getTargetCurrency());
        exchangeRates.setRate(exchangeRates.getRate());

        return exchangeRates;
    }
}
