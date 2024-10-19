package az.msrestaurantauth.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtResponse {
    private String token;
}
