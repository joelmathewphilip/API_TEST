package com.example.api_test;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterClassimage extends ArrayAdapter<DataClassImageLoad>
{
   ArrayList<DataClassImageLoad> apps=new ArrayList<>();
   Context mContext;
     public AdapterClassimage(@NonNull Context context, ArrayList<DataClassImageLoad> obj)
   {
       super(context,0,obj);
       this.apps=obj;
        mContext=context;

   }

        public View getView(int position, View convertview, ViewGroup parent) {
            View view = convertview;
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.list_view_row, null);
            }
            final DataClassImageLoad current_data = apps.get(position);

            ImageView imageView = (ImageView) view.findViewById(R.id.photo);
            TextView textView = (TextView) view.findViewById(R.id.textView);

            final String URL1 = current_data.getURL1();
            final String name = current_data.getName();
            final String social_rank = current_data.getSocial_rank();
            final String publisher = current_data.getPublisher();
            final String source_url = current_data.getSource_url();


            Picasso.with(getContext()).load(URL1).into(imageView);
            textView.setText(name);


                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), Recipe_full.class);
                        intent.putExtra("img", URL1);
                        intent.putExtra("name", name);
                        intent.putExtra("social_rank", social_rank);
                        intent.putExtra("publisher", publisher);
                        intent.putExtra("source_url", source_url);
                        v.getContext().startActivity(intent);
                    }
                });


            return view;
        }


}
