package com.dufana.berlin.toluakindolie.tax.service;

import com.dufana.berlin.toluakindolie.tax.entity.TaxBracket;
import com.dufana.berlin.toluakindolie.tax.repository.TaxBracketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxCalculationServiceTest {
    private TaxBracketRepository taxBracketRepository;
    private TaxCalculationService taxCalculationService;

    @BeforeEach
    void setup(){
        taxBracketRepository = Mockito.mock(TaxBracketRepository.class);
        taxCalculationService = new TaxCalculationService(taxBracketRepository);

    }

    @Test
    void testCalculateTax(){
        Mockito.when(taxBracketRepository.findAll()).thenReturn(Arrays.asList(
                new TaxBracket(1L, 0, 10000, 0.1),
                new TaxBracket(2L, 10000, 20000, 0.2),
                new TaxBracket(3L, 20000, Double.MAX_VALUE, 0.3)
        ));
        double tax = taxCalculationService.calculateTax(2500);
        assertEquals(250.0, tax, 0.01);

        System.out.println("Calculated Tax: " + tax);
    }
}
