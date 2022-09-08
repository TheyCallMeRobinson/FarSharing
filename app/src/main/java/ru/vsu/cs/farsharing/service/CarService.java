package ru.vsu.cs.farsharing.service;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import ru.vsu.cs.farsharing.model.entity.CarEntity;

public interface CarService {
    @GET("api/cars")
    Call<List<CarEntity>> getCars();

    @POST("api/car")
    Call<Void> postCar(CarEntity carEntity);

    @GET("api/car/{uid}")
    Call<CarEntity> getCar(@Path(value = "uid") UUID uid);

    @PUT("api/car/{uid}")
    Call<CarEntity> updateCar(@Path(value = "uid") UUID uid, CarEntity entity);

    @DELETE("api/car/{uid}")
    Call<Void> deleteCar(@Path(value = "uid") UUID uid);
}
