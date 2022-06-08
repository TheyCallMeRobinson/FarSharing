package ru.vsu.cs.farsharing.model.response;

import java.util.List;

import ru.vsu.cs.farsharing.model.entity.ContractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthAdminResponse {
    List<ContractEntity> contracts;

}
