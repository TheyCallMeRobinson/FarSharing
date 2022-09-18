package ru.vsu.cs.farsharing.model.response;

import java.util.List;
import java.util.UUID;

import ru.vsu.cs.farsharing.model.entity.CarEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthClientResponse {
    UUID clientUid;
    List<CarEntity> cars;
}
