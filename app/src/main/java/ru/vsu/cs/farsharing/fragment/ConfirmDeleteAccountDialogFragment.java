package ru.vsu.cs.farsharing.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.activity.welcome.WelcomeActivity;
import ru.vsu.cs.farsharing.config.FarSharingApp;

public class ConfirmDeleteAccountDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Вы уверены, что хотите удалить аккаунт?\nДействие нельзя обратить")
                .setPositiveButton("Да", (dialog, id) -> {
                    FarSharingApp.getInstance().getClientService().deleteClient(FarSharingApp.getInstance().getClientUid()).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                            if (response.code() == 200) {
                                Toast.makeText(FarSharingApp.getContext(), "Ваш аккаунт был успешно удален", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(FarSharingApp.getContext(), WelcomeActivity.class));
                            } else {
                                Toast.makeText(FarSharingApp.getContext(), "Не удалось удалить аккаунт", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                            t.printStackTrace();
                            Toast.makeText(FarSharingApp.getContext(), "Не удалось связаться с сервером", Toast.LENGTH_LONG).show();
                        }
                    });
                    startActivity(new Intent(FarSharingApp.getContext(), WelcomeActivity.class));
                })
                .setNegativeButton("Нет", (dialog, id) -> {
                    Toast.makeText(FarSharingApp.getContext(), "Действие отменено", Toast.LENGTH_LONG).show();
                });
        return builder.create();
    }
}