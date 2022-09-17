package ru.vsu.cs.farsharing.activity.payment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.activity.login.LoginActivity;
import ru.vsu.cs.farsharing.activity.main.MainActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityPaymentBinding;
import ru.vsu.cs.farsharing.model.request.PayRequest;
import ru.vsu.cs.farsharing.service.app.FieldValidatorService;
import ru.vsu.cs.farsharing.service.app.NotificationService;

public class PaymentActivity extends AppCompatActivity {

    private ActivityPaymentBinding binding;
    private EditText cardNumber;
    private EditText validThru;
    private EditText cvv;
    private CheckBox saveData;
    private Button submitPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        cardNumber = binding.cardNumberEditText;
        validThru = binding.validThruEditText;
        cvv = binding.cvvEditText;
        saveData = binding.savePaymentData;
        submitPayment = binding.submitPaymentButton;
    }

    private void setUpListeners() {
        submitPayment.setOnClickListener(v -> {
            if (fieldValuesCorrect()) {
                PayRequest payRequest = new PayRequest(
                        cardNumber.getText().toString(),
                        validThru.getText().toString(),
                        cvv.getText().toString(),
                        saveData.isChecked()
                );
                FarSharingApp.getInstance().getContractService().contractPayment(
                        (UUID) getIntent().getExtras().get("contractUid"),
                        payRequest
                ).enqueue(new Callback<Integer>() {
                    @Override
                    public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                        if (response.body() != null) {
                            Integer carOpenCode = response.body();
                            String textTitle = "Код разблокировки авто";
                            String textContent = "Ваш код разблокировки авто: " + carOpenCode;
                            NotificationService.sendPushNotificationWithIntent(FarSharingApp.getContext(), textTitle, textContent, new Intent(FarSharingApp.getContext(), LoginActivity.class));
                            Intent toMain = new Intent(FarSharingApp.getContext(), MainActivity.class);
                            startActivity(toMain);
                        } else {
                            Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {
                        Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private boolean fieldValuesCorrect() {
        return FieldValidatorService.checkInputLength(cardNumber, 16, "Неправильные данные карты") &&
               FieldValidatorService.checkFieldMatchesRegex(validThru, "(0[1-9]|1[0-2])\\/[0-9]{2}", "Введите дату в формате ММ/ГГ") &&
               FieldValidatorService.checkFieldMatchesRegex(cvv, "[0-9]{3}", "Неправильный CVV/CVC код");
    }
}