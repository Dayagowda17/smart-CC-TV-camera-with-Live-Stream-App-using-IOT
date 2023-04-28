package com.example.smartcctv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePage extends AppCompatActivity {
    Button btnimage;
    Button btnvideo;
    Button video;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("url");
    String value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btnimage=findViewById(R.id.btngalary);
        btnvideo=findViewById(R.id.btnvideo);
        video=findViewById(R.id.Video);

        myRef.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            // This method is called once with the initial value and again
                                            // whenever data at this location is updated.
                                            value = dataSnapshot.getValue(String.class);
                                            Toast.makeText(HomePage.this, ""+value, Toast.LENGTH_SHORT).show();

                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                            Toast.makeText(HomePage.this, "Some error occured", Toast.LENGTH_SHORT).show();
                                        }
                                    });

        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Galary.class);
                intent.putExtra("url",value);
                startActivity(intent);


            }
        });



        btnvideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Livestream.class);
//                intent.putExtra("url",value);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Video.class);
                intent.putExtra("url",value);
                startActivity(intent);

            }
        });

    }

    private void  gotoUrl(String s)
    {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}