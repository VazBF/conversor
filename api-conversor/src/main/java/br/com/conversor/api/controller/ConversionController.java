package br.com.conversor.api.controller;

import br.com.conversor.api.service.CurrencyConversionService;
import br.com.conversor.api.service.UnitConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/convert")
@Validated
public class ConversionController {

    @Autowired
    private CurrencyConversionService currencyService;

    @Autowired
    private UnitConversionService unitService;

    // Endpoint: GET /convert/currency?from=USD&to=BRL&amount=100
    @GetMapping("/currency")
    public double convertCurrency(
        @RequestParam 
        @Pattern(regexp = "USD|BRL|EUR", message = "Moeda 'from' não suportada. Use: USD, BRL ou EUR.")
        String from,
        
        @RequestParam 
        @Pattern(regexp = "USD|BRL|EUR", message = "Moeda 'to' não suportada. Use: USD, BRL ou EUR.")
        String to,
        
        @RequestParam 
        @Min(value = 0, message = "O valor 'amount' não pode ser negativo.")
        @Max(value = 1000000, message = "O valor 'amount' excede o limite máximo de 1.000.000.")
        double amount
    ) {
        return currencyService.convert(from, to, amount);
    }

    // Endpoint: GET /convert/unit/temperature?from=C&to=F&value=37
    @GetMapping("/unit/temperature")
    public double convertTemperature(
        @RequestParam 
        @Pattern(regexp = "C|F", message = "Unidade 'from' não suportada. Use: C ou F.")
        String from,
        
        @RequestParam 
        @Pattern(regexp = "C|F", message = "Unidade 'to' não suportada. Use: C ou F.")
        String to,
        
        @RequestParam 
        @Min(value = -273, message = "Valor abaixo do zero absoluto.") // Validação de limite mínimo
        double value
    ) {
        return unitService.convertTemperature(from, to, value);
    }

    // Endpoint Bônus: GET /convert/unit/distance?from=km&to=mi&value=10
     @GetMapping("/unit/distance")
    public double convertDistance(
        @RequestParam 
        @Pattern(regexp = "km|mi", message = "Unidade 'from' não suportada. Use: km ou mi.")
        String from,
        
        @RequestParam 
        @Pattern(regexp = "km|mi", message = "Unidade 'to' não suportada. Use: km ou mi.")
        String to,
        
        @RequestParam 
        @Min(value = 0, message = "A distância não pode ser negativa.")
        double value
    ) {
        return unitService.convertDistance(from, to, value);
    }
}