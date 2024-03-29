package ru.vsu.cs.farsharing.model.response;

import java.util.List;
import java.util.UUID;

import lombok.Data;
import ru.vsu.cs.farsharing.model.entity.ContractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AuthAdminResponse {
    UUID uuid;
    List<ContractEntity> contracts;
}
