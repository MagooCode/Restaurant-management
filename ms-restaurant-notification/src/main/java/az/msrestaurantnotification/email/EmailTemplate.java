package az.msrestaurantnotification.email;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {
    ORDER_CONFIRMATION("order-confirmation.html");

    private final String template;
}
