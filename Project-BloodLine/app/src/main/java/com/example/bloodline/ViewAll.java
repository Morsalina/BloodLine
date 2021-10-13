package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
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

public class ViewAll extends AppCompatActivity {

    public RecyclerView recyclerView;
    public UsersAdapter usersAdapter;
    Button BackToMainMenu ;
    TextView errorText;

    ArrayList<JsonDataList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        BackToMainMenu = (Button) findViewById(R.id.BackToMainMenuFromViewAll);
        recyclerView = findViewById(R.id.recyclerViewAll);

        arrayList = new ArrayList<JsonDataList>();
        JsonFetch jsonFetch = new JsonFetch();
        jsonFetch.execute();
        BackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchBlood.class);
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
                URL url = new URL("http://192.168.0.102/ProjectAndroid/ShowAllUsers.php");
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
                    String blood = child.getString("blood");
                    String location = child.getString("location");
                    String status = child.getString("status");

                    arrayList.add(new JsonDataList(fullname, contact, blood, location, status));
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

            JsonAdapter jsonAdapter = new JsonAdapter(arrayList, getApplicationContext());
            recyclerView.setAdapter(jsonAdapter);

        }
    }

}