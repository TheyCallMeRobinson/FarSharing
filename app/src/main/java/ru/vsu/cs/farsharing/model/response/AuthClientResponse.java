package ru.vsu.cs.farsharing.model.response;

import java.util.List;
import java.util.UUID;

import ru.vsu.cs.farsharing.model.CarEntity;

public class AuthClientResponse {
    UUID uid;
    List<CarEntity> cars;
}
