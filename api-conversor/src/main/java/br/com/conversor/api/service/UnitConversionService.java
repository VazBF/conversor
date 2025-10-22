package br.com.conversor.api.service;

import org.springframework.stereotype.Service;

@Service
public class UnitConversionService {

    private static final double KM_TO_MILE_FACTOR = 0.621371;
    
    public double convertTemperature(String from, String to, double value) {
        if (from.equalsIgnoreCase("C") && to.equalsIgnoreCase("F")) {
            // Celsius para Fahrenheit
            return (value * 9/5) + 32;
        } else if (from.equalsIgnoreCase("F") && to.equalsIgnoreCase("C")) {
            // Fahrenheit para Celsius
            return (value - 32) * 5/9;
        }
        return value;
    }

    public double convertDistance(String from, String to, double value) {
        if (from.equalsIgnoreCase("km") && to.equalsIgnoreCase("mi")) {
            // Quilômetros para Milhas
            return value * KM_TO_MILE_FACTOR;
        } else if (from.equalsIgnoreCase("mi") && to.equalsIgnoreCase("km")) {
            // Milhas para Quilômetros
            return value / KM_TO_MILE_FACTOR;
        }
        return value;
    }
}