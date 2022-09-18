package ru.vsu.cs.farsharing.model.entity;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationEntity {
    UUID uid;
    Double x;
    Double y;
}
