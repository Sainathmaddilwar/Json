package com.example.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String Myurl="https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
        Button button=(Button) findViewById(R.id.button);
        final ImageView image=(ImageView) findViewById(R.id.imageView);
        final int photo;
        photo=R.drawable.sai;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, Myurl, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.i("Data", "Data" + response);

                                try {
                                    String weather = response.getString("weather");


                                    JSONArray array = new JSONArray(weather);


                                    for (int i = 0; i < array.length(); i++) {

                                        JSONObject w = array.getJSONObject(i);
                                        Log.i("weather", "main :" + w.getString("main"));


                                    }
                                }
                                catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Error","Error is"+error);
                            }
                        });
                MySingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
                image.setImageResource(photo);

            }
        });
    }
}
