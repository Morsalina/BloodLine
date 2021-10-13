package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Volunteers extends AppCompatActivity {

    ListView volunteerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteers);

        volunteerList = (ListView) findViewById(R.id.volunteerList);
        HashMap<String, String> volunteers = new HashMap<>();
        volunteers.put("Onamika Hossain", "01734512487");
        volunteers.put("Morsalina ", "01875475773");
        volunteers.put("Urbana Musharrat Haider ", "01534198756");

        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, hashMapList, R.layout.helpline_layout, new String[]{"first line","second line"},new int[]{R.id.layoutForHelpline, R.id.layoutForHelpline2});


        Iterator it = volunteers.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String, String> result = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            result.put("first line", pair.getKey().toString());
            result.put("second line", pair.getValue().toString());
            hashMapList.add(result);

        }
        volunteerList.setAdapter(adapter);

    }
}