package com.example.database;

import android.app.appsearch.GetSchemaResponse;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.example.database.javabean.Schedule;
import com.example.database.javabean.Student;
import com.example.database.javabean.Tutor;
import com.example.database.javabean.User;
import com.example.database.javabean.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class MYsqliteopenhelper extends SQLiteOpenHelper {
    private static final String DB_NAME="MYsqlite.db";


    private static final String create_users="CREATE TABLE user ("
            + "user_id INTEGER PRIMARY KEY autoincrement,"
            + "email CHAR(50) UNIQUE,"
            + "user_name CHAR(20) UNIQUE,"
            + "password CHAR(15) NOT NULL,"
            + "phone CHAR(11),"
            + "user_type INTEGER NOT NULL)";

    /**
     * 创建tutor表
     */
    private static final String create_tutor="CREATE TABLE tutor ("
            + "tutor_id INTEGER PRIMARY KEY autoincrement,"
            + "user_id INTEGER,"
            + "intro CHAR(500),"
            + "teach_exp CHAR(500),"
            + "certificate CHAR(500),"
            + "tutor_name CHAR(40),"
            + "FOREIGN KEY (user_id) REFERENCES user(user_id)"
            + ")";

    /**
     * 创建student表
     */
    private static final String create_student="CREATE TABLE student ("
            + "student_id INTEGER PRIMARY KEY autoincrement,"
            + "user_id INTEGER,"
            + "interest CHAR(500),"
            + "education CHAR(50),"
            + "FOREIGN KEY (user_id) REFERENCES user(user_id)"
            + ")";

    /**
     * 创建schedule表
     */
    private static final String create_schedule="CREATE TABLE schedule ("
            + "schedule_id INTEGER PRIMARY KEY autoincrement,"
            + "start_time DATETIME NOT NULL,"
            + "end_time DATETIME NOT NULL,"
            + "subject CHAR(50) NOT NULL,"
            + "student_id INTEGER,"
            + "tutor_id INTEGER,"
            + "FOREIGN KEY (student_id) REFERENCES student(student_id),"
            + "FOREIGN KEY (tutor_id) REFERENCES tutor(tutor_id)"
            + ")";

    private static final String create_comment="CREATE TABLE comment ("
            + "comment_id INTEGER PRIMARY KEY autoincrement,"
            + "student_id INTEGER,"
            + "tutor_id INTEGER,"
            + "com_time DATETIME NOT NULL,"
            + "comment CHAR(500) NOT NULL,"
            + "FOREIGN KEY (student_id) REFERENCES student(student_id),"
            + "FOREIGN KEY (tutor_id) REFERENCES tutor(tutor_id)"
            + ")";

    private static final String create_score="CREATE TABLE score (" +
            "score_id INTEGER PRIMARY KEY autoincrement," +
            "student_id INTEGER," +
            "tutor_id INTEGER," +
            "score INTEGER NOT NULL," +
            "FOREIGN KEY (student_id) REFERENCES student(student_id)," +
            "FOREIGN KEY (tutor_id) REFERENCES tutor(tutor_id)" +
            ")";

    private static final String create_message="CREATE TABLE message (" +
            "message_id INTEGER PRIMARY KEY," +
            "sender_id INTEGER," +
            "receiver_id INTEGER," +
            "create_time DATETIME NOT NULL," +
            "content CHAR(500) NOT NULL," +
            "FOREIGN KEY (sender_id) REFERENCES user(user_id)," +
            "FOREIGN KEY (receiver_id) REFERENCES user(user_id)" +
            ")";

    private static final String create_subject="CREATE TABLE subject (" +
            "subject_id INTEGER PRIMARY KEY," +
            "subject_name CHAR(45) NOT NULL" +
            ")";

    private static final String create_t_subject="CREATE TABLE T_subject (" +
            "subject_id INTEGER," +
            "tutor_id INTEGER," +
            "FOREIGN KEY (subject_id) REFERENCES subject(subject_id)," +
            "FOREIGN KEY (tutor_id) REFERENCES tutor(tutor_id)" +
            ")";

    public MYsqliteopenhelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_users);
        sqLiteDatabase.execSQL(create_tutor);
        sqLiteDatabase.execSQL(create_student);
        sqLiteDatabase.execSQL(create_schedule);
        sqLiteDatabase.execSQL(create_comment);
        sqLiteDatabase.execSQL(create_score);
        sqLiteDatabase.execSQL(create_message);
        sqLiteDatabase.execSQL(create_subject);
        sqLiteDatabase.execSQL(create_t_subject);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long register(User u){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("user_name",u.getUser_name());
        cv.put("email",u.getEmail());
        cv.put("password",u.getPassword());
        cv.put("user_type",u.getUser_type());
        long user_id = db.insert("user", null, cv);

        if (u.getUser_type() == 1) {
            ContentValues cv_tutor = new ContentValues();
            cv_tutor.put("user_id", user_id);
            cv_tutor.put("tutor_name", "");
            cv_tutor.put("intro", "");
            cv_tutor.put("teach_exp", "");
            cv_tutor.put("certificate", "");
            db.insert("tutor", null, cv_tutor);
        }

        if (u.getUser_type() == 0) {
            ContentValues cv_student = new ContentValues();
            cv_student.put("user_id", user_id);
            cv_student.put("interest", "");
            cv_student.put("education", "");
            db.insert("student", null, cv_student);
        }
        db.close();
        return user_id;

    }

    /*public boolean login(String email,String password){
        SQLiteDatabase db1 = getWritableDatabase();
        boolean result =false;

        Cursor user = db1.query("user", null, "email like ?", new String[]{email}, null, null, null);
        if (user !=null){
            while (user.moveToNext()){
                String password1 = user.getString(1);
                result=password1.equals(password);
                return result;

            }

        }
        return false;

    }*/

    public boolean login(String email, String password) {
        SQLiteDatabase db1 = getWritableDatabase();
        boolean result = false;

        Cursor user = db1.query("user", null, "email like ?", new String[]{email}, null, null, null);
        if (user != null) {
            if (user.moveToFirst()) {
                String password1 = user.getString(3);
                result = password1.equals(password);
            }
            user.close();
        }

        return result;
    }

    public User getUser(String email, String password) {
        User user = null;
        SQLiteDatabase db2 = getReadableDatabase();
        String[] projection = {
                "user_id",
                "email",
                "user_name",
                "user_type",
                "phone"
        };
        Cursor cursor = db2.query("user",projection,"email = ? AND password = ?", new String[]{email, password},null,null,null);
        if (cursor.moveToFirst()){
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
            String userEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            int type = cursor.getInt(cursor.getColumnIndexOrThrow("user_type"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            user = new User(id, name, userEmail, type, phone);
        }
        cursor.close();
        db2.close();
        return user;
    }

    public String getEmailByTutorId(int tutorId){
        String email = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT user_id FROM tutor WHERE tutor_id=?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(tutorId)});
        if (cursor.moveToFirst()) {
            int userId = cursor.getInt(0);
            query = "SELECT email FROM user WHERE user_id=?";
            cursor = db.rawQuery(query, new String[]{String.valueOf(userId)});
            if (cursor.moveToFirst()) {
                email = cursor.getString(0);
            }
        }
        cursor.close();
        db.close();
        return email;
    }


    public Student getStudentById(int userId) {
        Student student = null;
        SQLiteDatabase db3 = getReadableDatabase();

        String[] projection = {
                "student_id",
                "interest",
                "education"
        };
        Cursor cursor = db3.query("student", projection, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);
        int studentid = -1;
        String intrst = "";
        String edu = "";
        if (cursor.moveToFirst()) {
            studentid = cursor.getInt(0);
            intrst = cursor.getString(1);
            edu = cursor.getString(2);
        }
        student = new Student(studentid, userId, intrst, edu);
        cursor.close();
        db3.close();
        return student;
    }

    public Tutor getTutorById(int userId) {
        Tutor tutor = null;
        SQLiteDatabase db3 = getReadableDatabase();

        String[] projection = {
                "tutor_name",
                "intro",
                "teach_exp",
                "tutor_id",
                "certificate"
        };
        Cursor cursor = db3.query("tutor", projection, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);
        int tutorId = -1;
        String intro = "";
        String teachingExp = "";
        String name = "";
        String certificate = "";
        if (cursor.moveToFirst()) {
            tutorId = cursor.getInt(3);
            intro = cursor.getString(1);
            teachingExp = cursor.getString(2);
            name = cursor.getString(0);
            certificate = cursor.getString(4);
        }
        tutor = new Tutor(tutorId, name, intro, teachingExp, certificate);
        cursor.close();
        db3.close();
        return tutor;
    }

    /*
    *创建schedule的函数
    * public void createSchedule(Date start_time, Date end_time, String subject, int student_id, int tutor_id) {
        ContentValues values = new ContentValues();
        values.put("start_time", start_time.getTime());
        values.put("end_time", end_time.getTime());
        values.put("subject", subject);
        values.put("student_id", student_id);
        values.put("tutor_id", tutor_id);

        SQLiteDatabase db4 = getWritableDatabase();
        db4.insert("schedule", null, values);
        db4.close();
    }

   }*/


    public List<CardBeanSch> getStudentSchedules(int studentId) {
        List<CardBeanSch> cardBeanSchList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selectQuery = "SELECT start_time, subject, tutor_id " +
                "FROM schedule WHERE student_id = " + studentId +
                " ORDER BY start_time";

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String startTime = cursor.getString(0);
                String subject = cursor.getString(1);
                int tutorId = cursor.getInt(2);

                //split start time into date and time
                String[] timeArray = startTime.split(" ");
                String date = timeArray[0];
                String time = timeArray[1];

                //get tutor name
                String selectTutor = "SELECT tutor_name FROM tutor WHERE tutor_id = " + tutorId;
                Cursor tutorCursor = db.rawQuery(selectTutor, null);
                String tutorName = "";
                if(tutorCursor.moveToFirst()){
                    tutorName = tutorCursor.getString(0);
                }
                tutorCursor.close();


                CardBeanSch cardBeanSch = new CardBeanSch();
                cardBeanSch.setName(tutorName);
                cardBeanSch.setDate(date);
                cardBeanSch.setTime(time);
                cardBeanSch.setSubject(subject);

                switch (tutorId % 3){
                    case 0:
                        cardBeanSch.setPic(R.mipmap.ttpic1);
                        break;
                    case 1:
                        cardBeanSch.setPic(R.mipmap.ttpic2);
                        break;
                    case 2:
                        cardBeanSch.setPic(R.mipmap.ttpic3);
                        break;
                }

                cardBeanSchList.add(cardBeanSch);


            } while (cursor.moveToNext());
        }

        // close the cursor and the database
        cursor.close();
        db.close();

        // return the schedule list
        return cardBeanSchList;
    }

    public List<CardBeanSch> getTutorSchedules(int tutorId) {
        List<CardBeanSch> cardBeanSchList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT start_time, subject, student_id " +
                "FROM schedule WHERE tutor_id = " + tutorId +
                " ORDER BY start_time";

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String startTime = cursor.getString(0);
                String subject = cursor.getString(1);
                int studentId = cursor.getInt(2);

                //split start time into date and time
                String[] timeArray = startTime.split(" ");
                String date = timeArray[0];
                String time = timeArray[1];

                //get tutor name
                String selectStudent1 = "SELECT user_id FROM student WHERE student_id = " + studentId;
                Cursor stuCursor = db.rawQuery(selectStudent1, null);
                int userId = 0;
                if (stuCursor.moveToFirst()){
                    userId = stuCursor.getInt(0);
                }
                stuCursor.close();

                String selectStudent2 = "SELECT user_name FROM user WHERE user_id = " + userId;
                Cursor studentCursor = db.rawQuery(selectStudent2, null);
                String studentName = "";
                if(studentCursor.moveToFirst()){
                    studentName = studentCursor.getString(0);
                }
                studentCursor.close();

                CardBeanSch cardBeanSch = new CardBeanSch();
                cardBeanSch.setName(studentName);
                cardBeanSch.setDate(date);
                cardBeanSch.setTime(time);
                cardBeanSch.setSubject(subject);

                switch (studentId % 2){
                    case 0:
                        cardBeanSch.setPic(R.mipmap.std1);
                        break;
                    case 1:
                        cardBeanSch.setPic(R.mipmap.std2);
                        break;
                }

                cardBeanSchList.add(cardBeanSch);


            } while (cursor.moveToNext());
        }

        // close the cursor and the database
        cursor.close();
        db.close();

        // return the schedule list
        return cardBeanSchList;
    }


    /*
    public List<CardBean> searchTutorsByName(String keyword) {
        List<CardBean> cardBeans = new ArrayList<>();
        SQLiteDatabase db6 = getReadableDatabase();
        String selectTutor = "SELECT tutor_id, tutor_name, intro FROM tutor WHERE tutor_name LIKE ?";
        Cursor cursor = db6.rawQuery(selectTutor, new String[]{"%" + keyword + "%"});

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int tutorId = cursor.getInt(0);
                String tutorName = cursor.getString(1);
                String intro = cursor.getString(2);

                String selectScore = "SELECT AVG(score) FROM score WHERE tutor_id = " + tutorId;
                int score = 0;
                Cursor scoreCursor = db6.rawQuery(selectScore,null);
                if(scoreCursor.moveToFirst()){
                    score = scoreCursor.getInt(0);
                }
                scoreCursor.close();

                String comment = "";
                String comTime = "";
                String selectComment = "SELECT comment, com_time FROM comment WHERE tutor_id = " + tutorId + " ORDER BY com_time DESC LIMIT 1";
                Cursor commentCursor = db6.rawQuery(selectComment,null);
                if(commentCursor.moveToFirst()){
                    comment = commentCursor.getString(0);
                }
                commentCursor.close();

                CardBean cardBean = new CardBean();
                switch (tutorId % 3){
                    case 0:
                        cardBean.setPic(R.mipmap.ttpic1);
                        break;
                    case 1:
                        cardBean.setPic(R.mipmap.ttpic2);
                        break;
                    case 2:
                        cardBean.setPic(R.mipmap.ttpic3);
                        break;
                }
                cardBean.setName(tutorName);
                cardBean.setTutorid(tutorId);
                cardBean.setDate(intro);
                if (TextUtils.isEmpty(comment)) {
                    cardBean.setDes("");
                } else {
                    cardBean.setDes(comment);
                }
                if(score == 0){
                    cardBean.setScore(0);
                }
                else{
                    cardBean.setScore(score);
                }

                cardBeans.add(cardBean);

            } while (cursor.moveToNext());
        }

        // close the cursor and the database
        cursor.close();
        db6.close();

        // return the tutor list
        return cardBeans;
    }
    * */

    public List<CardBean> searchTutorsByName(String keyword) {
        List<CardBean> cardBeans = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectTutor = "SELECT tutor_id, tutor_name, intro FROM tutor WHERE tutor_name LIKE ?";
        String selectScoreComment = "SELECT AVG(score), comment, com_time FROM score " +
                "LEFT JOIN comment ON score.tutor_id = comment.tutor_id " +
                "WHERE score.tutor_id = ? ORDER BY com_time LIMIT 1";
        Cursor tutorCursor = null;
        Cursor scoreCommentCursor = null;

        try {
            tutorCursor = db.rawQuery(selectTutor, new String[]{"%" + keyword + "%"});
            if (tutorCursor.getCount() == 0) {
                return cardBeans;
            }
            while (tutorCursor.moveToNext()) {
                int tutorId = tutorCursor.getInt(0);
                String tutorName = tutorCursor.getString(1);
                String intro = tutorCursor.getString(2);

                scoreCommentCursor = db.rawQuery(selectScoreComment, new String[]{String.valueOf(tutorId)});
                int score = 0;
                String comment = "";
                if (scoreCommentCursor.moveToFirst()) {
                    score = scoreCommentCursor.getInt(0);
                    comment = scoreCommentCursor.getString(1);
                }
                scoreCommentCursor.close();

                CardBean cardBean = new CardBean();
                switch (tutorId % 3) {
                    case 0:
                        cardBean.setPic(R.mipmap.ttpic1);
                        break;
                    case 1:
                        cardBean.setPic(R.mipmap.ttpic2);
                        break;
                    case 2:
                        cardBean.setPic(R.mipmap.ttpic3);
                        break;
                }
                cardBean.setName(tutorName);
                cardBean.setTutorid(tutorId);
                cardBean.setDate(intro);
                if (TextUtils.isEmpty(comment)) {
                    cardBean.setDes("");
                } else {
                    cardBean.setDes(comment);
                }
                cardBean.setScore(score);
                cardBeans.add(cardBean);
            }
        } finally {
            if (tutorCursor != null) {
                tutorCursor.close();
            }
            if (scoreCommentCursor != null) {
                scoreCommentCursor.close();
            }
            db.close();
        }
        return cardBeans;
    }



    public Tutor getTutorDetailById(int tutorId){
        Tutor tutor = null;
        SQLiteDatabase db7 = getReadableDatabase();
        String[] projection = {
                "tutor_name",
                "intro",
                "teach_exp"
        };
        Cursor cursor = null;
        try{
            cursor = db7.query("tutor", projection, "tutor_id = ?", new String[]{String.valueOf(tutorId)}, null, null, null);
            if(cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(0);
                String intro = cursor.getString(1);
                String teachingExp = cursor.getString(2);
                tutor = new Tutor(tutorId, name, intro, teachingExp);
            }
        }finally {
            if(cursor != null){
                cursor.close();
            }
            db7.close();
        }
        return tutor;
    }

    public int getUserIdByStudentId(int studentId){
        SQLiteDatabase db9 = getReadableDatabase();
        Cursor cursor = null;
        int userId = -1;
        try{
            cursor = db9.query("student", new String[]{"user_id"}, "student_id = ?", new String[]{String.valueOf(studentId)}, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                userId = cursor.getInt(cursor.getColumnIndexOrThrow("user_id"));
            }
        } finally {
            if(cursor != null){
                cursor.close();
            }
            db9.close();
        }
        return userId;
    }

    public String getUserNameByUserId(int userId){
        SQLiteDatabase db10 = getReadableDatabase();
        Cursor cursor = null;
        String userName = "";
        try{
            cursor = db10.query("user", new String[]{"user_name"}, "user_id = ?", new String[]{String.valueOf(userId)}, null, null, null);
            if(cursor != null && cursor.moveToFirst()){
                userName = cursor.getString(cursor.getColumnIndexOrThrow("user_name"));
            }
        } finally {
            if(cursor != null){
                cursor.close();
            }
            db10.close();
        }
        return userName;
    }

    public List<card3Bean> getCommentsByTutorId(int tutorId){
        List<card3Bean> commentList = new ArrayList<>();
        SQLiteDatabase db8 = getReadableDatabase();
        String[] projection = {
                "student_id",
                "comment",
                "com_time"
        };
        Cursor cursor = null;
        try {
            cursor = db8.query("comment", projection, "tutor_id = ?", new String[]{String.valueOf(tutorId)},null,null,null);
            if(cursor != null && cursor.moveToFirst()){
                do {
                    int studentId = cursor.getInt(0);
                    String comment = cursor.getString(1);
                    String time = cursor.getString(2);
                    String username = getUserNameByUserId(getUserIdByStudentId(studentId));
                    card3Bean card3Bean = new card3Bean();
                    switch (studentId % 2){
                        case 0:
                            card3Bean.setPic(R.mipmap.std1);
                            break;
                        case 1:
                            card3Bean.setPic(R.mipmap.std2);
                            break;
                    }
                    card3Bean.setComment(comment);
                    card3Bean.setDate(time);
                    card3Bean.setName(username);
                    commentList.add(card3Bean);
                } while (cursor.moveToNext());
            }
        } finally {
            if(cursor != null){
                cursor.close();
            }
            db8.close();
        }
        return commentList;
    }

    public void submitScore(int studentId, int tutorId, int score){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tutor_id", tutorId);
        values.put("student_id", studentId);
        values.put("score", score);
        db.insert("score",null,values);
        db.close();
    }

    public void submitComment(int studentId, int tutorId, String comment, String com_time){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tutor_id", tutorId);
        values.put("student_id", studentId);
        values.put("comment", comment);
        values.put("com_time", com_time);
        db.insert("comment",null,values);
        db.close();
    }


    public void create_schedule(Schedule schedule){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String startTime = dateFormat.format(schedule.getStart_time());
        //String endTime = dateFormat.format(schedule.getEnd_time());

        values.put("start_time", schedule.getStart_time());
        values.put("end_time", schedule.getEnd_time());
        values.put("subject", schedule.getSubject());
        values.put("student_id", schedule.getStudent_id());
        values.put("tutor_id", schedule.getTutor_id());
        db.insert("schedule", null, values);
        //db.close();

        Cursor cursor = null;
        long subjectId = 0;
        cursor = db.query("subject", new String[]{"subject_id"}, "subject_name=?", new String[]{schedule.getSubject()}, null, null, null);

        /*
        if (cursor.getCount() > 0){
            ContentValues subjectValues = new ContentValues();
            subjectValues.put("subject_name", schedule.getSubject());
            subjectId = db.insert("subject", null, subjectValues);
        }else{
            //subjectId = cursor.getLong(0);
        }
        */

        if(cursor.moveToFirst()){
            // 游标不为空，执行相关操作
            subjectId = cursor.getLong(0);
        } else {
            // 游标为空，未找到匹配项
            ContentValues subjectValues = new ContentValues();
            subjectValues.put("subject_name", schedule.getSubject());
            subjectId = db.insert("subject", null, subjectValues);
        }
        cursor.close();

        Cursor cursor1 = null;
        cursor1 = db.query("T_subject", new String[]{"subject_id", "tutor_id"}, "subject_id=? AND tutor_id=?", new String[]{Long.toString(subjectId), Integer.toString(schedule.getTutor_id())}, null, null, null);

        if (!cursor1.moveToFirst()) {
            ContentValues tSubjectValues = new ContentValues();
            tSubjectValues.put("subject_id", subjectId);
            tSubjectValues.put("tutor_id", schedule.getTutor_id());
            db.insert("T_subject", null, tSubjectValues);
        }
        cursor1.close();
        db.close();
    }

    public void updateTutorIntro(int userId, String intro) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("intro", intro);
        db.update("tutor", values, "user_id = ?", new String[]{String.valueOf(userId)});
    }

    public void updateStudentInterest(int userId, String interest) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("interest", interest);
        db.update("student", values, "user_id = ?", new String[]{String.valueOf(userId)});
    }

    public void updateStudentEdu(int userId, String edu) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("education", edu);
        db.update("student", values, "user_id = ?", new String[]{String.valueOf(userId)});
    }

    public void updatePhone(int userId, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        db.update("user", values, "user_id = ?", new String[]{String.valueOf(userId)});
    }

    public void updateTutorName(int userId, String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tutor_name", name);
        db.update("tutor", values, "user_id = ?", new String[]{String.valueOf(userId)});
    }

    public void updateTutorTeachExp(int userId, String teachExp) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("teach_exp", teachExp);
        db.update("tutor", values, "user_id = ?", new String[]{String.valueOf(userId)});
    }








    /*public int update(String name,String NEWpassword,String password){
        SQLiteDatabase db2 = getWritableDatabase();
        ContentValues cv1=new ContentValues();
        boolean login = login(name, password);
        if (login){
            cv1.put("name",name);
            cv1.put("password",NEWpassword);
        }else {
            int o=-1;
            return o;
        }

        int i = db2.update("users", cv1, "name= ?", new String[]{name});
        return i;

    }*/

}
