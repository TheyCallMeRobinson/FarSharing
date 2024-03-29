package ru.vsu.cs.farsharing.service.rest;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.vsu.cs.farsharing.model.entity.CarEntity;
import ru.vsu.cs.farsharing.model.request.AddCarRequest;

public interface CarService {
    @GET("api/cars")
    Call<List<CarEntity>> getCars();

    @POST("api/car")
    Call<Void> postCar(@Body AddCarRequest carRequest);

    @GET("api/car/{uid}")
    Call<CarEntity> getCar(@Path(value = "uid") UUID uid);

    @PUT("api/car/{uid}")
    Call<CarEntity> updateCar(@Path(value = "uid") UUID uid, CarEntity entity);

    @DELETE("api/car/{uid}")
    Call<Void> deleteCar(@Path(value = "uid") UUID uid);
}
