package ru.vsu.cs.farsharing.model.response;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponse {
    Boolean isFree;
    Boolean thisClient;
    String status;
    UUID contractUid;
}
