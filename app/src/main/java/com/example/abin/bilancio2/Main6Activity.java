package com.example.abin.bilancio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main6Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);


    }
    public void onBack(View view)
    {
        Intent i=new Intent(this,Main2Activity.class);
        startActivity(i);
    }
}
