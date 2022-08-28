package ru.vsu.cs.farsharing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.model.enums.Role;
import ru.vsu.cs.farsharing.model.request.UserRequest;
import ru.vsu.cs.farsharing.model.response.IAuthResponse;

public class LoginActivity extends AppCompatActivity {

    private Button loginNextButton;
    private EditText loginEmailField, loginPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        loginNextButton = findViewById(R.id.loginNextButton);
        loginEmailField = findViewById(R.id.loginEmailField);
        loginPasswordField = findViewById(R.id.loginPasswordField);
    }

    private void setUpListeners() {
        loginNextButton.setOnClickListener(v -> {
            logIn(loginEmailField.getText().toString(), loginPasswordField.getText().toString());
        });
    }

    private void logIn(String email, String password) {
        FarSharingApp.getInstance().getUserService().auth(new UserRequest(email, password)).enqueue(new Callback<IAuthResponse>() {
            @Override
            public void onResponse(Call<IAuthResponse> call, Response<IAuthResponse> response) {
                if (response.body() != null) {
                    FarSharingApp.getInstance().setUserUUID(response.body().getUserUid());
                    if (response.body().getAuthAdminResponse() != null) {
                        FarSharingApp.getInstance().setRole(Role.ADMIN);
                    } else if (response.body().getAuthClientResponse() != null) {
                        FarSharingApp.getInstance().setClientUUID(response.body().getAuthClientResponse().getUid());
                        FarSharingApp.getInstance().setRole(Role.CLIENT);
                    }
                    Intent toMainActivity = new Intent(FarSharingApp.getContext(), MainActivity.class);
                    if (FarSharingApp.getInstance().getUserUUID() != null) {
                        startActivity(toMainActivity);
                    }
                    else {
                        Toast.makeText(FarSharingApp.getContext(), "Данный пользователь был удален", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(FarSharingApp.getContext(), "Неверный логин или пароль", Toast.LENGTH_LONG).show();
                }
                loginPasswordField.setText("");
            }

            @Override
            public void onFailure(Call<IAuthResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}