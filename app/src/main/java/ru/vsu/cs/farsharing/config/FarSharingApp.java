package ru.vsu.cs.farsharing.config;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import java.util.UUID;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.vsu.cs.farsharing.model.enums.Role;
import ru.vsu.cs.farsharing.service.CarService;
import ru.vsu.cs.farsharing.service.ClientService;
import ru.vsu.cs.farsharing.service.ContractService;
import ru.vsu.cs.farsharing.service.LocationService;
import ru.vsu.cs.farsharing.service.UserService;

public final class FarSharingApp extends Application {

    public final String BASE_URL = "http://farsharing-server.herokuapp.com/";
    private final String CHANNEL_ID = "1";

    private Retrofit retrofit;

    private CarService carService;
    private ClientService clientService;
    private LocationService locationService;
    private UserService userService;
    private ContractService contractService;

    private UUID userUid;
    private UUID clientUid;
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
                      .baseUrl(BASE_URL)
                      .addConverterFactory(GsonConverterFactory.create())
                      .client(new OkHttpClient.Builder().build())
                      .build();

        carService = retrofit.create(CarService.class);
        clientService = retrofit.create(ClientService.class);
        locationService = retrofit.create(LocationService.class);
        userService = retrofit.create(UserService.class);
        contractService = retrofit.create(ContractService.class);
        createNotificationChannel();
    }
    private void createNotificationChannel() {
        CharSequence name = "Notification Channel";
        String description = "Notification Channel for FarSharing App";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
        channel.setDescription(description);
        // Register the channel with the system; you can't change the importance
        // or other notification behaviors after this
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
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

    public UUID getUserUid() {
        return userUid;
    }

    public void setUserUid(UUID userUid) {
        this.userUid = userUid;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getClientUid() {
        return clientUid;
    }

    public void setClientUid(UUID clientUid) {
        this.clientUid = clientUid;
    }

    public ContractService getContractService() {
        return contractService;
    }

    public void setContractService(ContractService contractService) {
        this.contractService = contractService;
    }

    public String getNotificationChannelId() {
        return CHANNEL_ID;
    }
}