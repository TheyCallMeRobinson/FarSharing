package ru.vsu.cs.farsharing.activity.main;

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
import ru.vsu.cs.farsharing.model.entity.CarEntity;


public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<CarEntity> list;
    private final OnCarListItemListener onCarListItemListener;

    public CarListAdapter(@NonNull Activity context, List<CarEntity> list, OnCarListItemListener onCarListItemListener) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.onCarListItemListener = onCarListItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.car_list_item, parent, false);
        return new ViewHolder(view, onCarListItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarEntity item = list.get(position);

        holder.brand.setText(item.getBrand());
        holder.model.setText(item.getModel());
        holder.stateNumber.setText(item.getStateNumber());
        holder.status.setText(item.getIsAvailable() ? "Свободен" : "Занят");
        holder.status.setTextColor(item.getIsAvailable() ? Color.GREEN : Color.RED);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView brand;
        final TextView model;
        final TextView stateNumber;
        final TextView status;
        OnCarListItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnCarListItemListener onItemListener) {
            super(itemView);
            this.brand = itemView.findViewById(R.id.brandListItem);
            this.model = itemView.findViewById(R.id.modelListItem);
            this.stateNumber = itemView.findViewById(R.id.stateNumberListItem);
            this.status = itemView.findViewById(R.id.statusListItem);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
