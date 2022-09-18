package ru.vsu.cs.farsharing.model.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PayRequest {
    String cardNumber;
    String validThru;
    String cvv;
    Boolean savePaymentData;
}
