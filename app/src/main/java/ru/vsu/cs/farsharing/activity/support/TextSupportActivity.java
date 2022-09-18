package ru.vsu.cs.farsharing.activity.support;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.activity.menu.MenuActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityTextSupportBinding;
import ru.vsu.cs.farsharing.model.request.MessageRequest;

public class TextSupportActivity extends AppCompatActivity {
    private ActivityTextSupportBinding binding;
    private EditText bugReportField;
    private EditText bugReportTitle;
    private Button bugReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextSupportBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        setUpListeners();
    }

    private void setUpViews() {
        bugReportField = binding.bugReportField;
        bugReportButton = binding.bugReportButton;
        bugReportTitle = binding.bugReportTitle;
    }

    private void setUpListeners() {
        bugReportButton.setOnClickListener(v -> {
            MessageRequest message = new MessageRequest(bugReportTitle.getText().toString(), bugReportField.getText().toString());
            FarSharingApp.getInstance().getBugReportService().sendBugReportMessage(message).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                    if (response.code() == 200) {
                        Toast.makeText(FarSharingApp.getContext(), "Ваше сообщение успешно отправлено, спасибо за обратную связь", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(FarSharingApp.getContext(), MenuActivity.class));
                        finish();
                    } else {
                        Snackbar.make(binding.getRoot(), "Что-то пошло не так, ваше сообщение не доставлено", Snackbar.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                    t.printStackTrace();
                    Snackbar.make(binding.getRoot(), "Не удалось связаться с сервером", Snackbar.LENGTH_LONG).show();
                }
            });
        });
    }

}