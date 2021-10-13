package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends AppCompatActivity {

    TextView textShowUsername, textShowFullname, textShowContact, textShowBlood, textShowLocation, textShowUserID;
    Button backToMenu, buttonForUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        textShowUserID = (TextView) findViewById(R.id.showUserID);
        textShowUsername = (TextView) findViewById(R.id.showUsername);
        textShowFullname = (TextView) findViewById(R.id.showFullname);
        textShowContact = (TextView) findViewById(R.id.showContact);
        textShowBlood = (TextView) findViewById(R.id.showBlood);
        textShowLocation = (TextView) findViewById(R.id.showLocation);
        backToMenu = (Button) findViewById(R.id.buttonBack);
        buttonForUpdate = (Button) findViewById(R.id.buttonUpdate);

        backToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
                finish();
            }
        });

        buttonForUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UpdateUserInfo.class);
                startActivity(intent);

            }
        });

       // textShowUserID.setText(SharedPrefManager.getInstance(this).getUserID());
        String user = SharedPrefManager.getInstance(this).getUsername();
        System.out.println(user);
        int id = SharedPrefManager.getInstance(this).getUserID();
        System.out.println(id);
        String userID = Integer.toString(id);
        System.out.println(userID);
        textShowUserID.setText(userID);
        textShowUsername.setText(SharedPrefManager.getInstance(this).getUsername());
        textShowFullname.setText(SharedPrefManager.getInstance(this).getUserFullname());
        textShowContact.setText(SharedPrefManager.getInstance(this).getUserContact());
        textShowBlood.setText(SharedPrefManager.getInstance(this).getUserBlood());
        textShowLocation.setText(SharedPrefManager.getInstance(this).getUserLocation());


    }
}