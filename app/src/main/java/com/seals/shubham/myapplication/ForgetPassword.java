package com.seals.shubham.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {
    dbController db = new dbController(this);
    EditText regId;
    Button bck,sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        regId = (EditText)findViewById(R.id.frgtRegId);
        sub = (Button)findViewById(R.id.btnSubmit);
        bck = (Button)findViewById(R.id.btnBck);
        bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Registration_id = regId.getText().toString();
                if(db.getVal(Registration_id)!=null){
                    Intent i = new Intent(getApplicationContext(),SendSmsActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid Information",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
