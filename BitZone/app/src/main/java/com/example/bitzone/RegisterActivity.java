package com.example.bitzone;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText regnm, regpass, regmail;
    Button reg_btn;
    String regnmv, regpassv, regmailv, regtypev;
    Spinner reg_spinner;
    public AlertDialog.Builder ex;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference rootRef = db.getReference();


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        ex.show();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        reg_btn = (Button) findViewById(R.id.Reg_btn);
        regnm = (EditText) findViewById(R.id.reg_name);
        regpass = (EditText) findViewById(R.id.reg_pass);
        regmail = (EditText) findViewById(R.id.reg_email);
        reg_spinner = (Spinner) findViewById(R.id.reg_spin);
        String type[] = {"Staff", "Student"};
        reg_spinner.setOnItemSelectedListener(RegisterActivity.this);
        ArrayAdapter<String> typelist = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        typelist.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reg_spinner.setAdapter(typelist);

        ex = new AlertDialog.Builder(this).setTitle("Exit ?").setMessage("Do You Want To Exit").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        regtypev = parent.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public final boolean isInternetOn() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec = (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {
            // if connected with internet
            return true;
        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
    }


    public void doRegister(View view) {
        if (isInternetOn()) {
            regnmv = regnm.getText().toString();
            regpassv = regpass.getText().toString();
            regmailv = regmail.getText().toString();
            HashMap<String, String> usermap = new HashMap<>();
            usermap.put("name",regnmv);
            usermap.put("email",regmailv);
            usermap.put("password",regpassv);
            if (((regnmv.matches("")) && (regpassv.matches(""))) && (regmailv.matches(""))) {
                Toast.makeText(RegisterActivity.this, "Please Enter Detail", Toast.LENGTH_SHORT).show();
            } else {
                if (((regnmv != null) && (regpassv != null)) && ((regmailv != null)) && (regtypev != null)) {
                    if (regpassv.length() >= 8) {
                        final ProgressDialog pdlg = new ProgressDialog(this);
                        pdlg.setTitle("Registering");
                        pdlg.setMessage("Please Wait");
                        pdlg.show();
                        //crosscheck logic

                        rootRef.child(regtypev).push().setValue(usermap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(RegisterActivity.this, "Register Sucessfull", Toast.LENGTH_SHORT).show();
                                    pdlg.cancel();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Register Unsucessfull" + task.getResult(), Toast.LENGTH_SHORT).show();
                                    pdlg.cancel();
                                }
                            }
                        });



                    } else {
                        Toast.makeText(RegisterActivity.this, "Password Should Be 8 Digit Long", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        } else {
            Toast.makeText(this, "Please Check Internet Connection ", Toast.LENGTH_LONG).show();
        }


    }






}
