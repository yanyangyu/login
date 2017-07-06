package com.example.user.login;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loginid,password;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findview();

    }

    public void findview(){
        loginid = (EditText)findViewById(R.id.loginid);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSubmit();
            }
        });
    }

    public void doSubmit(){


        if(loginid.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("帳號不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            loginid.setFocusableInTouchMode(true);
            loginid.requestFocus();

        }else if (password.getText().toString().equals("")){
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setMessage("密碼不得為空")
                    .setPositiveButton("ok",null)
                    .show();

            password.setFocusableInTouchMode(true);
            password.requestFocus();

        }else {

            Intent intent = new Intent(this, CheckActivity.class);

            Bundle bag = new Bundle();
            bag.putString("loginid", loginid.getText().toString());
            bag.putString("password", password.getText().toString());

            intent.putExtras(bag);
            startActivity(intent);

        }
    }
}
