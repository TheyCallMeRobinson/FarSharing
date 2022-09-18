package ru.vsu.cs.farsharing.activity.admin.client_list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vsu.cs.farsharing.config.FarSharingApp;
import ru.vsu.cs.farsharing.databinding.ActivityClientListBinding;
import ru.vsu.cs.farsharing.model.entity.ClientEntity;

public class ClientListActivity extends AppCompatActivity implements OnClientListItemListener {
    private RecyclerView userRecyclerList;
    private ActivityClientListBinding binding;
    private List<ClientEntity> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setUpViews();
        FarSharingApp.getInstance().getClientService().getClients().enqueue(new Callback<List<ClientEntity>>() {
            @Override
            public void onResponse(@NonNull Call<List<ClientEntity>> call, @NonNull Response<List<ClientEntity>> response) {
                if (response.body() != null) {
                    clientList = response.body();
                    ClientListAdapter clientListAdapter = new ClientListAdapter(ClientListActivity.this, clientList, ClientListActivity.this);
                    LinearLayoutManager manager = new LinearLayoutManager(FarSharingApp.getContext());
                    userRecyclerList.setLayoutManager(manager);
                    userRecyclerList.setHasFixedSize(true);
                    userRecyclerList.setAdapter(clientListAdapter);
                    DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(userRecyclerList.getContext(), 1);
                    userRecyclerList.addItemDecoration(dividerItemDecoration);
                } else {
                    Snackbar.make(binding.getRoot(), "Не удалось получить список клиентов", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ClientEntity>> call, @NonNull Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setUpViews() {
        userRecyclerList = binding.userList;
    }

    @Override
    public void onItemClick(int position) {
        Intent toClientDetails = new Intent(FarSharingApp.getContext(), ClientDetailsActivity.class);
        toClientDetails.putExtra("clientUid", clientList.get(position).getUid());
        startActivity(toClientDetails);
    }
}