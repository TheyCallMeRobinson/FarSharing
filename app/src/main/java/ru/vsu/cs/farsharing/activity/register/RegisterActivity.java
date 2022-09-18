package ru.vsu.cs.farsharing.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import lombok.var;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityRegisterBinding;
import ru.vsu.cs.farsharing.model.request.ClientRequest;
import ru.vsu.cs.farsharing.model.response.IAuthResponse;
import ru.vsu.cs.farsharing.service.app.FieldValidatorService;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private Button registerNextButton;
    private EditText lastNameField;
    private EditText firstNameField;
    private EditText midNameField;
    private EditText licenseField;
    private EditText emailField;
    private EditText passwordField;
    private EditText passwordRepeatField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        registerNextButton = binding.registerNextButton;
        lastNameField = binding.lastNameField;
        firstNameField = binding.firstNameField;
        midNameField = binding.midNameField;
        licenseField = binding.licenseNumberField;
        emailField = binding.emailField;
        passwordField = binding.registerPasswordField;
        passwordRepeatField = binding.registerPasswordRepeatField;
    }

    private List<EditText> getWrongInputs() { // изменить
        List<EditText> wrongInputs = new ArrayList<>();
        if (!FieldValidatorService.checkFieldMatchesRegex(emailField, "^.+@.+$")) {
            wrongInputs.add(emailField);
        }
        if (!FieldValidatorService.checkFieldsMatch(passwordField, passwordRepeatField)) {
            wrongInputs.add(passwordField);
            wrongInputs.add(passwordRepeatField);
        }
        return wrongInputs;
    }

    private void setUpListeners() {
        registerNextButton.setOnClickListener(v -> {
            var wrongFields = getWrongInputs();
            if (wrongFields.isEmpty()) {
                Intent toEmailConfirm = new Intent(RegisterActivity.this, ConfirmEmailActivity.class);
                var clientRequest = new ClientRequest(
                    emailField.getText().toString(),
                    passwordField.getText().toString(),
                    licenseField.getText().toString(),
                    firstNameField.getText().toString(),
                    midNameField.getText().toString(),
                    lastNameField.getText().toString(),
                    null,
                    null,
                    null,
                    null,
                    null
                );
                FarSharingApp.getInstance().getClientService().register(clientRequest).enqueue(new Callback<IAuthResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<IAuthResponse> call, @NonNull Response<IAuthResponse> response) {
                        if (response.body() != null) {
                            toEmailConfirm.putExtra("clientUid", response.body().getAuthClientResponse().getClientUid());
                            toEmailConfirm.putExtra("userUid", response.body().getUserUid());
                            startActivity(toEmailConfirm);
                            finish();
                        } else {
                            Snackbar.make(binding.getRoot(), "Ваш Email" + emailField.getText() + " уже зарегистрирован, пожалуйста, подтвердите аккаунт с помощью кода", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<IAuthResponse> call, @NonNull Throwable t) {
                        Snackbar.make(binding.getRoot(), t.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            } else {
                FieldValidatorService.showWrongInputFields(wrongFields);
            }
        });
    }
}