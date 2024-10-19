package az.msrestaurantnotification.repository;

import az.msrestaurantnotification.entity.OrderNotificationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderNotificationInfoRepository extends JpaRepository<OrderNotificationInfo, Long> {
}
