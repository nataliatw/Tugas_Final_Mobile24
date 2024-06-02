package com.example.f1raceandcircuitinformation.Fragment;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.f1raceandcircuitinformation.Adapter.HistoryAdapter;
import com.example.f1raceandcircuitinformation.DatabaseHelper;
import com.example.f1raceandcircuitinformation.R;
import com.example.f1raceandcircuitinformation.Response.HistoryItem;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private RecyclerView rvHistories;
    private HistoryAdapter historyAdapter;
    private ArrayList<HistoryItem> historyList;
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        rvHistories = view.findViewById(R.id.rv_histories);
        rvHistories.setLayoutManager(new LinearLayoutManager(getContext()));

        databaseHelper = new DatabaseHelper(getContext());

        // Mengambil data dari database
        historyList = new ArrayList<>();
        Cursor cursor = databaseHelper.getAllHistory();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.getColumnHistoryId()));
                @SuppressLint("Range") String itemId = cursor.getString(cursor.getColumnIndex(DatabaseHelper.getColumnItemId()));
                @SuppressLint("Range") String itemType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.getColumnHistoryId()));
                @SuppressLint("Range") String lastSeen = cursor.getString(cursor.getColumnIndex(DatabaseHelper.getColumnItemType()));
                // Buat objek HistoryItem dan tambahkan ke dalam historyList
                historyList.add(new HistoryItem(id, itemId, itemType, lastSeen));
            } while (cursor.moveToNext());
            cursor.close();
        }

        // Inisialisasi dan atur adapter
        historyAdapter = new HistoryAdapter(getContext(), historyList); // Ubah konstruktor
        rvHistories.setAdapter(historyAdapter);

        return view;
    }
}
