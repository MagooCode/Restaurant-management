package az.springlesson.fooddelivery.msuserservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "roles")
public class RoleEntity {
    @Id
    @SequenceGenerator(name = "role_id" , sequenceName = "role_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "role_id")
    private Long roleId;
    private String roleName;
    private String description;

    @OneToMany(mappedBy = "role")
    private List<UserEntity> userEntities;
}
