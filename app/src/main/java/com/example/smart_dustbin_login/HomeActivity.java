package com.example.smart_dustbin_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button btnlogout;
    FirebaseAuth mfirebaseAuth;
    ImageView im1,im2,im3;
    private FirebaseAuth.AuthStateListener mauthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnlogout = findViewById(R.id.logout);
        im1 = findViewById(R.id.imgadd);
        im3 = findViewById(R.id.imgstatus);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intAddDustbin = new Intent(HomeActivity.this,AddDustbinActivity.class);
                startActivity(intAddDustbin);
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intMain = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intMain);
            }


        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRetrive = new Intent(HomeActivity.this,RetriveDataActivity.class);
                startActivity(intRetrive);
            }
        });


    }
}
