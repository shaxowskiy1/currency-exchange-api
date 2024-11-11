package ru.shaxowskiy.springlesson.controllers;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.springlesson.dto.ExchangeDTO;
import ru.shaxowskiy.springlesson.models.Currency;
import ru.shaxowskiy.springlesson.models.ExchangeRates;
import ru.shaxowskiy.springlesson.services.ExchangeRatesServiceImpl;
import ru.shaxowskiy.springlesson.util.MappingDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@RestController
public class ExchangeRatesController {

    private ExchangeRatesServiceImpl exchangeRatesService;

    public ExchangeRatesController(ExchangeRatesServiceImpl exchangeRatesService) {
        this.exchangeRatesService = exchangeRatesService;
    }

    @GetMapping("/exchangeRates")
    public List<ExchangeRates> findAll(){
        return exchangeRatesService.findAll();
    }

    @GetMapping("/exchangeRate")
    public ExchangeRates findByName(@RequestParam("name") String name){
        return exchangeRatesService.findByName(name);
    }

    @GetMapping("/exchangeRate/{id}")
    public ExchangeRates findById(@PathVariable("id") Integer id){
        return exchangeRatesService.findById(id);
    }

    @PostMapping("/exchangeRates")
    public void postExchangeRate(@RequestBody ExchangeRates exchangeRates){
        exchangeRatesService.createExchangeRate(exchangeRates);
    }

    @DeleteMapping("/exchangeRates/{id}")
    public void deleteExchangeRate(@PathVariable("id") Integer id){
        exchangeRatesService.deleteById(id);
    }

    @GetMapping("/exchange")
    public ExchangeDTO findByName(@RequestParam("from") String from,
                                  @RequestParam("to") String to,
                                  @RequestParam("amount") BigDecimal amount){
        ExchangeRates exchangeRatesByName = exchangeRatesService.findByName(from + to);
        ExchangeDTO exchangeDTO = MappingDTO.mapToExchangeDTO(exchangeRatesByName);
        exchangeDTO.setAmount(amount);
        exchangeDTO.setConvertedAmount(amount.multiply(exchangeDTO.getRate()).setScale(2, RoundingMode.HALF_UP));

        return exchangeDTO;
    }
}
