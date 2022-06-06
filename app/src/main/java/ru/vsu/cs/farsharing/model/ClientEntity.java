package ru.vsu.cs.farsharing.model;

import java.util.UUID;

import ru.vsu.cs.farsharing.model.enums.Status;

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
