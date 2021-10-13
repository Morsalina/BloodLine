package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class CreatePost extends AppCompatActivity implements View.OnClickListener{
    EditText createdPost;
    TextView countOfCharacters;
    Button cancelCreatePost, shareCreatedPost, backToMainMenu;
    Spinner bloodAmountSpin, hospitalNameSpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        createdPost = findViewById(R.id.createdPost);
        countOfCharacters = findViewById(R.id.count);
        cancelCreatePost = (Button) findViewById(R.id.cancelButtonCreatePost);
        shareCreatedPost = (Button) findViewById(R.id.buttonSharePost);
        backToMainMenu = (Button) findViewById(R.id.backToMainFromCreatePost);
        bloodAmountSpin = (Spinner) findViewById(R.id.spinnerBloodAmount);
        hospitalNameSpin = (Spinner) findViewById(R.id.spinnerHospitalName);

        String[] AmountOfBlood = getResources().getStringArray(R.array.bloodAmount);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,AmountOfBlood);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodAmountSpin.setAdapter(adapter);

        String[] NameOfHospital = getResources().getStringArray(R.array.HospitalName);
        ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item,NameOfHospital);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hospitalNameSpin.setAdapter(adapter1);

        shareCreatedPost.setOnClickListener(this);


        createdPost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String typedCharacter = createdPost.getText().toString();
               // countOfCharacters.setText(typedCharacter);
                int sizeOfTypedCharacter = typedCharacter.length();
                String sizeOf = Integer.toString(sizeOfTypedCharacter);
                Log.i("number of characters", sizeOf);
                countOfCharacters.setText(sizeOf);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        cancelCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createdPost.setText("");

            }
        });

        backToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void SharePost(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        String username = SharedPrefManager.getInstance(this).getUsername();
        String contact = SharedPrefManager.getInstance(this).getUserContact();
        String fullname = SharedPrefManager.getInstance(this).getUserFullname();
        String bloodAmount = bloodAmountSpin.getSelectedItem().toString();
        String hospitalName = hospitalNameSpin.getSelectedItem().toString();
        System.out.println(bloodAmount);
        System.out.println(hospitalName);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        String postCreationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        System.out.println(postCreationTime);

        String writtenPost = createdPost.getText().toString();

        if (writtenPost.equals("")) {
            Toast.makeText(this, "Please write something!", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_POST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            //we need to pass the json message
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Log.e("anyText", response);
                                createdPost.setText("");
                                Toast.makeText(CreatePost.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();


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
                            Toast.makeText(CreatePost.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("writtenPost", writtenPost);
                    params.put("postCreationTime", postCreationTime);
                    params.put("contact", contact);
                    params.put("fullname", fullname);
                    params.put("bloodAmount", bloodAmount);
                    params.put("hospitalName", hospitalName);
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
        if(v == shareCreatedPost){
            SharePost();
        }
    }
}