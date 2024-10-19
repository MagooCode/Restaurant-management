package az.msrestaurantnotification.entity;

import az.msrestaurantnotification.enums.NotificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class NotificationEntity {
    @Id
    @SequenceGenerator(name = "notification_id", sequenceName = "notification_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id")
    private Long id;

    private NotificationType type;
    private LocalDateTime notificationDate;

}
