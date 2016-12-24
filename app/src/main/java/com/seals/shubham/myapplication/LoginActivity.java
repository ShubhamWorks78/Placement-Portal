package com.seals.shubham.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    dbController db = new dbController(this);
    EditText regId,pass;
    Button logIn,exit;
    TextView forget,reg;
    CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regId = (EditText)findViewById(R.id.LogRegId);
        pass = (EditText)findViewById(R.id.LogPass);
        forget = (TextView)findViewById(R.id.frgtPass);
        exit = (Button)findViewById(R.id.btnExit);
        reg = (TextView)findViewById(R.id.CreAcc);
        cb = (CheckBox)findViewById(R.id.showPass);
        logIn = (Button)findViewById(R.id.btnLog);
        /*To Show Password in Normal Manner when CheckBox is ticked*/
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pass.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
                }
                else{
                    pass.setInputType(129);
                }
            }
        });
        /*Clicking on Create Account Takes the user to Registration Activity*/
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);//Intent to Registration Activity
                startActivity(i); //Start the Intent
            }
        });
        /*For resetting the Password*/
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ForgetPassword.class); //Intent to Forget Password Activity
                startActivity(i);
            }
        });
        /*this takes user to his Profile*/
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = regId.getText().toString();
                String password = pass.getText().toString();
                regId.setText("");
                pass.setText("");
                if(db.checkData(userName,password)==1){
                    Intent i = new Intent(getApplicationContext(),CommonActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid User",Toast.LENGTH_LONG).show();
                }
            }
        });
        /*Exit Takes it Out of the Application*/
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(LoginActivity.this);
                ab.setMessage("Hey Fella!Really wanna Check Out?");//Message to be Shown to the User when he Click on Exit
                ab.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*If this Option is selected then user resides in the Application****Dialog is dismissed*/
                        dialogInterface.dismiss();
                    }
                });
                ab.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*If this Option is Selected then Application is Closed...*/
                        finishAffinity();
                    }
                });
                ab.show();
            }
        });
    }
}
