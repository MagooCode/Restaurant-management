package com.spring.msorder.events;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class OrderConfirmationEvent {
    private Long customerId;
    private String customerName;
    private String customerMail;
    private Float totalAmount;
}
