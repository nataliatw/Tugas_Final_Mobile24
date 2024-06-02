package com.example.f1raceandcircuitinformation.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.f1raceandcircuitinformation.Activity.DriversActivity;
import com.example.f1raceandcircuitinformation.DatabaseHelper;
import com.example.f1raceandcircuitinformation.Response.Driver;
import com.example.f1raceandcircuitinformation.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<Driver> drivers;
    private ArrayList<Driver> filteredDriversList;

    public DriversAdapter(Context context, ArrayList<Driver> drivers) {
        this.context = context;
        this.drivers = drivers;
        this.filteredDriversList = new ArrayList<>(drivers);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_drivers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Driver driver = filteredDriversList.get(position);
        holder.name.setText(driver.getGivenName() + " " + driver.getFamilyName());
        holder.nationality.setText(driver.getNationality());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DriversActivity.class);
            intent.putExtra("driverId", driver.getDriverId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredDriversList.size();
    }

    public void filter(String query) {
        filteredDriversList.clear();
        if (query.isEmpty()) {
            filteredDriversList.addAll(drivers);
        } else {
            for (Driver driver : drivers) {
                if (driver.getGivenName().toLowerCase().contains(query.toLowerCase()) ||
                        driver.getFamilyName().toLowerCase().contains(query.toLowerCase())) {
                    filteredDriversList.add(driver);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, nationality;
        ImageView driverImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_namadriver);
            nationality = itemView.findViewById(R.id.tv_negaradriver);
            driverImage = itemView.findViewById(R.id.iv_drivers);
        }
    }
}
