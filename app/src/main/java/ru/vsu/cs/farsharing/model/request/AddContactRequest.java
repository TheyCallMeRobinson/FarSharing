package ru.vsu.cs.farsharing.model.request;

import java.time.ZonedDateTime;
import java.util.UUID;

public class AddContactRequest {
    UUID clientUid;
    UUID carUid;
    ZonedDateTime startTime;
    ZonedDateTime endTime;
}
