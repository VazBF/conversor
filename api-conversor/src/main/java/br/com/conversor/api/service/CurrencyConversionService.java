package br.com.conversor.api.service;

import br.com.conversor.api.exception.ConversionRateNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class CurrencyConversionService {

    private static final Map<String, Double> exchangeRatesToUsd = Map.of(
        "USD", 1.0,
        "BRL", 5.25, // 1 USD = 5.25 BRL
        "EUR", 0.92  // 1 USD = 0.92 EUR
    );

    public double convert(String from, String to, double amount) {

        Double fromRate = exchangeRatesToUsd.get(from.toUpperCase());
        Double toRate = exchangeRatesToUsd.get(to.toUpperCase());

        if (fromRate == null) {
            throw new ConversionRateNotFoundException("Taxa de câmbio não encontrada para a moeda: " + from);
        }
        if (toRate == null) {
            throw new ConversionRateNotFoundException("Taxa de câmbio não encontrada para a moeda: " + to);
        }

        double amountInUsd = amount / fromRate;
        
        return amountInUsd * toRate;
    }
}