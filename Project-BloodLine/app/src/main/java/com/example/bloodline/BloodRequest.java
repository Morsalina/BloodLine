package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BloodRequest extends AppCompatActivity {

    public RecyclerView recyclerView;
    public UsersAdapter usersAdapter;
    Button BackToMainMenu ;
    TextView errorText;

    ArrayList<JsonDataList> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_request);

        BackToMainMenu = (Button)findViewById(R.id.BackToMainMenu);
        errorText = (TextView) findViewById(R.id.ErrorMessageShow);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SearchData();





        arrayList = new ArrayList<JsonDataList>();
//        JsonFetch jsonFetch = new JsonFetch();
//        jsonFetch.execute();
//        JsonAdapter jsonAdapter = new JsonAdapter(arrayList, getApplicationContext());
//        recyclerView.setAdapter(jsonAdapter);
        BackToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchBlood.class);
                startActivity(intent);

            }
        });

    }

    private void SearchData(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        progressDialog.show();
        String data = getIntent().getStringExtra("blood");
        String loc = getIntent().getStringExtra("location");
        System.out.println(data);
        System.out.println(loc);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_SEARCH,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        System.out.println(response);
                        try {
                            JSONArray parent = new JSONArray(response);
                            int i= 0;
                            if(parent.length() <=0){
                                errorText.setText("There is no such result");
                            }
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
                            JsonAdapter jsonAdapter = new JsonAdapter(arrayList, getApplicationContext());
                            recyclerView.setAdapter(jsonAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(BloodRequest.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("blood", data);
                params.put("location", loc);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}