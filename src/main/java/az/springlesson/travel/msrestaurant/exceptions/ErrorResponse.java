package az.springlesson.travel.msrestaurant.exceptions;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class ErrorResponse {
    private String message;
    private List<String> errors;
}
