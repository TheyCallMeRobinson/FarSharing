package ru.vsu.cs.farsharing.activity.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.yandex.metrica.YandexMetrica;

import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.login.LoginActivity;
import ru.vsu.cs.farsharing.activity.register.RegisterActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;

public class WelcomeActivity extends AppCompatActivity {
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        YandexMetrica.activate(this.getApplicationContext(), getString(R.string.metrika_api_key));
        YandexMetrica.enableActivityAutoTracking(FarSharingApp.getInstance());
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