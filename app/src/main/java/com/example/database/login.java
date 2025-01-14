package com.example.database;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.javabean.User;

public class login extends AppCompatActivity {

    private MYsqliteopenhelper mYsqliteopenhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mYsqliteopenhelper = new MYsqliteopenhelper(this);

        TextView jump = findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, signup.class);
                startActivity(intent);
            }
        });


    }


    public void login(View v) {
        //1.判断是否为空
        EditText emailEdt = findViewById(R.id.email);
        EditText pwdEdt = findViewById(R.id.password);
        String email = emailEdt.getText().toString();
        String pwd = pwdEdt.getText().toString();
        boolean login = mYsqliteopenhelper.login(email, pwd);
        if (email.equals("")) {
            //如果为空，则提示
            //无焦点提示
            Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show();
        }
        if (pwd.equals("")) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        }
        //不为空，则出现进度条

        if (!login) {
            Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
        }
        //zhelikaishi
        else {
            User user = mYsqliteopenhelper.getUser(email, pwd);
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            int userId = sharedPreferences.getInt("id", -1);
            if (userId != -1) {
                editor.remove("id");
                editor.remove("userEmail");
                editor.remove("name");
                editor.remove("type");
                editor.remove("phone");
                editor.apply();
            }

            editor.putInt("id", user.getUser_id());
            editor.putString("userEmail", user.getEmail());
            editor.putString("name", user.getUser_name());
            editor.putInt("type", user.getUser_type());
            editor.putString("phone", user.getPhone());
            editor.apply();

            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        //daozheli
    }


}