package com.example.smart_dustbin_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    EditText email, pass;
    Button signup;
    TextView signin,farmform,mnumber,name,adr;
    FirebaseAuth mFirebaseAuth;
    String emailid;
    AnimationDrawable dustbinAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFirebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        signup = findViewById(R.id.signup);
        signin = findViewById(R.id.textView2);
        farmform = findViewById(R.id.textView3);
        mnumber = findViewById(R.id.mobileno1);
        name = findViewById(R.id.name1);
        adr = findViewById(R.id.addr1);
        ImageView imageView = (ImageView)findViewById(R.id.image1);
        imageView.setBackgroundResource(R.drawable.dustbin_animation);
        dustbinAnimation =(AnimationDrawable) imageView.getBackground();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailid = email.getText().toString();
                String pwd = pass.getText().toString();
                if (emailid.isEmpty()) {
                    email.setError("Email required");
                    email.requestFocus();
                } else if (pwd.isEmpty()) {
                    pass.setError("password required");
                    pass.requestFocus();
                } else if (emailid.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "fileds are empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailid.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(emailid, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "SignUp unsuccesful,Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("user").child(mFirebaseAuth.getUid() + "").child("email");
                                myRef.setValue(emailid);
                                startActivity(new Intent(MainActivity.this, HomeActivity.class));
                            }

                        }

                    });
                } else {
                    Toast.makeText(MainActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }


        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        farmform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user");
                myRef.child("number").setValue(mnumber);
                myRef.child("Name").setValue(name);
                myRef.child("Address").setValue(adr);
                //myRef.setValue();
                Intent i = new Intent(MainActivity.this, UserActivity.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        dustbinAnimation.start();
    }

}
