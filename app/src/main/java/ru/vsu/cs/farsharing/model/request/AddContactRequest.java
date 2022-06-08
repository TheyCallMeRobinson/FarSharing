package ru.vsu.cs.farsharing.model.request;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddContactRequest {
    UUID clientUid;
    UUID carUid;
    ZonedDateTime startTime;
    ZonedDateTime endTime;
}
