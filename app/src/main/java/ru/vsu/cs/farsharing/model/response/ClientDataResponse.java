package ru.vsu.cs.farsharing.model.response;

import ru.vsu.cs.farsharing.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDataResponse {
    String email;
    String password;
    String license;
    String firstName;
    String midName;
    String lastName;
    String address;
    String phoneNumber;
    String cardNumber;
    String validThru;
    String cvv;
    Integer accidents;
    Status status;
    Boolean existsActiveContract;
}
