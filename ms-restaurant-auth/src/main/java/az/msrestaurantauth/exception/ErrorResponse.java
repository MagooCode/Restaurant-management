package az.msrestaurantauth.exception;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private String message;
}
