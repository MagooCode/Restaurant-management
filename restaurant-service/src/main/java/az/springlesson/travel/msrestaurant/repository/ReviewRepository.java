package az.springlesson.travel.msrestaurant.repository;

import az.springlesson.travel.msrestaurant.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {

}
