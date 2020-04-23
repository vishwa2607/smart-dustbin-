package com.example.smart_dustbin_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    ImageView k1,a1,g1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        g1 = findViewById(R.id.grscr);
        k1 = findViewById(R.id.kitscr);
        a1 = findViewById(R.id.anibed);
        k1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intAddDustbin = new Intent(UserActivity.this, activity_kitscrap.class);
                startActivity(intAddDustbin);
            }
        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intMain = new Intent(UserActivity.this, activity_animalbed.class);
                startActivity(intMain);
            }


        });
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intRetrive = new Intent(UserActivity.this, activity_grascrap.class);
                startActivity(intRetrive);
            }
        });


    }
}