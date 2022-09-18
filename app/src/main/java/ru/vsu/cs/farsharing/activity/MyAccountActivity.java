package ru.vsu.cs.farsharing.activity;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.activity.welcome.WelcomeActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityMyAccountBinding;
import ru.vsu.cs.farsharing.model.request.ClientRequest;
import ru.vsu.cs.farsharing.model.response.ClientDataResponse;
import ru.vsu.cs.farsharing.service.app.FieldValidatorService;

public class MyAccountActivity extends Activity {

    private ActivityMyAccountBinding binding;

    private EditText lastName;
    private EditText firstName;
    private EditText midName;
    private EditText address;
    private EditText phoneNumber;
    private EditText licenseNumber;
    private EditText email;
    private EditText password;
    private EditText repeatPassword;
    private Button submitChanges;
    private Button deleteAccount;

    private ClientDataResponse clientData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setClientData();
        setUpViews();
        setUpListeners();
    }

    private void setClientData() {
        FarSharingApp.getInstance().getClientService().getClientData(FarSharingApp.getInstance().getClientUid()).enqueue(new Callback<ClientDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<ClientDataResponse> call, @NonNull Response<ClientDataResponse> response) {
                if (response.body() != null) {
                    clientData = response.body();
                    lastName.setText(clientData.getLastName());
                    firstName.setText(clientData.getFirstName());
                    midName.setText(clientData.getMidName());
                    address.setText(clientData.getAddress());
                    phoneNumber.setText(clientData.getPhoneNumber());
                    licenseNumber.setText(clientData.getLicense());
                    email.setText(clientData.getEmail());
                } else {
                    Snackbar.make(binding.getRoot(), "Не удалось загрузить данные о клиенте", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ClientDataResponse> call, @NonNull Throwable t) {
                t.printStackTrace();
                Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void setUpViews() {
        lastName = binding.lastName;
        firstName = binding.firstName;
        midName = binding.midName;
        address = binding.address;
        phoneNumber = binding.phoneNumber;
        licenseNumber = binding.licenseNumber;
        email = binding.email;
        password = binding.password;
        repeatPassword = binding.repeatPassword;
        submitChanges = binding.submitChanges;
        deleteAccount = binding.deleteAccount;
    }

    private void setUpListeners() {
        submitChanges.setOnClickListener(v -> {
            if (FieldValidatorService.checkFieldMatchesRegex(email, "^.+@.+$", "Неправильная почта") &&
                    FieldValidatorService.checkFieldsMatch(password, repeatPassword, "Введёные пароли не совпадают") &&
                    FieldValidatorService.checkFieldMatchesRegex(phoneNumber, "[1-9][0-9]{10}", "Телефон должен быть в формате 89012345678")) {
                ClientRequest request = new ClientRequest(
                        email.getText().toString(),
                        password.getText().toString(),
                        licenseNumber.getText().toString(),
                        firstName.getText().toString(),
                        midName.getText().toString(),
                        lastName.getText().toString(),
                        address.getText().toString(),
                        phoneNumber.getText().toString(),
                        null,
                        null,
                        null
                );
                FarSharingApp.getInstance().getClientService().updateClientData(FarSharingApp.getInstance().getClientUid(), request).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                        if (response.code() == 200) {
                            Snackbar.make(binding.getRoot(), "Данные успешно обновлены", Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(binding.getRoot(), "Лицензия с таким номером уже присвоена другому аккаунту", Snackbar.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                        t.printStackTrace();
                        Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                    }
                });
            }
        });

        deleteAccount.setOnClickListener(v -> {
            Snackbar dialog = Snackbar
                .make(binding.getRoot(), "Вы уверены, что хотите удалить аккаунт?", Snackbar.LENGTH_INDEFINITE)
                .setAction("Да", view -> {
                    FarSharingApp.getInstance().getClientService().deleteClient(FarSharingApp.getInstance().getClientUid()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                            if (response.code() == 200) {
                                FarSharingApp.getInstance().clearUserSessionData();
                                Toast.makeText(FarSharingApp.getContext(), "Ваш аккаунт был успешно удален", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(FarSharingApp.getContext(), WelcomeActivity.class));
                                finish();
                            } else {
                                Toast.makeText(FarSharingApp.getContext(), "Не удалось удалить аккаунт", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                            t.printStackTrace();
                            Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                        }
                    });
                });
            dialog.show();
        });
    }
}