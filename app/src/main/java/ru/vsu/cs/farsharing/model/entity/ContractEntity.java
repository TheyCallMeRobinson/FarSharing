package ru.vsu.cs.farsharing.model.entity;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContractEntity {
    UUID uid;
    ClientEntity client;
    CarEntity car;
    String status;
    ZonedDateTime startTime;
    ZonedDateTime endTime;
}
