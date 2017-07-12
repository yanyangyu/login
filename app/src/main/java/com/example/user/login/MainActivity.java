package com.example.user.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText loginid,password;
    Button submit;
    CheckBox checkBox , checkBox2;
    String checkbox , checkbox2;

    String myLoginid, myPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        findview();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // 設置一個checkbox  並確認他是否有選取
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    Toast.makeText(MainActivity.this, "要記錄帳號密碼", Toast.LENGTH_SHORT).show();
                    checkbox = "yes";
                } else {
                    Toast.makeText(MainActivity.this, "不要記錄帳號密碼", Toast.LENGTH_SHORT).show();
                    checkbox = "no";
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // 設置一個checkbox  並確認他是否有選取
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox2.isChecked()) {
                    Toast.makeText(MainActivity.this, "要記錄登陸時間", Toast.LENGTH_SHORT).show();
                    checkbox2 = "yes";
                } else {
                    Toast.makeText(MainActivity.this, "不要記錄登陸時間", Toast.LENGTH_SHORT).show();
                    checkbox2 = "no";
                }
            }
        });



    }


    public void init(){

        SharedPreferences setting = getSharedPreferences("login",MODE_PRIVATE);
        myLoginid = setting.getString("loginid", "");
        myPassword = setting.getString("password", "");

    }

    public void findview(){
        loginid = (EditText)findViewById(R.id.loginid);
        password = (EditText)findViewById(R.id.password);
        submit = (Button)findViewById(R.id.submit);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        checkBox2 = (CheckBox)findViewById(R.id.checkBox2);




            loginid.setText(myLoginid);
            password.setText(myPassword);


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
            bag.putString("checkbox",checkbox);
            bag.putString("checkbox2",checkbox2);

            intent.putExtras(bag);
            startActivity(intent);

        }
    }

}
