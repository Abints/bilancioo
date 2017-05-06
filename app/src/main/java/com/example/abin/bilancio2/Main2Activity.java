package com.example.abin.bilancio2;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
  //TextView t1,t2;
    String consumerNo,billNo;
   EditText e1,e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         e1= (EditText) findViewById(R.id.editText);
         e2=(EditText)findViewById(R.id.editText2);
        // t1=(TextView)findViewById(R.id.textView5);
        // t2=(TextView)findViewById(R.id.textView6);
     // e1.setText("123456");
       // e2.setText("9876");


    }
    public void onBill(View view){
        consumerNo = e1.getText().toString();
         billNo= e2.getText().toString();
        Log.e("Value",consumerNo);
        Log.e("Value2",billNo);

      // t1.setText(editTextValue);
       // t2.setText(editTextValue2);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Consumer");
// Read from the database
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if(dataSnapshot.hasChild(consumerNo))
                {
                    if (dataSnapshot.child(consumerNo).hasChild(billNo))

                    {
                        String state=dataSnapshot.child(consumerNo).child(billNo).child("Status").getValue().toString();
                        if(state.equals("no"))
                        {
                            String d = dataSnapshot.child(consumerNo).child(billNo).child("Details").getValue().toString();
                            //d.replace(",","\n");
                            //  Toast.makeText(Main2Activity.this,d,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                            intent.putExtra("extra", d);
                            intent.putExtra("consume", consumerNo);
                            intent.putExtra("bill", billNo);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(Main2Activity.this,"You have already paid the bill!!!",Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(Main2Activity.this,"Invalid consumer number or bill number",Toast.LENGTH_SHORT).show();

                    }
                }
                else
                {
                    Toast.makeText(Main2Activity.this,"Invalid consumer number or bill number",Toast.LENGTH_SHORT).show();

                }
               // String value = dataSnapshot.getValue(String.class);
               // Log.d(TAG, "Value is: " + value);
               //
                // t1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("hai", "Failed to read value.", error.toException());
            }
        });

    }
}
