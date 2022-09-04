package ru.vsu.cs.farsharing.model.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
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
