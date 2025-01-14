package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class score extends AppCompatActivity {

    private EditText studentIdEditText;
    private EditText tutorIdEditText;
    private EditText commentEditText;
    private RatingBar ratingBar;
    private Button submitButton;
    private MYsqliteopenhelper mYsqliteopenhelper1;
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        mYsqliteopenhelper1 = new MYsqliteopenhelper(this);

        // Find views by id
        back = findViewById(R.id.back);
        studentIdEditText = findViewById(R.id.student_id_edittext);
        tutorIdEditText = findViewById(R.id.tutor_id_edittext);
        ratingBar = findViewById(R.id.rating_bar);
        commentEditText = findViewById(R.id.edit_comment);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        // Set click listener for submit button
        submitButton = findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get values from EditText and RatingBar
                int studentId = Integer.parseInt(studentIdEditText.getText().toString());
                int tutorId = Integer.parseInt(tutorIdEditText.getText().toString());
                int score = (int) ratingBar.getRating();
                String comment = commentEditText.getText().toString();
                Date now = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedTime = formatter.format(now);

                // Submit score to database
                mYsqliteopenhelper1.submitScore(studentId, tutorId, score);
                mYsqliteopenhelper1.submitComment(studentId, tutorId, comment, formattedTime);
                onBackPressed();
            }
        });
    }

}
