package com.example.bloodline;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JsonAdapterForMyPost extends RecyclerView.Adapter<JsonDataHolderForMyPost> {

    ArrayList<JsonDataList> list;
    Context context;
    public JsonAdapterForMyPost(ArrayList<JsonDataList> list, Context context){
        this.list = list;
        this.context = context;
    }

    public JsonAdapterForMyPost() {
    }

    @Override
    public JsonDataHolderForMyPost onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_for_my_posts, parent, false);
        return new JsonDataHolderForMyPost(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonDataHolderForMyPost holder, int position) {

        JsonDataList currentData = list.get(position);

        holder.username.setText(currentData.getFullname());
        holder.contact.setText(currentData.getContact());
        holder.postCreationTime.setText(currentData.getPostCreationTime());
        holder.writtenPost.setText(currentData.getWrittenPost());
        holder.bloodAmount.setText(currentData.getBloodAmount());
        holder.hospitalName.setText(currentData.getHospitalName());
        holder.bloodStatus.setText(currentData.getBloodStatus());

        String status = currentData.getBloodStatus();
        System.out.println(status);

            holder.bloodStatus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PopupWindowForBloodStatus.class);
                    intent.putExtra("postCreationTime", currentData.getPostCreationTime());
                    intent.putExtra("bloodStatus", currentData.getBloodStatus());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
