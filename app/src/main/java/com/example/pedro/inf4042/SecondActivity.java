package com.example.pedro.inf4042;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.Serializable;

public class SecondActivity extends AppCompatActivity {

    RecyclerView rv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BiersUpdate(),intentFilter);
        GetBiersServices.startActionBiers(getApplicationContext());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final int[] i = {0};
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i[0] ==0) {
                    rv.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                    i[0] ++;
                }
                else {
                    rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
                    i[0] = 0;
                }

            }
        });

        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        rv.setAdapter(new BiersAdapter());
    }

    public static final String BIERS_UPDATE = "com.example.pedro.inf4042.action.BIERS_UPDATE";
    public class BiersUpdate extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("GetBiersServices", " "+getIntent().getAction());
            ((BiersAdapter) rv.getAdapter()).setBiers(getBiersFromFile());

        }
    }

    public JSONArray getBiersFromFile(){
        try{
            InputStream is = new FileInputStream(getCacheDir()+"/"+"bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        }catch (JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder>{

        JSONArray biers = new JSONArray();

        @Override
        public BiersAdapter.BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_bier_element,parent,false);

            return new BierHolder(v);
        }

        @Override
        public void onBindViewHolder(BiersAdapter.BierHolder holder, int position) {
            try {
                holder.name.setText((biers.getJSONObject(position).getString("name"))+"\n"+(biers.getJSONObject(position).getString("description")));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return biers.length();
        }

        public void setBiers(JSONArray json){
            biers = json;
            notifyDataSetChanged();
        }

        public class BierHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            public TextView name = null;
            public ImageView image_name = null;

            public BierHolder(View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);
                name = (TextView)itemView.findViewById(R.id.rv_bier_element_name);
                image_name = (ImageView)itemView.findViewById(R.id.rv_bier_element_img_desc);
            }

            @Override
            public void onClick(View v) {
                try {
                    image_name.buildDrawingCache();
                    Bitmap image = image_name.getDrawingCache();
                    Bundle extras = new Bundle();
                    extras.putParcelable("img", image);

                    Intent i = new Intent(getApplicationContext(),ThirdActivity.class);
                    i.putExtra("name", biers.getJSONObject(getPosition()).getString("name"));
                    i.putExtra("description", biers.getJSONObject(getPosition()).getString("description"));
                    i.putExtra("notes", biers.getJSONObject(getPosition()).getString("note"));
                    i.putExtra("time", biers.getJSONObject(getPosition()).getString("created_at"));
                    i.putExtras(extras);

                    startActivity(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



