package com.example.to_dolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.Toast;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "ToDoList2.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TODO = "todo";
    private static final String TODO_ID = "_id";
    private static final String TODO_SUBJECT = "subject";
    private static final String TODO_TASK = "task";

    private static final String TABLE_DOING = "doing";
    private static final String DOING_ID = "_id";
    private static final String DOING_SUBJECT = "subject";
    private static final String DOING_TASK = "task";

    private static final String TABLE_DONE = "done";
    private static final String DONE_ID = "_id";
    private static final String DONE_SUBJECT = "subject";
    private static final String DONE_TASK = "task";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTodo = "CREATE TABLE " + TABLE_TODO +
                " (" + TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TODO_SUBJECT + " TEXT, " +
                TODO_TASK + " TEXT);";

        String queryDoing = "CREATE TABLE " + TABLE_DOING +
                " (" + DOING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DOING_SUBJECT + " TEXT, " +
                DOING_TASK + " TEXT);";

        String queryDone = "CREATE TABLE " + TABLE_DONE +
                " (" + DONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DONE_SUBJECT + " TEXT, " +
                DONE_TASK + " TEXT);";

        db.execSQL(queryTodo);
        db.execSQL(queryDoing);
        db.execSQL(queryDone);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOING);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DONE);
        onCreate(db);
    }

//    ADDING TO DATABASE
    void addTask(String subject, String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TODO_SUBJECT, subject);
        cv.put(TODO_TASK, task);
        long result = db.insert(TABLE_TODO, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to add task!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Task added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void moveTask(String subject, String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DOING_SUBJECT, subject);
        cv.put(DOING_TASK, task);
        long result = db.insert(TABLE_DOING, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to move task!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Task moved successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void moveToDone(String subject, String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DOING_SUBJECT, subject);
        cv.put(DOING_TASK, task);
        long result = db.insert(TABLE_DONE, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed to move task!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Congratulations! You've just done a task!", Toast.LENGTH_SHORT).show();
        }
    }

//    RETRIEVING DATA
    Cursor getTodo(){
        String query = "SELECT * FROM " + TABLE_TODO;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor getDoing(){
        String query = "SELECT * FROM " + TABLE_DOING;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    Cursor getDone(){
        String query = "SELECT * FROM " + TABLE_DONE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

//    DELETE DATA
    void deleteFromTodo(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_TODO, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Cannot perform action!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteFromDoing(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_DOING, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Cannot perform action!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteFromDone(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_DONE, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Cannot perform action!", Toast.LENGTH_SHORT).show();
        }
    }
}