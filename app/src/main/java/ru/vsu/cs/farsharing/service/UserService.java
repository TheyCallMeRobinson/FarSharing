package ru.vsu.cs.farsharing.service;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.vsu.cs.farsharing.model.request.UserRequest;
import ru.vsu.cs.farsharing.model.response.IAuthResponse;

public interface UserService {
    @POST("/user/auth")
    Call<IAuthResponse> auth(@Body UserRequest userRequest);

    @GET("/user/activate/{uid}/{code}")
    Call<Boolean> activateAccount(@Path(value = "uid") UUID uid, @Path(value = "code") Integer code);
}
