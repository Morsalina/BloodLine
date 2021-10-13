package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {

    EditText textUsername, textPassword;
    Button textLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if the user is already logged in
        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            Intent intent = new Intent(getApplicationContext(), Menu.class);
            startActivity(intent);
            return;
        }


        textUsername = (EditText) findViewById(R.id.usernameLogin);
        textPassword = (EditText) findViewById(R.id.passwordLogin);
        textLogin = (Button) findViewById(R.id.loginButton);

        textLogin.setOnClickListener(this);

    }

    private void userLogin() {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        String username = textUsername.getText().toString().trim();
        String password = textPassword.getText().toString().trim();

        if (username.equals("") || password.equals("")) {
            Toast.makeText(this, "Please fill out all the fields. !", Toast.LENGTH_SHORT).show();
        } else {


            progressDialog.show();
            StringRequest stringRequest = new StringRequest(
                    Request.Method.POST,
                    Constants.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {
                                System.out.println(response);
                                JSONObject obj = new JSONObject(response);
                                System.out.println(obj);
                                if (response.equalsIgnoreCase("Login Successfull")) {
                                    //user is successfully logged in
                                    //store the info in shared preferences

                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                            obj.getInt("id"),
                                            obj.getString("fullname"),
                                            obj.getString("username"),
                                            obj.getString("contact"),
                                            obj.getString("blood"),
                                            obj.getString("location")
                                    );

                                    Toast.makeText(getApplicationContext(), "User login successful.", Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(getApplicationContext(), Menu.class);
                                   startActivity(intent);
                                   finish();

                                } else {

                                    //user gave wrong info
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);

                    return params;
                }
            };

            RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
        }
    }

    @Override
    public void onClick(View v) {
         if(v == textLogin){
             userLogin();
         }
    }
}