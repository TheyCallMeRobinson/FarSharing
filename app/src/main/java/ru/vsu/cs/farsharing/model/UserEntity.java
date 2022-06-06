package ru.vsu.cs.farsharing.model;

import ru.vsu.cs.farsharing.model.enums.Role;

public class UserEntity {
    String uid;
    String email;
    String password;
    Role role;
    Integer activationCode;
}
