package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    ListView listview;
    ArrayList<String> courses_array;
    ArrayAdapter<String> adapter;
    String selected_course;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview= (ListView) findViewById(R.id.coursesList);
        courses_array =new ArrayList<String>();
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
                courses_array.add(c.getString(examCourseIndex));
//                Toast.makeText(getApplicationContext(),c.getString(examCourseIndex),Toast.LENGTH_LONG).show();
                c.moveToNext() ;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,courses_array);

        listview.setAdapter(adapter); listview.setOnItemClickListener(this::onItemSelected);


    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selected_course = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(getApplicationContext(),selected_course,Toast.LENGTH_LONG).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void openWebView(View view){

    }
}