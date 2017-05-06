package com.example.abin.bilancio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
 TextView textView;
    String pays,conume,bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView=(TextView)findViewById(R.id.textView9);
        pays=getIntent().getStringExtra("pays");
        conume=getIntent().getStringExtra("consume");
        bill=getIntent().getStringExtra("bill");
        textView.setText(pays);


    }
    public void onProceed(View view)
    {

        Intent i=new Intent(Main4Activity.this,Main5Activity.class);
        i.putExtra("consume",conume);
        i.putExtra("bill",bill);
        startActivity(i);
    }

}
