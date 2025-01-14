package com.example.database;



        import android.app.DatePickerDialog;
        import android.app.TimePickerDialog;
        import android.os.Bundle;
        import android.text.format.DateFormat;
        import android.view.View;
        import android.widget.Button;
        import android.widget.DatePicker;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.TimePicker;
        import android.widget.Toast;

        import androidx.appcompat.app.AppCompatActivity;

        import com.example.database.javabean.Schedule;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;

public class createcourse extends AppCompatActivity {

    private MYsqliteopenhelper mYsqliteopenhelper1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcourse);
        mYsqliteopenhelper1 = new MYsqliteopenhelper(this);

        ImageView back = findViewById(R.id.back1);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        EditText startTimeEditText = findViewById(R.id.btn_start_time);
        EditText endTimeEditText = findViewById(R.id.btn_end_time);
        Button createbtn = findViewById(R.id.btn_create);
        EditText subEdt = findViewById(R.id.et_subject);
        EditText stuEdt = findViewById(R.id.et_student_id);
        EditText tutEdt = findViewById(R.id.et_tutor_id);

        startTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker(startTimeEditText);
            }
        });

        endTimeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimePicker(endTimeEditText);
            }
        });


        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject = subEdt.getText().toString();
                int stuId = Integer.parseInt(stuEdt.getText().toString());
                int tutId = Integer.parseInt(tutEdt.getText().toString());
                String startTime = startTimeEditText.getText().toString().trim();
                String endTime = endTimeEditText.getText().toString().trim();
                Schedule schedule = new Schedule(startTime, endTime, subject, stuId, tutId);
                mYsqliteopenhelper1.create_schedule(schedule);
                onBackPressed();
            }
        });



    }

    private void showDateTimePicker(final EditText editText) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog timePickerDialog = new TimePickerDialog(createcourse.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateTime = dateFormat.format(calendar.getTime());
                        editText.setText(dateTime);
                    }
                },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        true);
                timePickerDialog.show();
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
}