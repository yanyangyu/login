package com.example.user.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Intent intent = getIntent();
        Bundle bag = intent.getExtras();
        String loginid = bag.getString("loginid");
        String password = bag.getString("password");


        if(loginid.equals("nfu") && password.equals("nfu")) {
            new AlertDialog.Builder(this)
                    .setMessage("帳號\t" + loginid + "\n密碼\t" + password)
                    .setTitle("登入成功")
                    .setPositiveButton("ok",null)
                    .show();

        }else{
            new AlertDialog.Builder(this)
                    .setTitle("登入失敗")
                    .setPositiveButton("返回", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }

    }
}
