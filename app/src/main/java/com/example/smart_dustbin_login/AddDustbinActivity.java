package com.example.smart_dustbin_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddDustbinActivity extends AppCompatActivity {
    Button submitbtn;
    EditText dustid,areaid,person,mobno,lg,lt,index;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dustbin);

        submitbtn = findViewById(R.id.btnid);
        dustid = findViewById(R.id.did);
        areaid = findViewById(R.id.area);
        person = findViewById(R.id.prsn);
        mobno = findViewById(R.id.mno);
        lt = findViewById(R.id.ltid);
        lg = findViewById(R.id.lgid);
        index=findViewById(R.id.index);
        mFirebaseAuth = FirebaseAuth.getInstance();
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ind=Integer.parseInt(index.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("user").child(mFirebaseAuth.getUid()+"").child(ind+"").child("dustid");
                myRef.setValue(dustid.getText().toString());
                myRef = database.getReference("user").child(mFirebaseAuth.getUid()+"").child(ind+"").child("areaid");
                myRef.setValue(areaid.getText().toString());
                myRef = database.getReference("user").child(mFirebaseAuth.getUid()+"").child(ind+"").child("person");
                myRef.setValue(person.getText().toString());
                myRef = database.getReference("user").child(mFirebaseAuth.getUid()+"").child(ind+"").child("mobno");
                myRef.setValue(mobno.getText().toString());
                myRef = database.getReference("user").child(mFirebaseAuth.getUid()+"").child(ind+"").child("lt");
                myRef.setValue(lt.getText().toString());
                myRef = database.getReference("user").child(mFirebaseAuth.getUid()+"").child(ind+"").child("lg");
                myRef.setValue(lg.getText().toString());
                myRef = database.getReference("user").child("total");
                final DatabaseReference finalMyRef = myRef;
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String valu = dataSnapshot.getValue(String.class);
                        finalMyRef.setValue((Long.parseLong(valu)+1)+"");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        /*FirebaseDatabase database = FirebaseDatabase.getInstance();
        */
        //myRef.setValue(emailid);





    }



}
