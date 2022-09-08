package ru.vsu.cs.farsharing.model.request;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddContractRequest {
    UUID clientUid;
    UUID carUid;
//    Instant startTime;
//    Instant endTime;
    String startTime;
    String endTime;
}
