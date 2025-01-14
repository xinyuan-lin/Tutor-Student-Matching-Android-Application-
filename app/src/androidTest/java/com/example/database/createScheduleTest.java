package com.example.database;


import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertNotNull;
        import static org.junit.Assert.assertTrue;

        import android.content.ContentValues;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import androidx.test.core.app.ApplicationProvider;
        import androidx.test.ext.junit.runners.AndroidJUnit4;

        import com.example.database.MYsqliteopenhelper;
import com.example.database.javabean.Schedule;

import org.junit.After;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;

        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.List;

@RunWith(AndroidJUnit4.class)
public class createScheduleTest {
    private MYsqliteopenhelper mYsqliteopenhelper;
    private SQLiteDatabase db;

    @Before
    public void setUp() {
        mYsqliteopenhelper = new MYsqliteopenhelper(ApplicationProvider.getApplicationContext());
        db = mYsqliteopenhelper.getWritableDatabase();
    }

    @After
    public void tearDown() {
        mYsqliteopenhelper.close();
    }

    @Test
    public void testCreateSchedule() throws ParseException {
        // Create a test schedule

        Schedule schedule = new Schedule("2023-05-06 14:30:00", "2023-05-06 15:30:00", "Math", 1, 5);



        // Insert the schedule into the database
        mYsqliteopenhelper.create_schedule(schedule);

        // Verify that the schedule was inserted
        Cursor cursor = db.query("schedule", null, null, null, null, null, null);
        assertEquals(1, cursor.getCount());
        assertTrue(cursor.moveToFirst());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = dateFormat.parse(schedule.getStart_time());
        assertEquals(startDate, cursor.getLong(cursor.getColumnIndex("start_time")));
        Date endDate = dateFormat.parse(schedule.getEnd_time());
        assertEquals(endDate, cursor.getLong(cursor.getColumnIndex("end_time")));
        assertEquals(schedule.getSubject(), cursor.getString(cursor.getColumnIndex("subject")));
        assertEquals(schedule.getStudent_id(), cursor.getInt(cursor.getColumnIndex("student_id")));
        assertEquals(schedule.getTutor_id(), cursor.getInt(cursor.getColumnIndex("tutor_id")));
        cursor.close();

        // Verify that the subject was inserted
        cursor = db.query("subject", null, null, null, null, null, null);
        assertEquals(1, cursor.getCount());
        assertTrue(cursor.moveToFirst());
        long subjectId = cursor.getLong(cursor.getColumnIndex("subject_id"));
        assertEquals(schedule.getSubject(), cursor.getString(cursor.getColumnIndex("subject_name")));
        cursor.close();

        // Verify that the tutor was inserted
        cursor = db.query("T_subject", null, null, null, null, null, null);
        assertEquals(1, cursor.getCount());
        assertTrue(cursor.moveToFirst());
        assertEquals(subjectId, cursor.getLong(cursor.getColumnIndex("subject_id")));
        assertEquals(schedule.getTutor_id(), cursor.getInt(cursor.getColumnIndex("tutor_id")));
        cursor.close();
    }
}

