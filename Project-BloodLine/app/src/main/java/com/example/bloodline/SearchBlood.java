package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.io.InputStream;

public class SearchBlood extends AppCompatActivity {

    Button buttonForSearch, buttonForMainMenu, buttonViewAll;
    Spinner bloodTextView, locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_blood);

        buttonForSearch = (Button) findViewById(R.id.buttonSearch);
        buttonForMainMenu = (Button) findViewById(R.id.buttonBackToMainMenu);
        buttonViewAll = (Button) findViewById(R.id.ViewAll);
        bloodTextView = findViewById(R.id.bloodSpinner);
        locationTextView = findViewById(R.id.locationSpinner);

        String[] BloodGroup = getResources().getStringArray(R.array.bloodGroup);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,BloodGroup);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodTextView.setAdapter(adapter);

        String[] locations = getResources().getStringArray(R.array.locationName);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,locations);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTextView.setAdapter(adapter1);
//
//        BloodRequest sc = new BloodRequest();
//        sc.getBlood(blood);


        buttonForMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);

            }
        });

        buttonForSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String blood = bloodTextView.getSelectedItem().toString();
                System.out.println(blood);
                String loc = locationTextView.getSelectedItem().toString();
                System.out.println(loc);
                Intent intent = new Intent(getApplicationContext(), BloodRequest.class);
                intent.putExtra("blood",blood);
                intent.putExtra("location", loc);
                startActivity(intent);

            }
        });

        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewAll.class);
                startActivity(intent);

            }
        });
    }


}