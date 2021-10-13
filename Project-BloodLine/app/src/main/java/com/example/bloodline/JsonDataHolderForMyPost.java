package com.example.bloodline;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JsonDataHolderForMyPost extends RecyclerView.ViewHolder {

    TextView username, contact, postCreationTime, writtenPost,bloodAmount, hospitalName,bloodStatus;
    ImageButton deleteButton;

    public JsonDataHolderForMyPost(@NonNull View itemView) {
        super(itemView);

        username = itemView.findViewById(R.id.rcy_showUsernameMyPost);
        contact = itemView.findViewById(R.id.rcy_phnNumberMyPost);
        postCreationTime = itemView.findViewById(R.id.rcy_postingTimeMyPost);
        writtenPost = itemView.findViewById(R.id.rcy_sharedPostMyPost);
        bloodAmount = itemView.findViewById(R.id.rcy_amountBloodMyPost);
        hospitalName = itemView.findViewById(R.id.rcy_hospitalMyPost);
        bloodStatus = itemView.findViewById(R.id.rcy_bloodStatus);

    }
}
