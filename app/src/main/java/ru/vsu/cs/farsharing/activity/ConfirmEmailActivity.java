package ru.vsu.cs.farsharing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.login.LoginActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;

public class ConfirmEmailActivity extends AppCompatActivity {

    private Button confirmEmailNextButton;
    private EditText confirmCodeText;
    private UUID userUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);
        setUpViews();
        getExtras();
        setUpListeners();
    }

    private void getExtras() {
        Bundle extras = getIntent().getExtras();
        userUid = (UUID) extras.get("userUid");
    }

    private void setUpViews() {
        confirmEmailNextButton = findViewById(R.id.confirmEmailNextButton);
        confirmCodeText = findViewById(R.id.confirmCodeText);
    }

    private void setUpListeners() {
        confirmEmailNextButton.setOnClickListener(v -> {
            FarSharingApp.getInstance().getUserService().activateAccount(userUid, Integer.getInteger(confirmCodeText.getText().toString())).enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                    if (response.body() != null && response.body()) {
                        Intent toLoginActivity = new Intent(FarSharingApp.getContext(), LoginActivity.class);
                        startActivity(toLoginActivity);
                    } else {
                        confirmCodeText.setError("Неверный код");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

                }
            });
        });
    }
}