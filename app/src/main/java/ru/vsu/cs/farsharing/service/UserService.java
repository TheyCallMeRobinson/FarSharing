package ru.vsu.cs.farsharing.service;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.vsu.cs.farsharing.model.request.UserRequest;
import ru.vsu.cs.farsharing.model.response.IAuthResponse;

public interface UserService {
    @POST("/api/user/auth")
    Call<IAuthResponse> auth(@Body UserRequest userRequest);
}
