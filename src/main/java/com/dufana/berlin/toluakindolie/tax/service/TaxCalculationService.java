package com.dufana.berlin.toluakindolie.tax.service;

import com.dufana.berlin.toluakindolie.tax.entity.TaxBracket;
import com.dufana.berlin.toluakindolie.tax.repository.TaxBracketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxCalculationService {
    private final TaxBracketRepository taxBracketRepository;

    public TaxCalculationService(TaxBracketRepository taxBracketRepository) {
        this.taxBracketRepository = taxBracketRepository;
    }

    public double calculateTax(double income) {
        List<TaxBracket> taxBrackets = taxBracketRepository.findAll();
        double tax = 0;

        for (TaxBracket bracket : taxBrackets){
            if (income > bracket.getLowerBound()){
                double taxableIncome = Math.min(income, bracket.getUpperBound() - bracket.getLowerBound());
                tax += taxableIncome * bracket.getRate();
                System.out.println("Bracket: " + bracket);
                System.out.println("Taxable Income: " + taxableIncome);
                System.out.println("Bracket Tax: " + tax);
            }
        }
        return tax;
    }

}
