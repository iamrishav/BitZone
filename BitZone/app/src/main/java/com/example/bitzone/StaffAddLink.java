package com.example.bitzone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class StaffAddLink extends AppCompatActivity {
    EditText NEWLINK;
    Button ADD;
    private ArrayList<StudentLinkItem> exampleList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    String TAG = "H";

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference root = firebaseDatabase.getReference();
    DatabaseReference myRef =root.child("links");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_add_link);

        ADD = findViewById(R.id.addLink);
        NEWLINK = findViewById(R.id.ADDLINKTEXT);
        generateData();
        recyclerViewConfig();

        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.push().child("link").setValue(NEWLINK.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(StaffAddLink.this,"Link added succesfully",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    public void generateData() {
        exampleList = new ArrayList<>();


        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String link = dataSnapshot.child("link").getValue().toString();
                Log.d(TAG,"link"+link);
                exampleList.add(new StudentLinkItem(link));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void recyclerViewConfig() {
        //config for RecyclerView
        recyclerView = findViewById(R.id.linkRecycler);
        //for better performance
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new LinkAdapter(exampleList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
