package com.example.bloodline;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JsonDataHolderForHistory  extends RecyclerView.ViewHolder {

    TextView donationDate;

    public JsonDataHolderForHistory(@NonNull View itemView) {
        super(itemView);
        donationDate = itemView.findViewById(R.id.rcy_date);

    }
}
