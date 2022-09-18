package ru.vsu.cs.farsharing.activity.admin.client_list;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.vsu.cs.farsharing.R;
import ru.vsu.cs.farsharing.model.entity.ClientEntity;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<ClientEntity> list;
    private final OnClientListItemListener onClientListItemListener;

    public ClientListAdapter(@NonNull Activity context, List<ClientEntity> list, OnClientListItemListener onClientListItemListener) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.onClientListItemListener = onClientListItemListener;
    }

    @NonNull
    @Override
    public ClientListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.client_list_item, parent, false);
        return new ClientListAdapter.ViewHolder(view, onClientListItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientListAdapter.ViewHolder holder, int position) {
        ClientEntity item = list.get(position);

        //holder.id.setText(item.getUid().toString());
        holder.email.setText(item.getUser().getEmail());
        String fullName = String.format("%s %s %s", item.getLastName(), item.getFirstName(), item.getMidName());
        holder.fullName.setText(fullName);
        String licenseNumberText = "Номер водительских прав: " + item.getLicense();
        holder.licenseNumber.setText(licenseNumberText);
        String status;
        int statusColor;
        switch(item.getStatus()){
            case DEFAULT:
                status = "Активный";
                statusColor = Color.GREEN;
                break;
            case BANNED:
                status = "Забанен";
                statusColor = Color.RED;
                break;
            default:
                status = "Нет данных";
                statusColor = Color.GRAY;
                break;
        }
        holder.status.setText(status);
        holder.status.setTextColor(statusColor);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //final TextView id;
        final TextView fullName;
        final TextView email;
        final TextView licenseNumber;
        final TextView status;
        final OnClientListItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnClientListItemListener onItemListener) {
            super(itemView);
            //this.id = itemView.findViewById(R.id.clientListId);
            this.email = itemView.findViewById(R.id.clientListEmail);
            this.fullName = itemView.findViewById(R.id.clientListFullName);
            this.licenseNumber = itemView.findViewById(R.id.clientListLicense);
            this.status = itemView.findViewById(R.id.clientListStatus);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
