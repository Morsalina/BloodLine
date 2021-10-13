package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TimelineForPosts extends AppCompatActivity {

    RecyclerView recyclerViewTimeline;
    Button buttonBackToMain;
    TextView errorText;
    ArrayList<JsonDataList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_for_posts);

        recyclerViewTimeline = findViewById(R.id.recyclerViewTimeline);
        buttonBackToMain = (Button) findViewById(R.id.backToMainFromTimeline);

        recyclerViewTimeline.setHasFixedSize(true);
        recyclerViewTimeline.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<JsonDataList>();
        TimelineForPosts.JsonFetch jsonFetch = new TimelineForPosts.JsonFetch();
        jsonFetch.execute();

        buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);

            }
        });
    }

    public class JsonFetch extends AsyncTask<String, String, String> {

        HttpURLConnection httpURLConnection = null;
        String mainLine;
        BufferedReader bufferedReader = null;

        @Override
        protected String doInBackground(String... strings) {



            try {
                URL url = new URL("http://192.168.0.102/ProjectAndroid/SeeAllPosts.php");
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
//
                InputStream inputStream = httpURLConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                //response
                StringBuffer stringBuffer = new StringBuffer();

                String line ="";

                while((line = bufferedReader.readLine()) != null){

                    stringBuffer.append(line);
                }

                mainLine = stringBuffer.toString();

                JSONArray parent = new JSONArray(mainLine);
                int i= 0;
                while( i < parent.length()){

                    JSONObject child = parent.getJSONObject(i);
                    String fullname = child.getString("fullname");
                    String contact = child.getString("contact");
                    String postCreationTime = child.getString("postCreationTime");
                    String writtenPost = child.getString("writtenPost");
                    String bloodAmount = child.getString("bloodAmount");
                    String hospitalName = child.getString("hospitalName");

                    arrayList.add(new JsonDataList(fullname, contact, postCreationTime, writtenPost,bloodAmount,hospitalName));
                    i++;

                }


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            JsonAdapterForTimeline jsonAdapterForTimeline = new JsonAdapterForTimeline(arrayList, getApplicationContext());
            recyclerViewTimeline.setAdapter(jsonAdapterForTimeline);

        }
    }
}