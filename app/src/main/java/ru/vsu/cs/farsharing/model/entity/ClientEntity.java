package ru.vsu.cs.farsharing.model.entity;

import java.util.UUID;

import ru.vsu.cs.farsharing.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientEntity {
    UUID uid;
    UserEntity user;
    String license;
    String firstName;
    String midName;
    String lastName;
    String address;
    String phoneNumber;
    Integer accidents;
    WalletEmbeddable wallet;
    Status status;
}
