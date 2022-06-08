package ru.vsu.cs.farsharing.model.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
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
}
