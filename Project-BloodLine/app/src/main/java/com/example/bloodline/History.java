package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class History extends AppCompatActivity implements View.OnClickListener {

    Button backToMainFromHistory, seeFullHistory, saveChangesStatus, saveChangesData;
    RadioGroup radioGroup1, radioGroup2;
    RadioButton radioButton1, radioButton2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        backToMainFromHistory = (Button) findViewById(R.id.backToMainFromHistory);
        seeFullHistory = (Button) findViewById(R.id.seeFullHistory);
        saveChangesStatus = (Button) findViewById(R.id.applyChange2);
        saveChangesData = (Button) findViewById(R.id.applyChange);
        radioGroup1 = findViewById(R.id.radioGroup2);
        radioGroup2 = findViewById(R.id.radioGroup1);

        saveChangesStatus.setOnClickListener(this);
        saveChangesData.setOnClickListener(this);
        backToMainFromHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });

        seeFullHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewDonationHistory.class);
                startActivity(intent);

            }
        });

    }

    public void saveChangesStatus(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        int radioID1 = radioGroup1.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioID1);
        Log.i("First radio :", radioButton1.getText().toString());
        String status = radioButton1.getText().toString();
        String username = SharedPrefManager.getInstance(this).getUsername();
        Log.i("username", username);

        if (status.equals("")) {
            Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_INSERTSTATUS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            //we need to pass the json message
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Log.e("anyText", response);

                                Toast.makeText(History.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


                            } catch (JSONException e) {
                                Log.i("tagconvertstr", "[" + response + "]");
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(History.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("status", status);
                    return params;

                }
            };

            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add(stringRequest);

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        }

    }

    public void addDonationDate(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        int radioID2 = radioGroup2.getCheckedRadioButtonId();
        radioButton2 = findViewById(radioID2);
        Log.i("Second radio :", radioButton2.getText().toString());
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);
        Log.i("Last donation :", formattedDate);

        String donationDate = df.format(c);
        String username = SharedPrefManager.getInstance(this).getUsername();

        if (donationDate.equals("")) {
            Toast.makeText(this, "Please select an option!", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_DONATION,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            //we need to pass the json message
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Log.e("anyText", response);

                                Toast.makeText(History.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


                            } catch (JSONException e) {
                                Log.i("tagconvertstr", "[" + response + "]");
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(History.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("donationDate", donationDate);
                    return params;

                }
            };

            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add(stringRequest);

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

        }


    }

    @Override
    public void onClick(View v) {
        if(v == saveChangesStatus) {
            saveChangesStatus();
        }

        if(v == saveChangesData) {
            addDonationDate();
        }

    }
}