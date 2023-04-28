package com.example.smartcctv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Video extends AppCompatActivity {
    private WebView webView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("url");
    DatabaseReference myRef1 = database.getReference("Image");
    List<String> arrayList = new ArrayList<>();
    ListView listView;
    //    Intent intent = getIntent();
    String url;
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        listView = (ListView) findViewById(R.id.myList1);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(mAdapter);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                value = dataSnapshot.getValue(String.class);
//                Toast.makeText(Galary.this, ""+value, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Video.this, "Some error occured", Toast.LENGTH_SHORT).show();
            }
        });

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot product : dataSnapshot.getChildren()){
                    String key_val= product.getKey();
                    arrayList.add(key_val);

                }
                mAdapter.notifyDataSetChanged();
//                ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
////                listView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Video.this, "Some error occured", Toast.LENGTH_SHORT).show();
            }

        });
//        webView = (WebView) findViewById(R.id.webview);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl(""+url+"/img/");
//        listView = (ListView) findViewById(R.id.myList);
//        ArrayAdapter<String> mAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
//        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {
//                Toast.makeText(Galary.this, ""+arrayList.get(position), Toast.LENGTH_SHORT).show();
                String value1 = value.substring(0, value.length() - 1);
//                Toast.makeText(Galary.this, ""+value, Toast.LENGTH_SHORT).show();
                String img_url = value1+"/videos/"+arrayList.get(position);
                Toast.makeText(Video.this, ""+img_url, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),Video_view.class);
                intent.putExtra("url",img_url);
//                Toast.makeText(Galary.this, ""+img_url, Toast.LENGTH_SHORT).show();
                startActivity(intent);

            }

        });

//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }




    }
}