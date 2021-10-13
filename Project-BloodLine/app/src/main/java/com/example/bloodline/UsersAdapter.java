package com.example.bloodline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    Context context;
    ArrayList<GetSetUserInfo> userList;

    public UsersAdapter(Context context, ArrayList<GetSetUserInfo> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View userLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.userlistc_ardview, parent, false);

        return new UserHolder(userLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserHolder holder, int position) {

        GetSetUserInfo getSetUserInfo = userList.get(position);
        holder.fullname.setText(getSetUserInfo.getFullname());
        holder.contact.setText(getSetUserInfo.getContact());
        holder.blood.setText(getSetUserInfo.getBlood());
        holder.location.setText(getSetUserInfo.getLocation());

    }

    @Override
    public int getItemCount() {
        System.out.println(userList.size());
        return userList.size();

    }

    public class UserHolder extends RecyclerView.ViewHolder {

        TextView fullname, contact, blood, location;
        public UserHolder(@NonNull View itemView) {
            super(itemView);

            fullname = itemView.findViewById(R.id.rcy_name);
            contact = itemView.findViewById(R.id.rcy_contact);
            blood = itemView.findViewById(R.id.rcy_blood);
            location = itemView.findViewById(R.id.rcy_location);
        }
    }
}
