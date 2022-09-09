package ru.vsu.cs.farsharing.service.rest;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.vsu.cs.farsharing.model.entity.WalletEmbeddable;
import ru.vsu.cs.farsharing.model.request.AddContractRequest;
import ru.vsu.cs.farsharing.model.request.PayRequest;
import ru.vsu.cs.farsharing.model.response.CarResponse;

public interface ContractService {
    @POST("api/contract/new")
    Call<UUID> createNewContract(@Body AddContractRequest contractRequest);

    @PUT("api/pay/{contract_uid}")
    Call<Integer> contractPayment(@Path(value = "contract_uid") UUID contract, @Body PayRequest payRequest);

    @GET("api/pay/{client_uid}")
    Call<WalletEmbeddable> getWalletData(@Path(value = "client_uid") UUID clientUid);

    @GET("api/contract/{cl_uid}/{car_uid}")
    Call<CarResponse> getClientToCarRelation(@Path(value = "cl_uid") UUID clientUid, @Path(value = "car_uid") UUID carUid);

    @GET("api/contract/cancel/{contract_uid}")
    Call<Void> cancelContract(@Path(value = "contract_uid") UUID contractUid);

    //@GET("api/admin/request_info/{client_uid}")
}
