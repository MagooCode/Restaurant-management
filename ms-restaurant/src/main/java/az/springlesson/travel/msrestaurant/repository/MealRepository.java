package az.springlesson.travel.msrestaurant.repository;

import az.springlesson.travel.msrestaurant.entity.MealEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends JpaRepository<MealEntity,Long> {
}
