package com.example.sqlitecrudapp;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name=findViewById(R.id.studentName);
        rollNum=findViewById(R.id.studentRollNum);
        status=findViewById(R.id.IsEnrolled);

        addStudentBtn=findViewById(R.id.viewList);
        DBHelper db =new DBHelper(this);
        addStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Student_name=name.getText().toString();
                String Student_RollNum=rollNum.getText().toString();
                boolean Student_status=status.getShowText();
                boolean x=true;
                Boolean isAdded =db.AddStudent(Student_name,Student_RollNum,x);
                if (isAdded==true)
                {
                    Toast.makeText(getApplicationContext(),"NEW STUDENT ADDED",Toast.LENGTH_LONG).show();
                }
                
            }
        });


    }
}
