package com.example.abin.bilancio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main5Activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b1;
    String cardno,mon,ye,cv,passw,bill,consume;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference myref=database.getReference("Consumer");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        e1=(EditText)findViewById(R.id.card);
        e2=(EditText)findViewById(R.id.month);
        e3=(EditText)findViewById(R.id.hi);
        e4=(EditText)findViewById(R.id.cvv);
        e5=(EditText)findViewById(R.id.pass);
        bill=getIntent().getStringExtra("bill");
        consume=getIntent().getStringExtra("consume");
       // e1.setText("1001123498761234");
        //e2.setText("08");
        //e3.setText("2022");
        //e4.setText("143");

    }
    public void okDone(View view)
    {


        cardno=e1.getText().toString();
        Log.e("card",cardno);
        mon=e2.getText().toString();
        Log.e("mon",mon);
        ye=e3.getText().toString();
        Log.e("year",ye);
        cv=e4.getText().toString();
        Log.e("cvv",cv);
        b1=(Button)findViewById(R.id.ok);

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!cardno.equals("") && !mon.equals("") && !ye.equals("") && !cv.equals("")) {
                    if (dataSnapshot.hasChild(cardno)) {
                        if (dataSnapshot.child(cardno).hasChild(mon)) {
                            if (dataSnapshot.child(cardno).child(mon).hasChild(ye)) {
                                if (dataSnapshot.child(cardno).child(mon).child(ye).hasChild(cv)) {
                                    //Toast.makeText(Main5Activity.this, "yahoo", Toast.LENGTH_SHORT).show();
                                    b1.setVisibility(View.VISIBLE);
                                }
                                else
                                    Toast.makeText(Main5Activity.this, "Invalid Card Details ", Toast.LENGTH_SHORT).show();

                            }
                            else
                                Toast.makeText(Main5Activity.this, "Invalid Card Details", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(Main5Activity.this, "Invalid Card Details ", Toast.LENGTH_SHORT).show();

                    }
                    else
                        Toast.makeText(Main5Activity.this, "Invalid Card Details", Toast.LENGTH_SHORT).show();

                }
                else
                    Toast.makeText(Main5Activity.this, "Invalid Card Details", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("hai", "Failed to read value.", databaseError.toException());


            }
        });



    }
    public void onPass(View view)
    {
        passw=e5.getText().toString();
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pa=dataSnapshot.child(cardno).child(mon).child(ye).child(cv).getValue().toString();
                if(pa.equals(passw))
                {
                    myref.child(consume).child(bill).child("Status").setValue("yes");

                    Intent i=new Intent(Main5Activity.this,Main6Activity.class);
                    startActivity(i);



                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

}
