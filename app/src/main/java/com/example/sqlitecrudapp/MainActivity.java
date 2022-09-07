package com.example.sqlitecrudapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends  AppCompatActivity{
    EditText name, rollNum;
    Switch status;
    Button addStudentBtn;
    Button viewStudents;
    ImageButton DeleteStudent;
    ImageButton UpdateStudent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.studentName);
        rollNum=findViewById(R.id.studentRollNum);

        viewStudents=findViewById(R.id.viewList);
        addStudentBtn=findViewById(R.id.AddButton);
        DeleteStudent=findViewById(R.id.DeleteButton);
        UpdateStudent=findViewById(R.id.UpdateBtn);

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
        DeleteStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Student_RollNum = rollNum.getText().toString();
                Boolean isDeleted = db.delete(Student_RollNum);
                if (isDeleted == true) {
                    Toast.makeText(getApplicationContext(), "STUDENT with roll number " + Student_RollNum + " has been deleted", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No entry  deleted", Toast.LENGTH_LONG).show();

                }

            }
        });
        UpdateStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Student_name=name.getText().toString();
                String Student_RollNum = rollNum.getText().toString();
                Boolean isUpdated = db.Update(Student_name,Student_RollNum);
                if (isUpdated == true) {
                    Toast.makeText(getApplicationContext(), "STUDENT with roll number " + Student_RollNum + " has been updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "No entry updated", Toast.LENGTH_LONG).show();

                }

            }
        });
                viewStudents.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor c = db.getData();
                        if (c.getCount() == 0) {
                            Toast.makeText(getApplicationContext(), "NO DATA FOUND", Toast.LENGTH_LONG).show();
                            return;
                        } else {
                            StringBuffer stringBuffer = new StringBuffer();
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);

                            while (c.moveToNext()) {
                                stringBuffer.append("Name  :" + c.getString(1) + "\n");
                                stringBuffer.append("Roll Number  :" + c.getString(2) + "\n");
                            }
                            alertBuilder.setCancelable(true);
                            alertBuilder.setTitle("STUDENT LIST");
                            alertBuilder.setMessage(stringBuffer.toString());
                            alertBuilder.show();
                        }
                    }
                });


    }
}
