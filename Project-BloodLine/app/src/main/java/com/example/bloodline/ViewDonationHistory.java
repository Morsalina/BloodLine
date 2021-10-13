package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

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
import java.util.HashMap;
import java.util.Map;

public class ViewDonationHistory extends AppCompatActivity {

    public RecyclerView recyclerView1;
    Button buttonBackToMain;
    TextView errorText;

    ArrayList<JsonDataList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donation_history);

        recyclerView1 = findViewById(R.id.recyclerViewAllHistory);
        buttonBackToMain = (Button) findViewById(R.id.backToMainFromHistory1);
        errorText = (TextView) findViewById(R.id.ErrorMessageShow1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        ViewHistory();

        arrayList = new ArrayList<JsonDataList>();

        buttonBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
      
            }
        });

    }

    private void ViewHistory(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        progressDialog.show();

        String username = SharedPrefManager.getInstance(this).getUsername();
        Log.i("name ", username);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_DONATIONLIST,
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
                                String donationDate = child.getString("donationDate");

                                arrayList.add(new JsonDataList(donationDate));
                                i++;

                            }
                            JsonAdapterForHistory jsonAdapterForHistory = new JsonAdapterForHistory(arrayList, getApplicationContext());
                            recyclerView1.setAdapter(jsonAdapterForHistory);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(ViewDonationHistory.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}