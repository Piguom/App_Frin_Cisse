package com.example.pedro.inf4042;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Pedro on 07/12/2015.
 */
public class ThirdActivity extends AppCompatActivity {

    public TextView tx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_acitivity);

        Intent i = getIntent();
       String name = i.getStringExtra("name");

        tx = (TextView)findViewById(R.id.description_bieres);
       tx.setText("Name : "+name);
    }
}
