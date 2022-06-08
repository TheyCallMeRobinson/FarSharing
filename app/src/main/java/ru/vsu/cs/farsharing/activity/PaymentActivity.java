package ru.vsu.cs.farsharing.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.vsu.cs.farsharing.R;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNumber;
    private EditText validThru;
    private EditText cvv;
    private Button submitPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        setUpViews();
        setUpListeners();
    }

    void setUpViews() {
        cardNumber = findViewById(R.id.cardNumberEditText);
        validThru = findViewById(R.id.validThruEditText);
        cvv = findViewById(R.id.cvvEditText);
        submitPayment = findViewById(R.id.submitPaymentButton);
    }

    void setUpListeners() {
        submitPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toEmailConfirm = new Intent(PaymentActivity.this, ConfirmEmailActivity.class);
                startActivity(toEmailConfirm);
            }
        });
    }
}