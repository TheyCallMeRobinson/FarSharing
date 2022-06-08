package ru.vsu.cs.farsharing.model.request;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayRequest {
    String cardNumber;
    String validThru;
    String cvv;
    Boolean savePaymentData;
}
