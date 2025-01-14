package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.system.Os;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.database.javabean.Student;
import com.example.database.javabean.Tutor;
import com.example.database.javabean.User;

public class selfpage extends AppCompatActivity {

    private TextView add1, add2;
    private TextView username, usertype, email, phonenb, interestorintro, educorexp;
    private ImageView edph, ed1,ed2;
    private MYsqliteopenhelper mYsqliteopenhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selfpage);
        mYsqliteopenhelper = new MYsqliteopenhelper(this);
        find();
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        int userID = sharedPreferences.getInt("id", -1);
        String userName = sharedPreferences.getString("name","");
        String userEmail = sharedPreferences.getString("userEmail","");
        int type = sharedPreferences.getInt("type", -1);
        String phone = sharedPreferences.getString("phone", "");
        username.setText(userName);
        email.setText(userEmail);
        phonenb.setText(phone);
        typepanduan(type);
        switch (type){
            case 0:
                Student student = mYsqliteopenhelper.getStudentById(userID);
                interestorintro.setText(student.getInterest());
                educorexp.setText(student.getEducation());
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper.getTutorById(userID);
                interestorintro.setText(tutor.getIntro());
                educorexp.setText(tutor.getTeach_exp());
        }


        edph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialogedph(userID);
            }
        });
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialoged1(userID);
            }
        });

        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialoged2(userID);
            }
        });
    }


    private void find() {

        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        username = findViewById(R.id.usrname);
        usertype = findViewById(R.id.type);
        email = findViewById(R.id.eml);
        phonenb = findViewById(R.id.phonenb);
        interestorintro = findViewById(R.id.interestorintro);
        educorexp = findViewById(R.id.educorexp);
        edph = findViewById(R.id.editphone);
        ed1 = findViewById(R.id.edit1);
        ed2 = findViewById(R.id.edit2);
    }

    private void typepanduan(int type){
        if(type == 0){
            add1.setText("Interest");
            add2.setText("Education Background");
            usertype.setText("Student");
        }
        if(type == 1){
            add1.setText("Self Introduction");
            add2.setText("Teaching Experience");
            usertype.setText("Tutor");
        }
    }


    private void showInputDialoged1(int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Intro");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newIntro = input.getText().toString();
                mYsqliteopenhelper.updateTutorIntro(userID, newIntro); // 根据具体需要调用相应的更新方法
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showInputDialoged2(int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Experience");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newExp = input.getText().toString();
                mYsqliteopenhelper.updateTutorTeachExp(userID, newExp); // 根据具体需要调用相应的更新方法
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void showInputDialogedph(int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Phone");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String phoneString = input.getText().toString();
                mYsqliteopenhelper.updatePhone(userID, phoneString); // 根据具体需要调用相应的更新方法
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }





}