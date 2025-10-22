package br.com.conversor.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Anotação para retornar 404 - NOT_FOUND
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ConversionRateNotFoundException extends RuntimeException {
    public ConversionRateNotFoundException(String message) {
        super(message);
    }
}