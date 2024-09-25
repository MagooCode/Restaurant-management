package az.springlesson.travel.msrestaurant.repository;

import az.springlesson.travel.msrestaurant.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
