package com.example.pedro.inf4042;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pedro on 07/12/2015.
 */
public class ThirdActivity extends AppCompatActivity {

    public TextView name, desc, time, notes;
    public ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_acitivity);

        Intent i = getIntent();
        String name_tx = i.getStringExtra("name");
        String description_tx = i.getStringExtra("description");
        String notes_tx = i.getStringExtra("notes");
        String time_tx = i.getStringExtra("time");

        Bundle extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap)extras.getParcelable("img");

        name = (TextView)findViewById(R.id.name_biers);
        desc = (TextView)findViewById(R.id.description_biers);
        time = (TextView)findViewById(R.id.time_biers);
        notes = (TextView)findViewById(R.id.notes_biers);
        image = (ImageView)findViewById(R.id.image_desc);

        name.setText("Name : "+name_tx+"\n");
        desc.setText("Description : "+description_tx+"\n");
        time.setText("Created at : "+time_tx+"\n");
        notes.setText("Notes : "+notes_tx+"\n");
        image.setImageBitmap(bmp);
    }
}
