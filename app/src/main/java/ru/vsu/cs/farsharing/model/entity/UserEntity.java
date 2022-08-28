package ru.vsu.cs.farsharing.model.entity;

import java.util.UUID;

import ru.vsu.cs.farsharing.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
    UUID uid;
    String email;
    String password;
    Role role;
    Integer activationCode;
}
