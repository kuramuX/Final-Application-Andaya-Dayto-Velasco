package com.example.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText task_input, subject_input;
    Button add_button;
    Context context;
    String tsk, subjct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        task_input = findViewById(R.id.task);
        subject_input = findViewById(R.id.subject);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tsk = task_input.getText().toString().trim();
                subjct = subject_input.getText().toString().trim();
                if (tsk!=""){
                    filled();
                } else{
                   Toast.makeText(context, "Please fill the text fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void filled(){
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
        myDB.addTask(task_input.getText().toString().trim(),
                subject_input.getText().toString().trim());
        Intent i = new Intent(AddActivity.this, MainActivity.class);
        startActivity(i);
    }
}