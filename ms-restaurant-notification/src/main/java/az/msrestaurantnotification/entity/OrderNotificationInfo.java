package az.msrestaurantnotification.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_notification_info")
public class OrderNotificationInfo {
    @Id
    @SequenceGenerator(name = "order_notification_id",sequenceName = "order_notification_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_notification_id")
    private Long id;

    private Long userId;
    private Long notificationId;
    private Float totalAmount;
}
