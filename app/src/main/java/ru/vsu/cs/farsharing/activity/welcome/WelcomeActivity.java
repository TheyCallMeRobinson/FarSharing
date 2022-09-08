package ru.vsu.cs.farsharing.activity.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.material.snackbar.Snackbar;
import com.yandex.metrica.YandexMetrica;

import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.login.LoginActivity;
import ru.vsu.cs.farsharing.activity.main.MainActivity;
import ru.vsu.cs.farsharing.activity.payment.PaymentActivity;
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
//        YandexMetrica.activate(this.getApplicationContext(), getString(R.string.metrika_api_key));
//        YandexMetrica.enableActivityAutoTracking(FarSharingApp.getInstance());
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
                    Intent toMainActivity = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(toMainActivity);
                } else {
                    //toAdminMainActivity
                    //Intent toMainActivity = new Intent(WelcomeActivity.this, MainActivity.class);
                    //startActivity(toMainActivity);
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