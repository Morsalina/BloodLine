package com.example.bloodline;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText fullnameText, usernameText, passwordText, contactText;
    Button signupButton;
    ImageButton backButton;
    Spinner bloodText, locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullnameText = findViewById(R.id.fullname);
        usernameText = findViewById(R.id.username1);
        passwordText = findViewById(R.id.password1);
        contactText = findViewById(R.id.phoneNumber);
        bloodText = findViewById(R.id.spinnerBlood);
        locationText = findViewById(R.id.spinnerLocation);
        signupButton = (Button) findViewById(R.id.SignupButton);
        backButton = (ImageButton) findViewById(R.id.backButton);

        signupButton.setOnClickListener(this);

        String[] BloodGroup = getResources().getStringArray(R.array.bloodGroup);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,BloodGroup);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodText.setAdapter(adapter);

        String[] locations = getResources().getStringArray(R.array.locationName);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,locations);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationText.setAdapter(adapter1);

    }

    private void registerUser() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        final String fullname, username, password, contact, bloodgroup, location;

        bloodgroup = bloodText.getSelectedItem().toString();
        location = locationText.getSelectedItem().toString();

        fullname = fullnameText.getText().toString().trim();
        username = usernameText.getText().toString().trim();
        password = passwordText.getText().toString().trim();
        contact = contactText.getText().toString().trim();

        if (fullname.equals("") || username.equals("") || password.equals("") || contact.equals("") || bloodgroup.equals("") || location.equals("")) {
            Toast.makeText(this, "Please Fill out all the fields !", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            //we need to pass the json message
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Log.e("anyText", response);

                                Toast.makeText(Register.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();


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
                            Toast.makeText(Register.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("fullname", fullname);
                    params.put("username", username);
                    params.put("password", password);
                    params.put("contact", contact);
                    params.put("blood", bloodgroup);
                    params.put("location", location);
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
         if(v== signupButton){
             registerUser();
         }

    }
}