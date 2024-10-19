package az.msrestaurantnotification.kafka;

import az.msrestaurantnotification.email.EmailService;
import az.msrestaurantnotification.entity.NotificationEntity;
import az.msrestaurantnotification.entity.OrderNotificationInfo;
import az.msrestaurantnotification.enums.NotificationType;
import az.msrestaurantnotification.events.OrderConfirmationEvent;
import az.msrestaurantnotification.repository.NotificationRepository;
import az.msrestaurantnotification.repository.OrderNotificationInfoRepository;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static az.msrestaurantnotification.enums.NotificationType.*;

@Component
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    private final OrderNotificationInfoRepository orderNotificationInfoRepository;

    @Autowired
    public NotificationConsumer(NotificationRepository notificationRepository, EmailService emailService
    , OrderNotificationInfoRepository orderNotificationInfoRepository) {
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
        this.orderNotificationInfoRepository = orderNotificationInfoRepository;
    }

    @KafkaListener(topics = "order-confirmation-topic")
    public void consumeOrderConfirmationTopic(OrderConfirmationEvent orderConfirmationEvent) throws MessagingException{
        NotificationEntity notificationEntity = notificationRepository.save(NotificationEntity.builder()
                .type(ORDER_CONFIRMATION)
                .notificationDate(LocalDateTime.now())
                .build()
        );

        orderNotificationInfoRepository.save(OrderNotificationInfo.builder()
                        .notificationId(notificationEntity.getId())
                        .userId(orderConfirmationEvent.getCustomerId())
                        .totalAmount(orderConfirmationEvent.getTotalAmount())
                        .build());

        emailService.sentOrderConfirmationEmail(orderConfirmationEvent);
    }
}
