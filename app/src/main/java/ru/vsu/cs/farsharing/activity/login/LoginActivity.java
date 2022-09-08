package ru.vsu.cs.farsharing.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.main.MainActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityLoginBinding;
import ru.vsu.cs.farsharing.model.enums.Role;
import ru.vsu.cs.farsharing.model.request.UserRequest;
import ru.vsu.cs.farsharing.model.response.IAuthResponse;

public class LoginActivity extends AppCompatActivity {

    private Button loginNextButton;
    private EditText loginEmailField, loginPasswordField;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        loginNextButton = binding.loginNextButton;
        loginEmailField = binding.loginEmailField;
        loginPasswordField = binding.loginPasswordField;
    }

    private void setUpListeners() {
        loginNextButton.setOnClickListener(v -> {
            logIn(loginEmailField.getText().toString(), loginPasswordField.getText().toString());
        });
    }

    private void logIn(String email, String password) {
        FarSharingApp.getInstance().getUserService().auth(new UserRequest(email, password)).enqueue(new Callback<IAuthResponse>() {
            @Override
            public void onResponse(@NonNull Call<IAuthResponse> call, @NonNull Response<IAuthResponse> response) {
                if (response.body() != null) {
                    FarSharingApp.getInstance().setUserUid(response.body().getUserUid());
                    if (response.body().getAuthAdminResponse() != null) {
                        FarSharingApp.getInstance().setRole(Role.ADMIN);
                    } else if (response.body().getAuthClientResponse() != null) {
                        FarSharingApp.getInstance().setClientUid(response.body().getAuthClientResponse().getClientUid());
                        FarSharingApp.getInstance().setRole(Role.CLIENT);
                    }
                    Intent toMainActivity = new Intent(FarSharingApp.getContext(), MainActivity.class);
                    if (FarSharingApp.getInstance().getUserUid() != null) {
                        startActivity(toMainActivity);
                    } else {
                        Snackbar.make(binding.getRoot(), "Данный пользователь был удален", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    Snackbar.make(binding.getRoot(), "Неверный логин или пароль", Snackbar.LENGTH_LONG).show();
                    loginPasswordField.setBackgroundTintList(getBaseContext().getColorStateList(R.color.danger));
                    loginEmailField.setBackgroundTintList(getBaseContext().getColorStateList(R.color.danger));
                }
                loginPasswordField.setText("");
            }

            @Override
            public void onFailure(@NonNull Call<IAuthResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }
}