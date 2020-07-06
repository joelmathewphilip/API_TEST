package com.example.api_test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;


import android.content.DialogInterface;
import android.graphics.Bitmap;




import android.os.Bundle;
import android.os.Handler;


import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.android.volley.toolbox.Volley;


import org.json.JSONArray;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    String item = "chicken";
    String page = "1";
    int current_page=1;
    ProgressBar progressBar;
    String URL1 = "https://recipesapi.herokuapp.com/api/search?q=" + item+ "&page=" + page;
    String URL2="https://www.imgonline.com.ua/examples/jpeg-artifacts_3x.png";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=findViewById(R.id.progressBar6);
        progressBar.setVisibility(View.VISIBLE);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);
        View view=getSupportActionBar().getCustomView();

        ImageButton home=(ImageButton)view.findViewById(R.id.home);
        ImageButton left=(ImageButton)view.findViewById(R.id.left);
        ImageButton right=(ImageButton)view.findViewById(R.id.right);
        final TextView textView=(TextView)view.findViewById(R.id.textView3);






        textView.setText(String.valueOf(current_page));

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Download("1");
                current_page=1;
                textView.setText(String.valueOf(current_page));
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_page==30)
                {
                    current_page=30;
                }
                else
                {
                    current_page=current_page+1;
                }
                Download(String.valueOf(current_page));
                textView.setText(String.valueOf(current_page));
            }

        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_page==1)
                {
                    current_page=1;
                }
                else
                {
                    current_page=current_page-1;
                }
                Download(String.valueOf(current_page));
                textView.setText(String.valueOf(current_page));
            }
        });



        final SwipeRefreshLayout swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.Swipe);
        Download(String.valueOf(current_page));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //setContentView(R.layout.activity_main);
                Download1(String.valueOf(current_page));

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });

    }

public void Download(String page)
{
    requestQueue = Volley.newRequestQueue(this);
    progressBar.setVisibility(View.VISIBLE);
    String URL1 = "https://recipesapi.herokuapp.com/api/search?q=" + item+ "&page=" + page;
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL1, null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            try {
                Bitmap bitmap = null;
                ArrayList<DataClassImageLoad> obj=new ArrayList<>();
                AdapterClassimage adapterClassimage=new AdapterClassimage(getApplicationContext(),obj);
                ListView listView=(ListView)findViewById(R.id.listview);



                JSONArray array1=response.getJSONArray("recipes");

                for(int i=0;i<array1.length();i++)
                {
                    JSONObject jsonObject=array1.getJSONObject(i);
                    String image_url=jsonObject.getString("image_url");
                    String name=jsonObject.getString("title");
                    String social_rank=jsonObject.getString("social_rank");
                    String publisher=jsonObject.getString("publisher");
                    String source_url=jsonObject.getString("source_url");


                    obj.add(new DataClassImageLoad(image_url,name,social_rank,publisher,source_url));
                }
                listView.setAdapter(adapterClassimage);

                progressBar.setVisibility(View.INVISIBLE);

            }
            catch (Exception e) {
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    });
    requestQueue.add(jsonObjectRequest);

}


    public void Download1(String page)
    {
        requestQueue = Volley.newRequestQueue(this);
        //progressBar.setVisibility(View.VISIBLE);
        String URL1 = "https://recipesapi.herokuapp.com/api/search?q=" + item+ "&page=" + page;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL1, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    Bitmap bitmap = null;
                    ArrayList<DataClassImageLoad> obj=new ArrayList<>();
                    AdapterClassimage adapterClassimage=new AdapterClassimage(getApplicationContext(),obj);
                    ListView listView=(ListView)findViewById(R.id.listview);



                    JSONArray array1=response.getJSONArray("recipes");

                    for(int i=0;i<array1.length();i++)
                    {
                        JSONObject jsonObject=array1.getJSONObject(i);
                        String image_url=jsonObject.getString("image_url");
                        String name=jsonObject.getString("title");
                        String social_rank=jsonObject.getString("social_rank");
                        String publisher=jsonObject.getString("publisher");
                        String source_url=jsonObject.getString("source_url");


                        obj.add(new DataClassImageLoad(image_url,name,social_rank,publisher,source_url));
                    }
                    listView.setAdapter(adapterClassimage);
                    //progressBar.setVisibility(View.INVISIBLE);

                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);

    }

public void onBackPressed()
    {
        AlertDialog.Builder alertbox=new AlertDialog.Builder(this);

        alertbox.setMessage("Do you wanna close the app?");
        alertbox.setTitle("Warning!!");

        alertbox.setCancelable(false);
        alertbox.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertbox.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertbox.show();
    }

}

