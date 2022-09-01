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