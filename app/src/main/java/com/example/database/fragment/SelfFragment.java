package com.example.database.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.database.MYsqliteopenhelper;
import com.example.database.R;
import com.example.database.createcourse;
import com.example.database.javabean.Student;
import com.example.database.javabean.Tutor;
import com.example.database.score;

public class SelfFragment extends Fragment {

    private TextView add1, add2;
    private TextView username, usertype, email, phonenb, interestorintro, educorexp, typrorn;
    private ImageView edph, ed1,ed2, createc, img, edt;
    private MYsqliteopenhelper mYsqliteopenhelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selfpage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mYsqliteopenhelper = new MYsqliteopenhelper(getActivity());
        find();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int userID = sharedPreferences.getInt("id", -1);
        String userName = sharedPreferences.getString("name","");
        String userEmail = sharedPreferences.getString("userEmail","");
        int type = sharedPreferences.getInt("type", -1);
        String phone = sharedPreferences.getString("phone", "");

        edph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputDialogedph(userID);
            }
        });
        ed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (type) {
                    case 0:
                        showInputDialoged3(userID);
                        break;
                    case 1:
                        showInputDialoged1(userID);
                        break;
                }
            }
        });

        ed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case 0:
                        showInputDialoged4(userID);
                        break;
                    case 1:
                        showInputDialoged2(userID);
                        break;
                }
            }
        });

        edt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (type == 1){
                    showInputDialoged5(userID);
                }
            }
        });



        username.setText(userName);
        email.setText(userEmail);
        phonenb.setText(phone);
        typepanduan(type);

        switch (type){
            case 0:
                Student student = mYsqliteopenhelper.getStudentById(userID);
                interestorintro.setText(student.getInterest());
                educorexp.setText(student.getEducation());
                usertype.setText("Student");
                switch (student.getStudent_id() % 2){
                    case 0:
                        img.setImageResource(R.mipmap.std1);
                        break;
                    case 1:
                        img.setImageResource(R.mipmap.std2);
                        break;
                }
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper.getTutorById(userID);
                interestorintro.setText(tutor.getIntro());
                educorexp.setText(tutor.getTeach_exp());
                usertype.setText(tutor.getTutor_name());
                switch (tutor.getTutor_id() % 3){
                    case 0:
                        img.setImageResource(R.mipmap.ttpic1);
                        break;
                    case 1:
                        img.setImageResource(R.mipmap.ttpic2);
                        break;
                    case 2:
                        img.setImageResource(R.mipmap.ttpic3);
                        break;
                }
                break;
        }

        createc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getActivity(), score.class);
                Intent intent2 = new Intent(getActivity(), createcourse.class);
                switch (type) {
                    case 0:
                        startActivity(intent1);
                        break;
                    case 1:
                        startActivity(intent2);
                        break;
                }
            }
        });




    }


    private void find() {

        add1 = getActivity().findViewById(R.id.add1);
        add2 = getActivity().findViewById(R.id.add2);
        username = getActivity().findViewById(R.id.usrname);
        usertype = getActivity().findViewById(R.id.type);
        email = getActivity().findViewById(R.id.eml);
        phonenb = getActivity().findViewById(R.id.phonenb);
        interestorintro = getActivity().findViewById(R.id.interestorintro);
        educorexp = getActivity().findViewById(R.id.educorexp);
        edph = getActivity().findViewById(R.id.editphone);
        ed1 = getActivity().findViewById(R.id.edit1);
        ed2 = getActivity().findViewById(R.id.edit2);
        createc = getActivity().findViewById(R.id.createbtn);
        img = getActivity().findViewById(R.id.imageView);
        typrorn = getActivity().findViewById(R.id.typeorn);
        edt = getActivity().findViewById(R.id.edit);
    }

    private void typepanduan(int type){
        if(type == 0){
            add1.setText("Interest");
            add2.setText("Education Background");
            typrorn.setText("User type");
        }
        if(type == 1){
            add1.setText("Self Introduction");
            add2.setText("Teaching Experience");
            typrorn.setText("Tutor name");
        }
    }


    private void showInputDialoged1(int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Intro");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newIntro = input.getText().toString();
                updateData1(userID, newIntro); // 根据具体需要调用相应的更新方法
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

    private void showInputDialoged3(int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Interest");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newInterest = input.getText().toString();
                updateData3(userID, newInterest); // 根据具体需要调用相应的更新方法
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

    private void showInputDialoged4(int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Education Background");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newEdu = input.getText().toString();
                updateData4(userID, newEdu); // 根据具体需要调用相应的更新方法
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Experience");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newExp = input.getText().toString();
                updateData2(userID, newExp); // 根据具体需要调用相应的更新方法
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Phone");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String phoneString = input.getText().toString();
                updateDataph(userID, phoneString); // 根据具体需要调用相应的更新方法
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

    private void showInputDialoged5(int userID) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Tutorname");
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newName = input.getText().toString();
                updateData5(userID, newName); // 根据具体需要调用相应的更新方法
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

    private void updateData1(int userID, String newData) {
        // 更新数据库中的数据
        mYsqliteopenhelper.updateTutorIntro(userID, newData);

        // 重新加载数据并更新UI
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int type = sharedPreferences.getInt("type", -1);
        switch (type){
            case 0:
                Student student = mYsqliteopenhelper.getStudentById(userID);
                interestorintro.setText(student.getInterest());
                educorexp.setText(student.getEducation());
                usertype.setText("Student");
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper.getTutorById(userID);
                interestorintro.setText(tutor.getIntro());
                educorexp.setText(tutor.getTeach_exp());
                usertype.setText(tutor.getTutor_name());
        }
    }

    private void updateData2(int userID, String newData) {
        // 更新数据库中的数据
        mYsqliteopenhelper.updateTutorTeachExp(userID, newData);

        // 重新加载数据并更新UI
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int type = sharedPreferences.getInt("type", -1);
        switch (type){
            case 0:
                Student student = mYsqliteopenhelper.getStudentById(userID);
                interestorintro.setText(student.getInterest());
                educorexp.setText(student.getEducation());
                usertype.setText("Student");
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper.getTutorById(userID);
                interestorintro.setText(tutor.getIntro());
                educorexp.setText(tutor.getTeach_exp());
                usertype.setText(tutor.getTutor_name());
        }
    }
    private void updateData3(int userID, String newData) {
        // 更新数据库中的数据
        mYsqliteopenhelper.updateStudentInterest(userID, newData);

        // 重新加载数据并更新UI
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int type = sharedPreferences.getInt("type", -1);
        switch (type){
            case 0:
                Student student = mYsqliteopenhelper.getStudentById(userID);
                interestorintro.setText(student.getInterest());
                educorexp.setText(student.getEducation());
                usertype.setText("Student");
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper.getTutorById(userID);
                interestorintro.setText(tutor.getIntro());
                educorexp.setText(tutor.getTeach_exp());
                usertype.setText(tutor.getTutor_name());
        }
    }

    private void updateData4(int userID, String newData) {
        // 更新数据库中的数据
        mYsqliteopenhelper.updateStudentEdu(userID, newData);

        // 重新加载数据并更新UI
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int type = sharedPreferences.getInt("type", -1);
        switch (type){
            case 0:
                Student student = mYsqliteopenhelper.getStudentById(userID);
                interestorintro.setText(student.getInterest());
                educorexp.setText(student.getEducation());
                usertype.setText("Student");
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper.getTutorById(userID);
                interestorintro.setText(tutor.getIntro());
                educorexp.setText(tutor.getTeach_exp());
                usertype.setText(tutor.getTutor_name());
        }
    }

    private void updateDataph(int userID, String newData) {
        // 更新数据库中的数据
        mYsqliteopenhelper.updatePhone(userID, newData);
        phonenb.setText(newData);
    }
    private void updateData5(int userID, String newData) {
        // 更新数据库中的数据
        mYsqliteopenhelper.updateTutorName(userID, newData);

        // 重新加载数据并更新UI
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        int type = sharedPreferences.getInt("type", -1);
        switch (type){
            case 0:
                Student student = mYsqliteopenhelper.getStudentById(userID);
                interestorintro.setText(student.getInterest());
                educorexp.setText(student.getEducation());
                usertype.setText("Student");
                break;
            case 1:
                Tutor tutor = mYsqliteopenhelper.getTutorById(userID);
                interestorintro.setText(tutor.getIntro());
                educorexp.setText(tutor.getTeach_exp());
                usertype.setText(tutor.getTutor_name());
        }
    }

}
