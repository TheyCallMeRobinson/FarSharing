package ru.vsu.cs.farsharing.model.request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    String email;
    String password;
    public UserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
