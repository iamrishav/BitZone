package com.example.bitzone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class Circular extends AppCompatActivity {


    String email ;


    FirebaseDatabase database;
    DatabaseReference circular ;
    FirebaseStorage storage;
    StorageReference storageReference;
    FirebaseRecyclerAdapter<Notice,CircularViewHolder> adapter;



    RecyclerView recycler_circular;
    RecyclerView.LayoutManager layoutManager;

    String username;
    ImageView btnAdd ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circular);

        email = getIntent().getStringExtra("email");

        btnAdd = findViewById(R.id.btn_add);

        database = FirebaseDatabase.getInstance();
        circular = database.getReference("Circular");
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        recycler_circular = findViewById(R.id.recycler_circular);
        recycler_circular.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycler_circular.setLayoutManager(layoutManager);

        loadCircular();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Circular.this,NewCircular.class);
                intent.putExtra("email",email);
                startActivity(intent);

            }
        });

    }

    private void loadCircular(){
        adapter = new FirebaseRecyclerAdapter<Notice, CircularViewHolder>(
                Notice.class,
                R.layout.circular_item,
                CircularViewHolder.class,
                circular

        ) {
            @Override
            protected void populateViewHolder(CircularViewHolder viewHolder, Notice model, int position) {
                viewHolder.circularText.setText(model.getUserName().substring(0,1));
//                viewHolder.userName.setText(model.getUserName());
                viewHolder.circularHead.setText(model.getHeading());
                viewHolder.Message.setText(model.getMessage());
                viewHolder.currentDay.setText(model.getTime());


                if(model.getImage() != "") {
                    Picasso.with(Circular.this).load(model.getImage())
                            .into(viewHolder.circularImage);
                }
                else{
                    viewHolder.circularImage.setVisibility(View.GONE);
                }
            }
        };
        adapter.notifyDataSetChanged();
        recycler_circular.setAdapter(adapter);

    }


}
