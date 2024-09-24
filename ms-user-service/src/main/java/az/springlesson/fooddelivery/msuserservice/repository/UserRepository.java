package az.springlesson.fooddelivery.msuserservice.repository;

import az.springlesson.fooddelivery.msuserservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
