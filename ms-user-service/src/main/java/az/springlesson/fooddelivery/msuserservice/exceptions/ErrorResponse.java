package az.springlesson.fooddelivery.msuserservice.exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private String message;
    private  Integer status;
}
