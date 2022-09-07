package com.example.sqlitecrudapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends  AppCompatActivity{
    EditText name, rollNum;
    Switch status;
    Button addStudentBtn;
    Button viewStudents;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.studentName);
        rollNum=findViewById(R.id.studentRollNum);

        viewStudents=findViewById(R.id.viewList);
        addStudentBtn=findViewById(R.id.AddButton);
        DBHelper db =new DBHelper(this);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Student_name=name.getText().toString();
                String Student_RollNum=rollNum.getText().toString();
                Boolean isAdded =db.AddStudent(Student_name,Student_RollNum);
                if (isAdded==true)
                {
                    Toast.makeText(getApplicationContext(),"NEW STUDENT ADDED",Toast.LENGTH_LONG).show();
                }

            }
        });
        viewStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c=db.getData();
            if (c.getCount()==0)
            {
                Toast.makeText(getApplicationContext(),"NO DATA FOUND",Toast.LENGTH_LONG).show();
                return;
            }
            else
            {
                StringBuffer stringBuffer=new StringBuffer();
                while(c.moveToNext())
                {
                    stringBuffer.append("STUDENT_NAME :"+c.getString(1)+"\n");
                    stringBuffer.append("STUDENT_ROLLNUM :"+c.getString(2)+"\n");
                    
                }
                AlertDialog.Builder alertBuilder=new AlertDialog.Builder(MainActivity.this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle("STUDENT LIST");
                alertBuilder.setMessage(stringBuffer.toString());
                alertBuilder.show();
            }
            }
        });


    }
}
