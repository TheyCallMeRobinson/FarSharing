package ru.vsu.cs.farsharing.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.vsu.cs.farsharing.R;

public class PaymentActivity extends AppCompatActivity {

    private EditText cardNumber;
    private EditText validThru;
    private EditText cvv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }
}