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

//this intent is when we go back from the webview page
        Intent secondIntent = getIntent();

        listview= (ListView) findViewById(R.id.coursesList);
        courses_array =new ArrayList<String>();
        try{


            //we start by creating thw table of the final exams which include the course name and the course's corresponding webpage
             sql= this.openOrCreateDatabase("assignmentdb",MODE_PRIVATE,null);
            sql.execSQL("CREATE Table IF NOT EXISTS exams2 (exam_course VARCHAR,exam_website VARCHAR)");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Discrete Structures II','https://www.geeksforgeeks.org/introduction-of-finite-automata/')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Parallel Programming','https://www.geeksforgeeks.org/introduction-to-parallel-computing/')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Game Development','https://docs.unity3d.com/Manual/index.html')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Mobile Develpoment','https://developer.android.com/docs')");
//            sql.execSQL("INSERT INTO exams2(exam_course,exam_website) VALUES ('Capstone','https://ionicframework.com/docs')");
//

            //We then retrieve retrieve the exam courses' names and add them to the array list "courses_array"
            Cursor c=sql.rawQuery("Select * from exams2", null);
            int examCourseIndex = c.getColumnIndex("exam_course");
            c.moveToFirst();
            while (c!=null){
                courses_array.add(c.getString(examCourseIndex));
                c.moveToNext() ;
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }


        //We create a web adapter to link the array list above to the listview.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,courses_array);
        listview.setAdapter(adapter); listview.setOnItemClickListener(this::onItemSelected);


    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
     //We retrieve the item (course name) that the user selected
        selected_course = adapterView.getItemAtPosition(i).toString();

try {

    ///Then we want to retrieve the webpage corresponding to the course selected by the user
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
//after retrieving the webpage corresponding ot the selected course name, we create a new intent and we attach the webpage url to it
        //so that the new page could open a webpage corresponding to it.s
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