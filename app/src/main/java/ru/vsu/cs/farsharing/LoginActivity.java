package ru.vsu.cs.farsharing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private Button loginNextButton;
    private EditText loginEmailField, loginPasswordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private void setUpViews() {
        loginNextButton = findViewById(R.id.loginNextButton);
        loginEmailField = findViewById(R.id.loginEmailField);
        loginPasswordField = findViewById(R.id.loginPasswordField);
    }

    private void setUpListeners() {
        loginNextButton.setOnClickListener(v -> {
            Intent toEmailConfirm = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(toEmailConfirm);
        });
    }
}