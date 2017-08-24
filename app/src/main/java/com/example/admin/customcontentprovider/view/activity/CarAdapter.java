package com.example.admin.customcontentprovider.view.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.customcontentprovider.R;
import com.example.admin.customcontentprovider.model.Cars;

import java.util.ArrayList;


public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    ArrayList<Cars> carList = new ArrayList<>();
    Context context;

    public CarAdapter(ArrayList<Cars> carList) {
        this.carList = carList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvModel, tvBrand, tvDescription, tvYear, tvColor;

        public ViewHolder(View itemView) {
            super(itemView);
            tvModel = (TextView) itemView.findViewById(R.id.tvModel);
            tvBrand = (TextView) itemView.findViewById(R.id.tvBrand);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            tvYear = (TextView) itemView.findViewById(R.id.tvYear);
            tvColor = (TextView) itemView.findViewById(R.id.tvColor);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cars result = carList.get(position);
        holder.tvModel.setText(result.getModel());
        holder.tvBrand.setText(result.getBrand());
        holder.tvYear.setText(String.valueOf(result.getYear()));
        holder.tvColor.setText(result.getColor());
        holder.tvDescription.setText(result.getDescription());

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

}
