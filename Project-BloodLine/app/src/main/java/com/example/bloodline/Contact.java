package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class Contact extends AppCompatActivity {

    Button continueContact;
    EditText contactText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        continueContact = (Button) findViewById(R.id.continueButton3);
        contactText = findViewById(R.id.mobileSignup);


        continueContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contact = String.valueOf(contactText.getText());

                if(!contact.equals("")) {

                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[1];
                            field[0] = "contact";

                            //Creating array for data
                            String[] data = new String[1];
                            data[0] = contact;


                            PutData putData = new PutData("http://192.168.0.102/BloodLine/contact.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    //End ProgressBar (Set visibility to GONE)
                                    if(result.equals("Sign Up Success")){

                                        Toast.makeText(Contact.this, "Data inserted successfully.", Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(), BloodRequest.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });

                }
                else{
                    Toast.makeText(getApplicationContext(), "Please fill out all the fields.", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}