package ru.vsu.cs.farsharing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ru.vsu.cs.farsharing.R;

public class WelcomeActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        YandexMetrica.activate(this.getApplicationContext(), getString(R.string.metrika_api_key));
//        YandexMetrica.enableActivityAutoTracking(FarSharingApp.getInstance());
//        CarService carService = AppConfig.retrofit.create(CarService.class);
//        Call<List<BriefCarInfoResponse>> jsonObjectCall = carService.getCars();
//        jsonObjectCall.enqueue(new Callback<List<BriefCarInfoResponse>>() {
//            @Override
//            public void onResponse(Call<List<BriefCarInfoResponse>> call, Response<List<BriefCarInfoResponse>> response) {
//                System.out.println("kek");
//            }
//
//            @Override
//            public void onFailure(Call<List<BriefCarInfoResponse>> call, Throwable t) {
//                Toast.makeText(WelcomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//                System.err.println(t.getMessage());
//            }
//        });
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
    }

    private void setUpListeners() {
        login.setOnClickListener(v -> {
                Intent toLogin = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(toLogin);
        });
        register.setOnClickListener(v -> {
                Intent toRegister = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(toRegister);
        });
    }
}