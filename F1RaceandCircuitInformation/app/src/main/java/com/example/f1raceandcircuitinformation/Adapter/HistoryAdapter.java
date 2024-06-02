package com.example.f1raceandcircuitinformation.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f1raceandcircuitinformation.R;
import com.example.f1raceandcircuitinformation.Response.HistoryItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<HistoryItem> historyList;

    public HistoryAdapter(Context context, ArrayList<HistoryItem> historyList) {
        this.context = context;
        this.historyList = historyList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryItem historyItem = historyList.get(position);

        // Mendapatkan informasi dari HistoryItem
        String name = historyItem.getName();
        String date = historyItem.getDate();
        String lastSeen = historyItem.getLastSeen();

        // Mengatur teks untuk TextView
        holder.tvHistory1.setText(name);
        holder.tvHistory2.setText(date);
        holder.tvHistory3.setText(lastSeen);
    }


    @Override
    public int getItemCount() {
        return historyList.size(); // Mengembalikan jumlah item di historyList
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivHistory;
        public TextView tvHistory1;
        public TextView tvHistory2;
        public TextView tvHistory3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivHistory = itemView.findViewById(R.id.iv_history);
            tvHistory1 = itemView.findViewById(R.id.tv_history1);
            tvHistory2 = itemView.findViewById(R.id.tv_history2);
            tvHistory3 = itemView.findViewById(R.id.tv_history3);
        }
    }
}
