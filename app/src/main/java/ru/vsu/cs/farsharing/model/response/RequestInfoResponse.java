package ru.vsu.cs.farsharing.model.response;

import ru.vsu.cs.farsharing.model.entity.ClientEntity;
import ru.vsu.cs.farsharing.model.entity.ContractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestInfoResponse {
    ClientEntity client;
    ContractEntity contract;
}
