package com.example.to_dolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Doing extends AppCompatActivity {

    TextView task_output, subject_output;
    Button move_button;

    String id, task, subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doing2);

        task_output = findViewById(R.id.task2);
        subject_output = findViewById(R.id.subject2);
        move_button = findViewById(R.id.move_button);
        move_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        getAndSetIntentData();

        move_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }
    void getAndSetIntentData(){
        if(getIntent ().hasExtra("id") && getIntent ().hasExtra("task") && getIntent ().hasExtra("subject")) {
            id = getIntent().getStringExtra("id");
            task = getIntent().getStringExtra("task");
            subject = getIntent().getStringExtra("subject");

            task_output.setText(task);
            subject_output.setText(subject);

        }
        else {
            Toast.makeText(this, "We cannot find the data you are looking for.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Move " + task + "?");
        builder.setMessage("Are you sure you've done " + task + "?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Doing.this);
                myDB.moveToDone(task_output.getText().toString().trim(),
                        subject_output.getText().toString().trim());

                myDB = new MyDatabaseHelper(Doing.this);
                myDB.deleteFromDoing(id);

                Intent intent = new Intent(Doing.this, DoingActivity.class);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.create().show();
    }
}