package ru.vsu.cs.farsharing.config;

import android.app.Application;
import android.content.Context;

import java.util.UUID;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.cs.farsharing.model.enums.Role;
import ru.vsu.cs.farsharing.service.CarService;
import ru.vsu.cs.farsharing.service.ClientService;
import ru.vsu.cs.farsharing.service.LocationService;
import ru.vsu.cs.farsharing.service.UserService;

public final class FarSharingApp extends Application {

    public final String baseUrl = "http://farsharing-server.herokuapp.com/api/";

    private Retrofit retrofit;

    private CarService carService;
    private ClientService clientService;
    private LocationService locationService;
    private UserService userService;

    private UUID userUUID;
    private UUID clientUUID;
    private Role role;

    private static FarSharingApp instance;
    private static Context context;

    public static FarSharingApp getInstance() {
        if(instance == null) {
            instance = new FarSharingApp();
        }
        return instance;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        context = getApplicationContext();

        retrofit = new Retrofit.Builder()
                      .baseUrl(baseUrl)
                      .addConverterFactory(GsonConverterFactory.create())
                      .client(new OkHttpClient.Builder().build())
                      .build();

        carService = retrofit.create(CarService.class);
        clientService = retrofit.create(ClientService.class);
        locationService = retrofit.create(LocationService.class);
        userService = retrofit.create(UserService.class);
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public CarService getCarService() {
        return carService;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public LocationService getLocationService() {
        return locationService;
    }

    public UserService getUserService() {
        return userService;
    }

    public UUID getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(UUID userUUID) {
        this.userUUID = userUUID;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getClientUUID() {
        return clientUUID;
    }

    public void setClientUUID(UUID clientUUID) {
        this.clientUUID = clientUUID;
    }
}