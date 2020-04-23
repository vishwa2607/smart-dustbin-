package com.example.smart_dustbin_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
import java.util.Map;

public class activity_kitscrap extends AppCompatActivity {

    TextView kitchen_scrap,gras_scrap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitscrap);
        kitchen_scrap= (TextView)findViewById(R.id.avk);
        gras_scrap = (TextView)findViewById(R.id.ltid);


        FirebaseDatabase database1= FirebaseDatabase.getInstance();
        DatabaseReference myRef11=database1.getReference("user").child("total");
       final float[] ans = {0};
        myRef11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valuexx = dataSnapshot.getValue(String.class);
                final Long totalx=Long.parseLong(valuexx);

                for(int i=0;i<totalx;i++)
                {
                    FirebaseDatabase database= FirebaseDatabase.getInstance();
                    DatabaseReference myRef=database.getReference("user").child("Hn1D0NfVIrgOAb1Ki8lYWJyfbir1").child((i+1)+"").child("lg");

                    final int finalI = i;
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue(String.class);
                            ans[0] = ans[0] +Float.parseFloat(value);
                            if(finalI ==totalx-1)
                            {
                                kitchen_scrap.setText(ans[0]+"");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








        //dtid = (TextView)findViewById(R.id.did);
      /*  FirebaseDatabase database1= FirebaseDatabase.getInstance();
        DatabaseReference myref = database1.getReference("user");
        myref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<HashMap<String, Object>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Object>>() {};
                Map<String, Object> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                ArrayList<Object> objectArrayList = new ArrayList<Object>(objectHashMap.values());
                for (Map.Entry entry : objectHashMap.entrySet())
                {
                    kitchen_scrap.append("key: " + entry.getKey().toString() + "; value: " + entry.getValue().toString());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
