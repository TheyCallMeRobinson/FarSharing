package vsu5_12.farsharing.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_", uniqueConstraints = @UniqueConstraint(
        name = "email_un",
        columnNames = "email"
))
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false, insertable = false)
    private UUID uid;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_name", referencedColumnName = "name", nullable = false)
    @ToString.Exclude
    private UserRoleEntity role;

}
