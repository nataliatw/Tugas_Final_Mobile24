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
import com.example.f1raceandcircuitinformation.Activity.RaceActivity;
import com.example.f1raceandcircuitinformation.DatabaseHelper;
import com.example.f1raceandcircuitinformation.Response.Race;
import com.example.f1raceandcircuitinformation.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.ViewHolder> {

    private Context context;
    private final ArrayList<Race> races;
    private ArrayList<Race> filteredRaceList;

    public RaceAdapter(Context context, ArrayList<Race> races) {
        this.context = context;
        this.races = races;
        this.filteredRaceList = new ArrayList<>(races);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_race, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Race race = filteredRaceList.get(position);
        holder.racename.setText(race.getRaceName());
        holder.racetime.setText(race.getDate());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), RaceActivity.class);
            intent.putExtra("raceId", race.getCircuit().getCircuitId());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return filteredRaceList.size();
    }

    public void filter(String query) {
        filteredRaceList.clear();
        if (query.isEmpty()) {
            filteredRaceList.addAll(races);
        } else {
            for (Race race : races) {
                if (race.getRaceName().toLowerCase().contains(query.toLowerCase())) {
                    filteredRaceList.add(race);
                }
            }
        }
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView racename, racetime;
        ImageView raceImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            racename = itemView.findViewById(R.id.tv_race1);
            racetime = itemView.findViewById(R.id.tv_race2);
            raceImage = itemView.findViewById(R.id.iv_race);
        }
    }
}
