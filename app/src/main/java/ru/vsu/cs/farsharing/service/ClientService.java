package ru.vsu.cs.farsharing.service;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.vsu.cs.farsharing.model.request.ClientRequest;
import ru.vsu.cs.farsharing.model.response.ClientDataResponse;
import ru.vsu.cs.farsharing.model.response.IAuthResponse;

public interface ClientService {
    @POST("api/client/register")
    Call<IAuthResponse> register(@Body ClientRequest registerRequest);

    @GET("api/client/{client_uid}")
    Call<ClientDataResponse> getClientData(@Path(value = "client_uid") UUID client_uid);

    @PUT("api/client/{client_uid}")
    Call<Void> updateClientData(@Path(value = "client_uid") UUID client_uid, @Body ClientRequest clientUpdateRequest);

    @PUT("api/client/{client_uid}")
    Call<Void> banClient(@Path(value = "client_uid") UUID client_uid);
}
