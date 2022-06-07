package ru.vsu.cs.farsharing.activity.item;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.vsu.cs.farsharing.R;


public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<CarListItem> list;
    private final OnCarListItemListener onCarListItemListener;

    public CarListAdapter(@NonNull Activity context, List<CarListItem> list, OnCarListItemListener onCarListItemListener) {
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
        CarListItem item = list.get(position);
        holder.tvBrand.setText(item.getBrand());
        holder.tvModel.setText(item.getModel());
        holder.tvStateNumber.setText(item.getStateNumber());
        holder.tvStatus.setText(item.getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView tvBrand;
        final TextView tvModel;
        final TextView tvStateNumber;
        final TextView tvStatus;
        OnCarListItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnCarListItemListener onItemListener) {
            super(itemView);
            this.tvBrand = itemView.findViewById(R.id.brandListItem);
            this.tvModel = itemView.findViewById(R.id.modelListItem);
            this.tvStateNumber = itemView.findViewById(R.id.stateNumberListItem);
            this.tvStatus = itemView.findViewById(R.id.statusListItem);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
}
