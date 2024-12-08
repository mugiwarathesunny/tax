package com.dufana.berlin.toluakindolie.tax.controller;

import com.dufana.berlin.toluakindolie.tax.service.TaxCalculationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax")
public class TaxController {
    private final TaxCalculationService taxCalculationService;

    public TaxController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @GetMapping("/calculate")
    public double caculateTax(@RequestParam double income){
        return taxCalculationService.calculateTax(income);
    }
}
