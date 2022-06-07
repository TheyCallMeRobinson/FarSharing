package ru.vsu.cs.farsharing.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import ru.vsu.cs.farsharing.R;

public class CarDetailsActivity extends AppCompatActivity {

    private Button confirmChoice;
    private TextView brand;
    private TextView model;
    private TextView bodyType;
    private TextView color;
    private TextView address;
    private TextView pricePerHour;
    private TextView rentForHours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_details);
        setUpViews();
        setCarData();
        setUpListeners();
    }

    private void setUpViews() {
        brand = findViewById(R.id.brand);
        model = findViewById(R.id.model);
        bodyType = findViewById(R.id.bodyType);
        color = findViewById(R.id.color);
        address = findViewById(R.id.address);
        rentForHours = findViewById(R.id.rentForHours);
        pricePerHour = findViewById(R.id.pricePerHour);
        confirmChoice = findViewById(R.id.confirmChoice);
    }

    private void setUpListeners() {
        confirmChoice.setOnClickListener(v -> {
            Intent toLogin = new Intent(CarDetailsActivity.this, LoginActivity.class);
            startActivity(toLogin);
        });
    }

    private JSONObject getCarData(int carId) throws JSONException {
        return new JSONObject("{" +
                "\"brand\": \"RENAULT\"," +
                "\"model\": \"Captur\"," +
                "\"bodyType\": \"Кроссовер\"," +
                "\"color\": \"Красный\"," +
                "\"address\": \"ул. Ленина, 1\"," +
                "\"pricePerHour\": \"500\"," +
                "\"rentForHours\": \"5\"," +
                "}");
    }

    private void setCarData() {
//        try {
//            JSONObject jo = getCarData(1);
//            brand.setText(jo.getString("brand"));
//            model.setText(jo.getString("model"));
//            bodyType.setText(jo.getString("bodyType"));
//            color.setText(jo.getString("color"));
//            address.setText(jo.getString("address"));
//            pricePerHour.setText(jo.getString("pricePerHour"));
//            rentForHours.setText(jo.getString("rentForHours"));
//
//        } catch (JSONException jsone) {
//            jsone.printStackTrace();
//        }
        brand.setText("RENAULT");
        model.setText("Captur");
        bodyType.setText("Кроссовер");
        color.setText("Красный");
        address.setText("ул. Ленина, 1");
        pricePerHour.setText("500");
        rentForHours.setText("5");
    }
}