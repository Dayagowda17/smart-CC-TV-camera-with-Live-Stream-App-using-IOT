package com.example.smartcctv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText username,password;
    Button btnLogin;
    DBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.usernamelogin);
        password=findViewById(R.id.passwordlogin);
        btnLogin=findViewById(R.id.btnlogin);
        myDB=new DBHelper(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                Toast.makeText(Login.this, "Plz enter username and password", Toast.LENGTH_SHORT).show();

                if(user.equals(" ")|| pass.equals(" ")){
                    Toast.makeText(Login.this, "Plz enter username and password", Toast.LENGTH_SHORT).show();

                }
                else
                {
                  Boolean res= myDB.checkusernamepassword(user,pass);
                 if(res==true)
                 {
                            Intent intent = new Intent(Login.this, HomePage.class);
                            startActivity(intent);
                  } else {
                           Toast.makeText(Login.this, "Invalid username and password", Toast.LENGTH_SHORT).show();
                        }
                    }


            }
        });
    }
}