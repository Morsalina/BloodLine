package com.example.bloodline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JsonAdapterForTimeline  extends RecyclerView.Adapter<JsonDataHolderForTimeline> {

    ArrayList<JsonDataList> list;
    Context context;
    public JsonAdapterForTimeline(ArrayList<JsonDataList> list, Context context){
        this.list = list;
        this.context = context;
    }

    public JsonAdapterForTimeline() {
    }

    @Override
    public JsonDataHolderForTimeline onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_for_timeline, parent, false);
        return new JsonDataHolderForTimeline(view);
    }



    @Override
    public void onBindViewHolder(@NonNull JsonDataHolderForTimeline holder, int position) {

        JsonDataList currentData = list.get(position);

        holder.fullname.setText(currentData.getFullname());
        holder.contact.setText(currentData.getContact());
        holder.postCreationTime.setText(currentData.getPostCreationTime());
        holder.writtenPost.setText(currentData.getWrittenPost());
        holder.bloodAmount.setText(currentData.getBloodAmount());
        holder.hospitalName.setText(currentData.getHospitalName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
