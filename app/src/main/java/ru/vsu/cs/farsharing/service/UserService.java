package ru.vsu.cs.farsharing.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.vsu.cs.farsharing.model.request.UserRequest;
import ru.vsu.cs.farsharing.model.response.IAuthResponse;

public interface UserService {
    @POST("/api/user/auth")
    Call<IAuthResponse> auth(@Body UserRequest userRequest);
}
