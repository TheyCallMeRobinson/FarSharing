package ru.vsu.cs.farsharing.activity.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import ru.vsu.cs.farsharing.activity.menu.MenuActivity;
import ru.vsu.cs.farsharing.activity.admin.AdminMainActivity;
import ru.vsu.cs.farsharing.activity.login.LoginActivity;
import ru.vsu.cs.farsharing.activity.register.RegisterActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityWelcomeBinding;
import ru.vsu.cs.farsharing.model.enums.Role;

public class WelcomeActivity extends AppCompatActivity {
    private ActivityWelcomeBinding binding;
    private ImageView logo;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        logo = binding.logo;
        login = binding.loginButton;
        register = binding.registerButton;
    }

    private void setUpListeners() {
        login.setOnClickListener(v -> {
            if (FarSharingApp.getInstance().getRole() != null) {
                if (FarSharingApp.getInstance().getRole() == Role.CLIENT) {
                    startActivity(new Intent(WelcomeActivity.this, MenuActivity.class));
                } else {
                    startActivity(new Intent(WelcomeActivity.this, AdminMainActivity.class));
                }
            } else {
                Intent toLogin = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });
        register.setOnClickListener(v -> {
                Intent toRegister = new Intent(WelcomeActivity.this, RegisterActivity.class);
                startActivity(toRegister);
        });
    }
}