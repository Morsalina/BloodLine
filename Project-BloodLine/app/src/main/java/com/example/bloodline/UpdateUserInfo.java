package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserInfo extends AppCompatActivity implements View.OnClickListener{

    EditText editTextFullname, editTextLocation, editTextContact;
    TextView showUsername;
    Button updateButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_info);

        editTextFullname = (EditText) findViewById(R.id.editTextFullname);
        editTextContact = (EditText) findViewById(R.id.editTextContact);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        showUsername = (TextView) findViewById(R.id.showUsername1);
        updateButton = (Button) findViewById(R.id.UpdateButton);
        cancelButton = (Button) findViewById(R.id.buttonCancel);
        updateButton.setOnClickListener(this);


        showUsername.setText(SharedPrefManager.getInstance(this).getUsername());
        editTextFullname.setText(SharedPrefManager.getInstance(this).getUserFullname());
        editTextContact.setText(SharedPrefManager.getInstance(this).getUserContact());
        editTextLocation.setText(SharedPrefManager.getInstance(this).getUserLocation());

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);

            }
        });
    }

    public void UpdateInfo(){
        String fullname, contact, username,location;
        fullname = editTextFullname.getText().toString();
        username = showUsername.getText().toString();
        contact = editTextContact.getText().toString();
        location = editTextLocation.getText().toString();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating..");
        progressDialog.show();

        if (fullname.equals("") || contact.equals("") || location.equals("") || username.equals("")) {
            Toast.makeText(this, "Please Fill out all the fields !", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();
            System.out.println(fullname);
            System.out.println(contact);
            System.out.println(location);

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_UPDATE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            //we need to pass the json message
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Log.e("anyText", response);

                                Toast.makeText(UpdateUserInfo.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);



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
                            Toast.makeText(UpdateUserInfo.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("fullname", fullname);
                    params.put("username", username);
                    params.put("contact", contact);
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
        if(v == updateButton){
            UpdateInfo();
        }

    }
}