package com.example.abin.bilancio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity extends AppCompatActivity {
    TextView t1;
    String ab,conume,bill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        t1=(TextView)findViewById(R.id.textView5);
        ab=getIntent().getStringExtra("extra");
        t1.setText(ab);
        conume=getIntent().getStringExtra("consume");
        bill=getIntent().getStringExtra("bill");

    }
    public void onPay(View view)
    {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dataAccess = database.getReference("Consumer");
        dataAccess.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String detail=dataSnapshot.child(conume).child(bill).child("pay").getValue().toString();
              //  Toast.makeText(Main3Activity.this,detail,Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Main3Activity.this,Main4Activity.class);
                i.putExtra("pays",detail);
                i.putExtra("consume",conume);
                i.putExtra("bill",bill);
                startActivity(i);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("hai", "Failed to read value.", databaseError.toException());

            }
        });

    }
}
