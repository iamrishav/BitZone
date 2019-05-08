package com.example.bitzone;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;
import java.util.UUID;

public class StudentAttendanceSummary extends AppCompatActivity {
    TextView mRollText;
    TextView mPer;
    ProgressBar mProgressBar;
    private List<Student> mStudents;
    private List<Attendance> mAttendances;
    String roll;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance_summary);
        mRollText=(TextView)findViewById(R.id.myrollText);
        mProgressBar=(ProgressBar)findViewById(R.id.myprentProgress);

        mPer=(TextView)findViewById(R.id.mysubPer);

        roll = getIntent().getStringExtra("roll");
        Intent i=getIntent();
        UUID uuid=(UUID)i.getSerializableExtra(SummaryActivity.FRAGMENT_CODE);
        Lecture lecture = LectureLab.get().getLectureById(uuid);
        mAttendances= lecture.getAttendances();
//        if (mAttendances.get(Integer.parseInt(roll))!=null)
        mStudents = mAttendances.get(0).getStudents();

        mRollText.setText(String.format("%d ",mStudents.get(Integer.parseInt(roll)).getRollNo()));
        float per=AttendanceLab.get().getPresentyPercentage(mAttendances, Integer.parseInt(roll));
        mPer.setText(String.format("%.2f %%", per));
        mProgressBar.setProgress((int)per);
        if(per<75) {
            mRollText.setTextColor(Color.parseColor("#FF0000"));
            mProgressBar.setBackgroundColor(Color.parseColor("#770000"));
        }else
        {
            mRollText.setTextColor(Color.parseColor("#119966"));
            mProgressBar.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }


    }
}
