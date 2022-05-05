package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try{

            SQLiteDatabase sql= this.openOrCreateDatabase("assignmentdb",MODE_PRIVATE,null);
            sql.execSQL("CREATE Table IF NOT EXISTS exams (exam_course VARCHAR)");
//            sql.execSQL(("INSERT INTO exams(exam_course) VALUES ('Discrete Structures II')"));
//            sql.execSQL(("INSERT INTO exams(exam_course) VALUES ('Parallel Programming')"));
//            sql.execSQL(("INSERT INTO exams(exam_course) VALUES ('Game Development')"));
//            sql.execSQL(("INSERT INTO exams(exam_course) VALUES ('Mobile Develpoment')"));
//            sql.execSQL(("INSERT INTO exams(exam_course) VALUES ('Capstone')"));


            Cursor c=sql.rawQuery("Select * from exams", null);
            int examCourseIndex = c.getColumnIndex("exam_course");
            c.moveToFirst();
            while (c!=null){
                Toast.makeText(getApplicationContext(),c.getString(examCourseIndex),Toast.LENGTH_LONG).show();
                c.moveToNext() ;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}