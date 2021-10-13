package com.example.bloodline;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class JsonDataHolderForTimeline extends RecyclerView.ViewHolder {

    TextView fullname,contact,postCreationTime, writtenPost, bloodAmount, hospitalName;

    public JsonDataHolderForTimeline(View itemView) {
        super(itemView);
        fullname = itemView.findViewById(R.id.rcy_showUsername);
        contact = itemView.findViewById(R.id.rcy_phnNumber);
        postCreationTime = itemView.findViewById(R.id.rcy_postingTime);
        writtenPost = itemView.findViewById(R.id.rcy_sharedPost);
        bloodAmount = itemView.findViewById(R.id.rcy_amountBlood);
        hospitalName = itemView.findViewById(R.id.rcy_hospital);

    }
}
