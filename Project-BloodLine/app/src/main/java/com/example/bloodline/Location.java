package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Location extends AppCompatActivity {

    private Spinner locationTextView;
    Button continueLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationTextView = findViewById(R.id.locationSignup);
        continueLocation = (Button) findViewById(R.id.continueButton5);

        String[] locations = getResources().getStringArray(R.array.locationName);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,locations);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTextView.setAdapter(adapter);

        continueLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String locations = locationTextView.getSelectedItem().toString();
                Log.i("Location", locations);
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });
    }
}