package com.example.finalassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    SQLiteDatabase sql;
    String selected_exam_website;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent secondIntent = getIntent();

        listview= (ListView) findViewById(R.id.coursesList);
        courses_array =new ArrayList<String>();
        try{

             sql= this.openOrCreateDatabase("assignmentdb",MODE_PRIVATE,null);
            sql.execSQL("CREATE Table IF NOT EXISTS exams2 (exam_course VARCHAR,exam_website VARCHAR)");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Discrete Structures II','https://www.geeksforgeeks.org/introduction-of-finite-automata/')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Parallel Programming','https://www.geeksforgeeks.org/introduction-to-parallel-computing/')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Game Development','https://docs.unity3d.com/Manual/index.html')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Mobile Develpoment','https://developer.android.com/docs')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Capstone','https://ionicframework.com/docs')");
//



            Cursor c=sql.rawQuery("Select * from exams2", null);
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
//        Toast.makeText(getApplicationContext(),selected_course,Toast.LENGTH_LONG).show();


try {
    Cursor c = sql.rawQuery("Select * from exams2 where exam_course='"+selected_course+"';", null);
    int examCourseIndex = c.getColumnIndex("exam_website");
    c.moveToFirst();

    while (c!=null){
        selected_exam_website=c.getString(examCourseIndex);

        Toast.makeText(getApplicationContext(),c.getString(examCourseIndex),Toast.LENGTH_LONG).show();
        c.moveToNext() ;
    }

}
catch(Exception e) {
    e.printStackTrace();
}

        Intent intent= new Intent(this,WebViewActivity.class);
        intent.putExtra( "COURSE_NAME",selected_exam_website);
        startActivity(intent);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void openWebView(View view){

    }
}