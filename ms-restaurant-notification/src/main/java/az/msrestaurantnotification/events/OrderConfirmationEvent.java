package az.msrestaurantnotification.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmationEvent {
    private Long customerId;
    private String customerName;
    private String customerMail;
    private Float totalAmount;
}
