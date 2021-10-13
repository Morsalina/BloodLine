package com.example.bloodline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView naview;
    Toolbar tool;
    ImageButton profile, bloodRequest, history, timeline;
    TextView helpLine,questionAnswer, volunteer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        drawerLayout =findViewById(R.id.drawer);
        naview =findViewById(R.id.nav_view);
        tool =findViewById(R.id.toolbar);
        profile = (ImageButton) findViewById(R.id.home);
        bloodRequest = (ImageButton) findViewById(R.id.blood_req);
        history = (ImageButton) findViewById(R.id.historyMenu);
        timeline = (ImageButton) findViewById(R.id.timeline);
        helpLine = (TextView) findViewById(R.id.helpline);
        questionAnswer = (TextView) findViewById(R.id.qaText);
        volunteer = (TextView) findViewById(R.id.volunteer);

        //if the user is not logged in.
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);

            }
        });
        bloodRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchBlood.class);
                startActivity(intent);

            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), History.class);
                startActivity(intent);

            }
        });

        timeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimelineForPosts.class);
                startActivity(intent);
            }
        });

        helpLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HelpLine.class);
                startActivity(intent);

            }
        });

        questionAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Question_Answer.class);
                startActivity(intent);
            }
        });

        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Volunteers.class);
                startActivity(intent);
            }
        });


        setSupportActionBar(tool);



        naview.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,tool,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //to make the icons clickable
        naview.setNavigationItemSelectedListener(this);
    }


    //to prevent stopping the app when back button is pressed
    public void onBackPressed(){

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
                break;

            case R.id.allPosts:
                Intent intent1 = new Intent(getApplicationContext(), TimelineForPosts.class);
                startActivity(intent1);
                break;

            case R.id.bl_req:
                Intent intent2 = new Intent(getApplicationContext(), SearchBlood.class);
                startActivity(intent2);
                break;

            case R.id.historyXD:
                Intent intent3 = new Intent(getApplicationContext(), History.class);
                startActivity(intent3);
                break;

            case R.id.maps:
                Intent intent4 = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent4);
                break;

            case R.id.createPost:
                Intent intent5 = new Intent(getApplicationContext(), CreatePost.class);
                startActivity(intent5);
                break;

            case R.id.myPosts:
                Intent intent6 = new Intent(getApplicationContext(), SeeMyPosts.class);
                startActivity(intent6);
                break;

            case R.id.logout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                Intent intent7 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent7);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}