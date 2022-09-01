package ru.vsu.cs.farsharing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import lombok.var;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.model.request.ClientRequest;
import ru.vsu.cs.farsharing.service.ClientService;
import ru.vsu.cs.farsharing.service.FieldValidatorService;

public class RegisterActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_register);
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        registerNextButton = findViewById(R.id.registerNextButton);
        lastNameField = findViewById(R.id.lastNameField);
        firstNameField = findViewById(R.id.firstNameField);
        midNameField = findViewById(R.id.midNameField);
        licenseField = findViewById(R.id.licenseNumberField);
        emailField = findViewById(R.id.emailField);
        passwordField = findViewById(R.id.registerPasswordField);
        passwordRepeatField = findViewById(R.id.registerPasswordRepeatField);
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
                var clientRequest = new ClientRequest();
                clientRequest.setLastName(lastNameField.getText().toString());
                clientRequest.setFirstName(firstNameField.getText().toString());
                clientRequest.setMidName(midNameField.getText().toString());
                clientRequest.setLicense(licenseField.getText().toString());
                clientRequest.setEmail(emailField.getText().toString());
                clientRequest.setPassword(passwordField.getText().toString());
                FarSharingApp.getInstance().getClientService().register(clientRequest).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        //toEmailConfirm.putExtra(new Bundle(response.body()));
                        startActivity(toEmailConfirm);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(FarSharingApp.getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                FieldValidatorService.showWrongInputFields(wrongFields);
            }
        });
    }
}