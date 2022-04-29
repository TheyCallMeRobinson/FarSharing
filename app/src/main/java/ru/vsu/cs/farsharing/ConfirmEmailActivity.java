package ru.vsu.cs.farsharing;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmEmailActivity extends AppCompatActivity {

    private Button confirmEmailNextButton;
    private EditText confirmCodeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_email);
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        confirmEmailNextButton = findViewById(R.id.confirmEmailNextButton);
        confirmCodeText = findViewById(R.id.confirmCodeText);
    }

    private void setUpListeners() {
        confirmEmailNextButton.setOnClickListener(v -> {
            Intent toLogin = new Intent(ConfirmEmailActivity.this, ru.vsu.cs.farsharing.LoginActivity.class);
            startActivity(toLogin);
        });
    }
}