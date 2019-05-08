package com.example.bitzone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentAttendance extends AppCompatActivity {

    private EditText klass,rollNumber;
    private Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);

        klass = findViewById(R.id.student_class);
        rollNumber = findViewById(R.id.student_roll);
        submit = findViewById(R.id.submit_details);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StudentAttendance.this,StudentClasses.class);
                intent.putExtra("class",klass.getText().toString());
                intent.putExtra("roll",rollNumber.getText().toString());
                startActivity(intent);
            }
        });
    }
}
