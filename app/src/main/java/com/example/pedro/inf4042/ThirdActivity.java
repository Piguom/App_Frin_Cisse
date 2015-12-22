package com.example.pedro.inf4042;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by Pedro on 07/12/2015.
 */
public class ThirdActivity extends AppCompatActivity {

    public TextView name, desc, time, notes, rate;
    public ImageView image;
    public RatingBar rb;

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
        rate = (TextView)findViewById(R.id.rate_score);
        image = (ImageView)findViewById(R.id.image_desc);
        rb = (RatingBar)findViewById(R.id.ratingBar);
        rb.setIsIndicator(true);
        rb.setNumStars(5);

        name.setText(name_tx+"\n");
        desc.setText("Description : " + description_tx + "\n");
        time.setText("Created at : " + time_tx + "\n");
        if(notes_tx != "0") {
            //Log.d("ahahahaha","note = null");
            rb.setRating((Float.valueOf(notes_tx.trim()).floatValue()) / 2);
            rate.setText((Float.valueOf(notes_tx.trim()).floatValue()) / 2+"/5\n");
        }
        else {
            rb.setVisibility(View.INVISIBLE);
            notes.setText("Il n'y a pas de note de pour cet article\n");
        }
        image.setImageBitmap(bmp);
    }
}
