package com.example.to_dolist;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> id, task, subject;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);
        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        task = new ArrayList<>();
        subject = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, id, task, subject);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        CustomAdapter.act = "Todo";

    }
    void  storeDataInArrays(){
        Cursor cursor = myDB.getTodo();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Yehey! No task to do.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                task.add(cursor.getString(1));
                subject.add(cursor.getString(2));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.option1){
            Intent i = new Intent(MainActivity.this, MainActivity.class);
            startActivity(i);
            return true;
        } else if (id==R.id.option2){
            Intent i = new Intent(MainActivity.this, DoingActivity.class);
            startActivity(i);
            return true;
        } else if (id==R.id.option3){
            Intent i = new Intent(MainActivity.this, DoneActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}