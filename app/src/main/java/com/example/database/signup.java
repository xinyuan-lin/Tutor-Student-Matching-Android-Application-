package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.javabean.User;

public class signup extends AppCompatActivity {
    private MYsqliteopenhelper mYsqliteopenhelper1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mYsqliteopenhelper1 = new MYsqliteopenhelper(this);
        TextView jump = findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,login.class);
                startActivity(intent);
            }
        });


    }



    public void signup(View v) {
        //1.判断是否为空
        EditText emailEdt = findViewById(R.id.email);
        EditText usernameEdt = findViewById(R.id.username);
        EditText pwdEdt = findViewById(R.id.password);
        EditText confirmpEdt = findViewById(R.id.confirmp);
        RadioGroup radioGroup = findViewById(R.id.rgGroup);
        int selectedId = radioGroup.getCheckedRadioButtonId();



        String username = usernameEdt.getText().toString();
        String email = emailEdt.getText().toString();
        String pwd = pwdEdt.getText().toString();
        String confirmp = confirmpEdt.getText().toString();
        int user_type = 0;
        switch (selectedId) {
            case R.id.rgbutton1:
                user_type = 0;
                break;
            case R.id.rgbutton2:
                user_type = 1;
                break;
            default:
                break;
        }

        User u =new User(username, email, pwd, user_type);
        if(email.equals("")){
            //如果为空，则提示
            //无焦点提示
            Toast.makeText(this, "Email cannot be empty", Toast.LENGTH_SHORT).show();
        }
        if(username.equals("")){
            //如果为空，则提示
            //无焦点提示
            Toast.makeText(this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
        }
        if(pwd.equals("")){
            //如果为空，则提示
            //无焦点提示
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
        }
        if(confirmp.equals("")){
            //如果为空，则提示
            //无焦点提示
            Toast.makeText(this, "Confirm password cannot be empty", Toast.LENGTH_SHORT).show();
        }

        if(!pwd.equals(confirmp)){
            Toast.makeText(this, "The two passwords are different", Toast.LENGTH_SHORT).show();
        }



        //不为空，则出现进度条
        else {
            long l = mYsqliteopenhelper1.register(u);

            Button button = findViewById(R.id.btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(signup.this, login.class);
                    startActivity(intent);
                }
            });
        }


    }}