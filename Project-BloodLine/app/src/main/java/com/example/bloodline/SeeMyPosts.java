package com.example.bloodline;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SeeMyPosts extends AppCompatActivity {

    TextView showErrorMsg;
    Button backToMainMenu;
    RecyclerView recyclerView;
    ArrayList<JsonDataList> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_my_posts);

        showErrorMsg = (TextView) findViewById(R.id.errorMsgMyPost);
        backToMainMenu = (Button) findViewById(R.id.backToMainFromMyPost);
        recyclerView = findViewById(R.id.recyclerViewMyPost);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ViewMyPosts();

        arrayList = new ArrayList<JsonDataList>();

        backToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);

            }
        });
    }

    private void ViewMyPosts(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait...");

        progressDialog.show();

        String username = SharedPrefManager.getInstance(this).getUsername();
        Log.i("name ", username);
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.URL_MYPOST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        System.out.println(response);
                        try {
                            JSONArray parent = new JSONArray(response);
                            int i= 0;
                            if(parent.length() <=0){
                                showErrorMsg.setText("Opps, No posts yet !!");
                            }
                            while( i < parent.length()){

                                JSONObject child = parent.getJSONObject(i);
                                String fullname = child.getString("fullname");
                                String contact = child.getString("contact");
                                String postCreationTime = child.getString("postCreationTime");
                                String writtenPost = child.getString("writtenPost");
                                String bloodAmount = child.getString("bloodAmount");
                                String hospitalName = child.getString("hospitalName");
                                String bloodStatus = child.getString("bloodStatus");

                                arrayList.add(new JsonDataList(fullname, contact, postCreationTime, writtenPost,bloodAmount,hospitalName,bloodStatus));
                                i++;

                            }
                            JsonAdapterForMyPost jsonAdapterForMyPost = new JsonAdapterForMyPost(arrayList, getApplicationContext());
                            recyclerView.setAdapter(jsonAdapterForMyPost);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(SeeMyPosts.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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