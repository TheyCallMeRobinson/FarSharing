package ru.vsu.cs.farsharing.activity.admin.client_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityClientDetailsBinding;
import ru.vsu.cs.farsharing.model.entity.CarEntity;
import ru.vsu.cs.farsharing.model.enums.Status;
import ru.vsu.cs.farsharing.model.response.ClientDataResponse;

public class ClientDetailsActivity extends AppCompatActivity {

    private ActivityClientDetailsBinding binding;
    private TextView fullName;
    private TextView uid;
    private TextView phoneNumber;
    private TextView address;
    private TextView accidentsCount;
    private TextView carsInRent;
    private Button ban;
    private ClientDataResponse client;
    private UUID clientUid;
    private int carsInRentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        clientUid = (UUID) getIntent().getExtras().get("clientUid");
        getClientCarsInRent();
        setClientData();
        setUpViews();
        setUpListeners();
    }

    private void getClientCarsInRent() {
        FarSharingApp.getInstance().getClientService().getBookedCarsList(clientUid).enqueue(new Callback<List<CarEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<CarEntity>> call, @NonNull Response<List<CarEntity>> response) {
                if (response.body() != null) {
                    carsInRentCount = response.body().size();
                } else {
                    Snackbar.make(binding.getRoot(), "Не удалось получить список клиентов", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CarEntity>> call, @NonNull Throwable t) {
                t.printStackTrace();
                Snackbar.make(binding.getRoot(), "Не удалось получить список клиентов", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setClientData() {
        FarSharingApp.getInstance().getClientService().getClientData(clientUid).enqueue(new Callback<ClientDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<ClientDataResponse> call, @NonNull Response<ClientDataResponse> response) {
                if (response.body() != null) {
                    client = response.body();
                    String fullNameText = String.format("%s %s %s", client.getLastName(), client.getFirstName(), client.getMidName());
                    fullName.setText(fullNameText);
                    uid.setText(clientUid.toString());
                    phoneNumber.setText(client.getPhoneNumber() != null ? client.getPhoneNumber() : "Нет данных");
                    address.setText(client.getAddress() != null ? client.getAddress() : "Нет данных");
                    accidentsCount.setText("0");
                    carsInRent.setText("0");
                    ban.setText(client.getStatus() == Status.BANNED ? "Разбанить" : "Забанить");
                    ban.setBackgroundColor(client.getStatus() == Status.BANNED ? getColor(R.color.colorAccent) : getColor(R.color.danger));
                } else {
                    Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClientDataResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
                Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setUpViews() {
        fullName = binding.fullName;
        uid = binding.clientUid;
        phoneNumber = binding.phoneNumber;
        address = binding.address;
        accidentsCount = binding.accidentsCount;
        carsInRent = binding.carsInRent;
        ban = binding.banUser;
    }

    private void setUpListeners() {
        ban.setOnClickListener(v -> {
            FarSharingApp.getInstance().getClientService().banClient(clientUid).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.code() == 200) {
                        Toast.makeText(FarSharingApp.getContext(), "Статус пользователя изменён", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(FarSharingApp.getContext(), ClientListActivity.class));
                        finish();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    t.printStackTrace();
                    Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                }
            });
        });
    }
}