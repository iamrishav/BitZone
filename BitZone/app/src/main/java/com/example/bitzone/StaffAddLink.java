package com.example.bitzone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StaffAddLink extends AppCompatActivity {
    EditText NEWLINK;
    Button ADD;

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference root = firebaseDatabase.getReference();
    DatabaseReference myRef =root.child("links");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add_link);

        ADD = findViewById(R.id.addLink);
        NEWLINK = findViewById(R.id.ADDLINKTEXT);

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.push().child("link").setValue(NEWLINK.getText().toString());
            }
        });


    }
}
