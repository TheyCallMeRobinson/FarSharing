package vsu5_12.farsharing.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user_role")
public class UserRoleEntity {

    @Id
    private String name;

    @OneToMany(mappedBy = "role")
    @ToString.Exclude
    private List<UserEntity> userEntity;
}
