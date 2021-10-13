package com.example.bloodline;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class JsonDataViewHolder extends RecyclerView.ViewHolder {

    TextView fullname,contact,blood,location, status;

    public JsonDataViewHolder(@NonNull View itemView) {
        super(itemView);

        fullname = itemView.findViewById(R.id.rcy_name);
        contact = itemView.findViewById(R.id.rcy_contact);
        blood = itemView.findViewById(R.id.rcy_blood);
        location = itemView.findViewById(R.id.rcy_location);
        status = itemView.findViewById(R.id.rcy_status);
    }


}
