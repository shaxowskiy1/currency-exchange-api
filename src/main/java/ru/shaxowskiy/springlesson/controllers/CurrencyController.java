package ru.shaxowskiy.springlesson.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.shaxowskiy.springlesson.models.Currency;
import ru.shaxowskiy.springlesson.services.CurrencyServiceImpl;

import java.util.List;

@RestController
public class CurrencyController {

    private CurrencyServiceImpl currencyService;

    @Autowired
    public CurrencyController(CurrencyServiceImpl currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currencies")
    public List<Currency> findAll(){
        return currencyService.findAll();
    }

    @GetMapping("/currency/{id}")
    public Currency findById(@PathVariable("id") int id){
        return currencyService.findById(id);
    }

    @GetMapping("/currency")
    public Currency findByName(@RequestParam("name") String name){
        return currencyService.findByName(name);
    }

    @PostMapping("/currencies")
    public void postCurrency(@RequestBody Currency currency){
        currencyService.createCurrency(currency);
    }

    @PatchMapping("/currencies/{id}")
    public void updateCurrency(@RequestBody Currency currency,
                               @PathVariable("id") Integer id){
        System.out.println("Обнова в контроллере " + currency.toString() + " " + id);
        currencyService.updateCurrency(currency, id);
    }
    @DeleteMapping("/currencies/{id}")
    public void deleteCurrency(@PathVariable("id") Integer id){
        currencyService.deleteById(id);
    }
}
