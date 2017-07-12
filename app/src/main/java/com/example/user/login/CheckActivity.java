package com.example.user.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.user.login.R.id.checkbox;

public class CheckActivity extends AppCompatActivity {

    ListView list;

    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);


        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH點mm分");
        Date date = new Date();

        SharedPreferences history = getSharedPreferences("history",MODE_PRIVATE);
        //清除資料
        /*SharedPreferences.Editor editor = history.edit();
        editor.remove("date");
        editor.commit();*/
        s = history.getString("date", dateFormat.format(date));

        if(s.toString().equals(""))
            login();
        else
            arrList();

        login();


    }

    public static final String KEY = "com.my.package.app";



    public void arrList(){
        ArrayList<String> ar = new ArrayList<String>();

        String[] tokens = s.split(" ");
        for (String token:tokens) {
            ar.add("你在" + token + "時登入過");
        }


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ar);

        list = (ListView)findViewById(R.id.list);

        list.setAdapter(adapter);

        list.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View v, int position, long id) {

                String s = list.getItemAtPosition(position).toString();

                Toast.makeText(CheckActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login(){
        Intent intent = getIntent();
        Bundle bag = intent.getExtras();
        String loginid = bag.getString("loginid");
        String password = bag.getString("password");
        String checkbox = bag.getString("checkbox");
        String checkbox2 = bag.getString("checkbox2");


        DateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日HH點mm分");
        Date date = new Date();



        if(loginid.equals("nfu") && password.equals("nfu")) {
            new AlertDialog.Builder(this)
                    .setTitle("登入成功")
                    .setPositiveButton("ok",null)
                    .show();

            if (checkbox.equals("yes")){

            SharedPreferences setting = getSharedPreferences("login",MODE_PRIVATE);
            setting.edit().putString("loginid",loginid).commit();
            setting.edit().putString("password",password).commit();
            }
            else
            {

            }






            SharedPreferences history = getSharedPreferences("history",MODE_PRIVATE);
            history.edit().putString("date",s + " " + dateFormat.format(date)).commit();

            s = history.getString("date", dateFormat.format(date));




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
