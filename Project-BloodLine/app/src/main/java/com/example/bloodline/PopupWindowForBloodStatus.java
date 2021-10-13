package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

public class PopupWindowForBloodStatus extends AppCompatActivity {

    Button yesButton, noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window_for_blood_status);

        yesButton = (Button) findViewById(R.id.buttonManaged);
        noButton = (Button) findViewById(R.id.buttonUnmanaged);


        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateBloodStatus();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeeMyPosts.class);
                startActivity(intent);
                finish();
            }
        });

        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);

        int width = display.widthPixels;
        int height = display.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.4));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
    }
    public void UpdateBloodStatus(){

        String postCreationTime = getIntent().getStringExtra("postCreationTime");
        String username = SharedPrefManager.getInstance(this).getUsername();



        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Updating..");
        progressDialog.show();

        System.out.println(username);
        System.out.println(postCreationTime);

            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_UPDATEBLODDSTATUS,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            //we need to pass the json message
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                Log.e("anyText", response);

                                Toast.makeText(PopupWindowForBloodStatus.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), SeeMyPosts.class);
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
                            Toast.makeText(PopupWindowForBloodStatus.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("postCreationTime", postCreationTime);
                    return params;

                }
            };

            //RequestQueue requestQueue = Volley.newRequestQueue(this);
            //requestQueue.add(stringRequest);

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);


    }
}

