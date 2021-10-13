package com.example.bloodline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JsonAdapter extends RecyclerView.Adapter<JsonDataViewHolder>{

    ArrayList<JsonDataList> list;
    Context context;

    public JsonAdapter(ArrayList<JsonDataList> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public JsonAdapter() {
    }

    @NonNull

    @Override
    public JsonDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.userlistc_ardview, parent, false);
        return new JsonDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonDataViewHolder holder, int position) {
        JsonDataList currentData = list.get(position);

        holder.fullname.setText(currentData.getFullname());
        holder.contact.setText(currentData.getContact());
        holder.blood.setText(currentData.getBlood());
        holder.location.setText(currentData.getLocation());
        holder.status.setText(currentData.getStatus());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
