package com.example.smartcctv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    DatabaseReference myRef = database.getReference("https://smartcctv-3549b-default-rtdb.firebaseio.com/");
//    List<String> arrayList = new ArrayList<>();

    EditText username,password,repassword;
    Button btnsignup,btnsignin;

    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText) findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        btnsignin=(Button) findViewById(R.id.btnsignin);
        btnsignup=(Button) findViewById(R.id.btnsignup);
        myDB=new DBHelper(this);

//        DatabaseReference url = myRef.child("url");
//
//        Toast.makeText(this, ""+url, Toast.LENGTH_SHORT).show();
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                if(user.equals(" ")|| pass.equals(" ")|| repass.equals("")){
                    Toast.makeText(MainActivity.this, "Fill all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass)){
                        Boolean usercheckRes=myDB.checkusername(user);
                        if(usercheckRes==false) {
                            Boolean regRes = myDB.insertData(user, pass);
                            if (regRes == true) {
                                Toast.makeText(MainActivity.this, "Registeration sucessfull", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Login.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this, "User already exisist\n please sign in", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }else
                    {
                        Toast.makeText(MainActivity.this, "Password doesn't match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot product : dataSnapshot.getChildren()){
//                     String key_val= product.getKey();
//                     arrayList.add(key_val);
//
//                }
//                Toast.makeText(MainActivity.this, ""+arrayList, Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//                Toast.makeText(MainActivity.this, ""+error.toException(), Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}