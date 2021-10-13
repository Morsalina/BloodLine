package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HelpLine extends AppCompatActivity {
    ListView helpLineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_line);

        helpLineList = (ListView)findViewById(R.id.helpView);

        HashMap<String, String> emergencyNumbers = new HashMap<>();
        emergencyNumbers.put("National Emergency Helpline", "999");
        emergencyNumbers.put("Emergency Hotline-1", "333");
        emergencyNumbers.put("IEDCR", "10655");
        emergencyNumbers.put("Ambulance Service", "16263");
        emergencyNumbers.put("Expert Health Line", "09611677777");

        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, hashMapList, R.layout.helpline_layout, new String[]{"first line","second line"},new int[]{R.id.layoutForHelpline, R.id.layoutForHelpline2});

        Iterator it = emergencyNumbers.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String, String> result = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            result.put("first line", pair.getKey().toString());
            result.put("second line", pair.getValue().toString());
            hashMapList.add(result);

        }
        helpLineList.setAdapter(adapter);

//        String[] helpLine = getResources().getStringArray(R.array.helpLine);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(HelpLine.this, R.layout.helpline_layout, R.id.layoutForHelpline, helpLine);
//        helpLineList.setAdapter(arrayAdapter);
    }
}