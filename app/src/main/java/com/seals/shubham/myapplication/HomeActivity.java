package com.seals.shubham.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {
    Handler hnd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        hnd = new Handler();
        hnd.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                HomeActivity.this.startActivity(i);
                HomeActivity.this.finish();
            }
        },5000);
    }
}
