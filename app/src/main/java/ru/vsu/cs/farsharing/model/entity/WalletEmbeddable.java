package ru.vsu.cs.farsharing.model.entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletEmbeddable {
    String card;
    String validThru;
    Integer cvv;
}
