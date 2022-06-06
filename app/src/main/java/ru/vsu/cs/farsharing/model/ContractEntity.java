package ru.vsu.cs.farsharing.model;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.UUID;

public class ContractEntity {
    UUID uid;
    ClientEntity client;
    CarEntity car;
    String status;
    ZonedDateTime startTime;
    ZonedDateTime endTime;
}
