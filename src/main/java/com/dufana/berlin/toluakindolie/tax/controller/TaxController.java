package com.dufana.berlin.toluakindolie.tax.controller;

import com.dufana.berlin.toluakindolie.tax.entity.TaxBracket;
import com.dufana.berlin.toluakindolie.tax.repository.TaxBracketRepository;
import com.dufana.berlin.toluakindolie.tax.service.TaxCalculationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tax")
public class TaxController {
    private final TaxCalculationService taxCalculationService;
    private final TaxBracketRepository taxBracketRepository;

    public TaxController(TaxCalculationService taxCalculationService, TaxBracketRepository taxBracketRepository) {
        this.taxCalculationService = taxCalculationService;
        this.taxBracketRepository = taxBracketRepository;
    }

    @GetMapping("/calculate")
    public double calculateTax(@RequestParam double income) {
        List<TaxBracket> taxBrackets = taxBracketRepository.findAll();
        double tax = 0;
        for (TaxBracket bracket : taxBrackets) {
            if (income > bracket.getLowerBound()) {
                double taxableIncome = Math.min(income, bracket.getUpperBound() - bracket.getLowerBound());
                tax += taxableIncome * bracket.getRate();
                System.out.println("Bracket: " + bracket);
                System.out.println("Taxable Income: " + taxableIncome);
                System.out.println("Bracket Tax: " + tax);
            }
        }return tax;
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }
}
