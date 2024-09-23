package az.springlesson.travel.msuserservice.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;


@Entity
@Data
@RequiredArgsConstructor
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String firstName;
    private String lastName;
    @Column(unique = true)  //???
    private String email;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    @ManyToMany(fetch = FetchType.EAGER)  //???
    private List<Role> roles;

}
