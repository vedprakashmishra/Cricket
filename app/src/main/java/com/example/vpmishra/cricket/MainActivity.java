package com.example.vpmishra.cricket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    String url="http://cricapi.com/api/cricket/?apikey=oTBtsaC03TWZN4xCngQs1umpmXE3";
    TextView current_matches;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        current_matches=(TextView) findViewById(R.id.current_matches);
        l=(ListView) findViewById(R.id.lv);
        current_matches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getData();
            }
        });
    }
void  getData(){
    OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();
    client.newCall(request).enqueue(new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            Log.e(TAG, "onFailure: "+e.getMessage() );
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            String data=response.body().string();
            try {
                JSONObject jsonObject=new JSONObject(data);
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                for (int i=0;i<jsonArray.length();i++) {
                    JSONObject object=jsonArray.getJSONObject(i);
                    String s=object.getString("description");
                    
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //Log.d(TAG, "onResponse: "+data);
        }
    });
    }
}

