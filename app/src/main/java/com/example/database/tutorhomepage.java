package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.database.javabean.Tutor;

import java.util.ArrayList;
import java.util.List;


public class tutorhomepage extends AppCompatActivity {

    private card3Adapter card3Adapter;
    private RecyclerView recyclerView;
    private List<card3Bean> C3Bean = new ArrayList<>();
    private TextView ttname, intro, exp, eml;
    private MYsqliteopenhelper mYsqliteopenhelper;
    private ImageView img, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorhomepage);

        mYsqliteopenhelper = new MYsqliteopenhelper(this);

        int tutorId = getIntent().getIntExtra("tutor_id", 0);
        Tutor tutor = mYsqliteopenhelper.getTutorDetailById(tutorId);


        recyclerView = findViewById(R.id.mRecyclerView);
        ttname = findViewById(R.id.tutorname);
        intro = findViewById(R.id.introdu);
        exp = findViewById(R.id.exper);
        eml = findViewById(R.id.email);
        img = findViewById(R.id.imageView);
        back = findViewById(R.id.back2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        switch (tutorId % 3){
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

        eml.setText(mYsqliteopenhelper.getEmailByTutorId(tutorId));
        ttname.setText(tutor.getTutor_name());
        intro.setText(tutor.getIntro());
        exp.setText(tutor.getTeach_exp());

        List<card3Bean> C3Bean = mYsqliteopenhelper.getCommentsByTutorId(tutorId);




        card3Adapter = new card3Adapter(this,C3Bean);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(card3Adapter);
    }
}