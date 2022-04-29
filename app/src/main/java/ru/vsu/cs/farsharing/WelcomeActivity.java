package ru.vsu.cs.farsharing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        login = findViewById(R.id.login_button);
        register = findViewById(R.id.register_button);
    }

    private void setUpListeners() {
        login.setOnClickListener(v -> {
                Intent toLogin = new Intent(WelcomeActivity.this, ru.vsu.cs.farsharing.LoginActivity.class);
                startActivity(toLogin);
        });
        register.setOnClickListener(v -> {
                Intent toRegister = new Intent(WelcomeActivity.this, ru.vsu.cs.farsharing.RegisterActivity.class);
                startActivity(toRegister);
        });
    }
}