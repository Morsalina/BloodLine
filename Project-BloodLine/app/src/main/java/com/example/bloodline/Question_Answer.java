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

public class Question_Answer extends AppCompatActivity {

    ListView qaSectionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_answer);

        qaSectionList = (ListView) findViewById(R.id.qaSection);

        HashMap<String, String> questionAnswer = new HashMap<>();
        questionAnswer.put("After how many days blood can be donated?", "120 days after each donation");
        questionAnswer.put("Minimum age for donating blood? ", "18 years");
        questionAnswer.put("Minimum weight that a donor should have for donating? ", "47 kg");
        questionAnswer.put("Is there any side effect of blood donation?", "No, there is no side-effect");
        questionAnswer.put("How much blood is taken normally?", "380 ml to 400 ml");
        questionAnswer.put("How much time is needed to donate blood?", "Normally 5 minute to 7 minutes");
        questionAnswer.put("Can a diabetic patient donate blood?", "No, they should not donate unless it is emergency");
        questionAnswer.put("Can I donate blood after taking vaccine?", "No, you must wait at least for 4 weeks");
        questionAnswer.put("Can a person can donate blood if he has high blood pressure?", "Yes only if blood pressure is in under control");
        questionAnswer.put("After how many days of delivery a mother can donate blood?", "15 months minimum");
        questionAnswer.put("Can I donate blood after smoking or drinking?", "No, you can not. You must wait 24 hours");
        questionAnswer.put("Is the process painful?", "No");

        List<HashMap<String, String>> hashMapList = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, hashMapList, R.layout.helpline_layout, new String[]{"first line","second line"},new int[]{R.id.layoutForHelpline, R.id.layoutForHelpline2});


        Iterator it = questionAnswer.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String, String> result = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            result.put("first line", pair.getKey().toString());
            result.put("second line", pair.getValue().toString());
            hashMapList.add(result);

        }
        qaSectionList.setAdapter(adapter);


    }
}