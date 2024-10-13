package com.spring.msorder.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

    @Override
    public void initialize(ValidUrl constraintAnnotation) {
        // Initialization code if needed
    }

    @Override
    public boolean isValid(String url, ConstraintValidatorContext context) {
        if (url == null || url.isEmpty()) {
            return true;
        }
        try {
            new URL(url).toURI();
            return true;
        } catch (MalformedURLException | IllegalArgumentException | URISyntaxException e) {
            return false;
        }
    }
}
