package com.example.bloodline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JsonAdapterForHistory extends RecyclerView.Adapter<JsonDataHolderForHistory> {

    ArrayList<JsonDataList> list;
    Context context;

    public JsonAdapterForHistory(ArrayList<JsonDataList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public JsonAdapterForHistory() {
    }

    @NonNull
    @Override
    public JsonDataHolderForHistory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.allhistorylist, parent, false);
        return new JsonDataHolderForHistory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonDataHolderForHistory holder, int position) {
        JsonDataList currentData = list.get(position);
        holder.donationDate.setText(currentData.getDonationDate());

    }

    @Override
    public int getItemCount() {

            return list.size();
    }
}
