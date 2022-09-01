package ru.vsu.cs.farsharing.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.vsu.cs.farsharing.model.entity.LocationEntity;

public interface LocationService {
    @GET("/locations")
    Call<List<LocationEntity>> getAllLocations();
}
