package com.example.smart_dustbin_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetriveDataActivity extends AppCompatActivity {
    TextView pr1,arid,dtid;
    Button submitbtn;
    ImageView im3;
    public FirebaseAuth mFirebaseAuth ;
    private FirebaseDatabase firebaseDatabase;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_data);
        pr1 = (TextView)findViewById(R.id.prsn);
        arid = (TextView)findViewById(R.id.area);
        dtid = (TextView)findViewById(R.id.did);
        FirebaseDatabase database1= FirebaseDatabase.getInstance();
        DatabaseReference myRef11=database1.getReference("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        myRef11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, Object>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Object>>() {};
                Map<String, Object> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                ArrayList<Object> objectArrayList = new ArrayList<Object>(objectHashMap.values());

                String areaid = dataSnapshot.child("areaid").getValue(String.class);
                String person = dataSnapshot.child("person").getValue(String.class);
                String dustid = dataSnapshot.child("dustid").getValue(String.class);
                //arid.setText(areaid+"");
                for (Map.Entry entry : objectHashMap.entrySet())
                {
                    arid.append("key: " + entry.getKey().toString() + "; value: " + entry.getValue().toString());
                    //arid.setText("key: " + entry.getKey().toString() + "; value: " + entry.getValue().toString());
                    //arid.setText(""+objectArrayList.get(entry.getKey()));



                }
                //pr1.setText(person);
                //dtid.setText(dustid);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* mFirebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference("user").child(mFirebaseAuth.getUid());
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(RetriveDataActivity.this, "Japan", Toast.LENGTH_SHORT).show();
                reff = FirebaseDatabase.getInstance().getReference("user").child(mFirebaseAuth.getUid()+"");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        String areaid = dataSnapshot.child("areaid").getValue().toString();
                        String person = dataSnapshot.child("person").getValue().toString();
                        String dustid = dataSnapshot.child("dustid").getValue().toString();
                        arid.setText(areaid);
                        pr1.setText(person);
                        dtid.setText(dustid);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });*/
    }
}
