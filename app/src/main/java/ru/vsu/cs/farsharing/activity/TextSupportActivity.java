package ru.vsu.cs.farsharing.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import ru.vsu.cs.farsharing.R;

public class TextSupportActivity extends AppCompatActivity {
    private EditText bugReportField;
    private Button bugReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_support);
    }
}