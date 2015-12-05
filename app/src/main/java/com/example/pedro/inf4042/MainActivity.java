package com.example.pedro.inf4042;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = (ImageView)findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    sleep(500); //wait for 2 seconds
                    Intent myIntent = new Intent(getApplicationContext(), SecondActivity.class);
                    startActivity(myIntent);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
