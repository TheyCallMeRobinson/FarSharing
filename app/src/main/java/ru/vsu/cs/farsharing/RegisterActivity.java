package ru.vsu.cs.farsharing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private Button registerNextButton;
    private EditText nameField,
            birthdateField,
            licenseNumberField,
            emailField,
            registerPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        registerNextButton = findViewById(R.id.registerNextButton);
        nameField = findViewById(R.id.nameField);
        birthdateField = findViewById(R.id.birthdateField);
        licenseNumberField = findViewById(R.id.licenseNumberField);
        emailField = findViewById(R.id.emailField);
        registerPasswordField = findViewById(R.id.registerPasswordField);
    }

    private void setUpListeners() {
        registerNextButton.setOnClickListener(v -> {
            Intent toEmailConfirm = new Intent(RegisterActivity.this, ConfirmEmailActivity.class);
            startActivity(toEmailConfirm);
        });
    }
}