package com.example.api_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class Recipe_full extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_full);

        Intent myintent=getIntent();

        String URL1=myintent.getStringExtra("img");
        String name=myintent.getStringExtra("name");
        String social_rank=myintent.getStringExtra("social_rank");
        String publisher=myintent.getStringExtra("publisher");
        String source_url=myintent.getStringExtra("source_url");

        ImageView imageView=(ImageView)findViewById(R.id.food);
        TextView textView=(TextView)findViewById(R.id.name);
        TextView textView2=(TextView)findViewById(R.id.rank);
        TextView textView3=(TextView)findViewById(R.id.publisher);
        TextView textView4=(TextView)findViewById(R.id.url);
        //TextView textView5=(TextView)findViewById(R.id.textView11);


        Picasso.with(this).load(URL1).into(imageView);

        textView.setText(name);
        textView2.setText(social_rank);
        textView3.setText(publisher);
        textView4.setText(source_url);
        //textView5.setText(URL1);
    }
}
