package ru.vsu.cs.farsharing.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IAuthResponse {
    UUID userUid;
    AuthAdminResponse authAdminResponse;
    AuthClientResponse authClientResponse;
}
