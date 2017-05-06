package com.example.abin.bilancio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Main6Activity extends AppCompatActivity {

String bill,consume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        bill=getIntent().getStringExtra("bill");
        consume=getIntent().getStringExtra("consume");


    }
    public void onBack(View view)
    {
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("Consumer");
        myref.child(consume).child(bill).child("Status").setValue("yes");
        Intent i=new Intent(Main6Activity.this,Main2Activity.class);
        startActivity(i);
    }
}
