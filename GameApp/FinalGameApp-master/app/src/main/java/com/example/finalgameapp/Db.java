package com.example.finalgameapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Db extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "game.db";
    private static final int DATABASE_VERSION = 1;

    // Define the table name and column names
    private static final String TABLE_NAME = "scores";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_SCORE = "score";
    private static final String COLUMN_QUESTIONS = "questions";
    private static final String COLUMN_ANSWERS = "answers";
    private static final String COLUMN_CORRECT_ANS = "correctAns";

    public Db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the table when the database is first created
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DATE + " DATE,"
                + COLUMN_SCORE + " INTEGER,"
                + COLUMN_QUESTIONS + " TEXT,"
                + COLUMN_ANSWERS + " TEXT,"
                + COLUMN_CORRECT_ANS + " TEXT"
                + ")";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the table if it exists and recreate it
        String dropTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTableQuery);
        onCreate(db);
    }

    public void insertData(String[] questions, String[] answers, String[] correctAnswers, int score) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, getCurrentDate());
        values.put(COLUMN_SCORE, score);
        values.put(COLUMN_QUESTIONS, convertArrayToString(questions));
        values.put(COLUMN_ANSWERS, convertArrayToString(answers));
        values.put(COLUMN_CORRECT_ANS, convertArrayToString(correctAnswers));

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String convertArrayToString(String[] array) {
        StringBuilder builder = new StringBuilder();
        for (String item : array) {
            builder.append(item).append(", ");
        }
        String result = builder.toString();
        // Remove the trailing comma
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    public ArrayList<ResultItem> getAllResults() {
        ArrayList<ResultItem> resultList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {
                COLUMN_ID,
                COLUMN_DATE,
                COLUMN_SCORE,
                COLUMN_QUESTIONS,
                COLUMN_ANSWERS,
                COLUMN_CORRECT_ANS
        };

        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
            int columnIndexDate = cursor.getColumnIndex(COLUMN_DATE);
            int columnIndexScore = cursor.getColumnIndex(COLUMN_SCORE);
            int columnIndexQuestions = cursor.getColumnIndex(COLUMN_QUESTIONS);
            int columnIndexAnswers = cursor.getColumnIndex(COLUMN_ANSWERS);
            int columnIndexCorrectAnswers = cursor.getColumnIndex(COLUMN_CORRECT_ANS);

            do {
                int id = cursor.getInt(columnIndexId);
                String date = cursor.getString(columnIndexDate);
                int score = cursor.getInt(columnIndexScore);
                String questions = cursor.getString(columnIndexQuestions);
                String answers = cursor.getString(columnIndexAnswers);
                String correctAnswers = cursor.getString(columnIndexCorrectAnswers);

                ResultItem resultItem = new ResultItem(date, score, questions, answers, correctAnswers);
                resultList.add(resultItem);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return resultList;
    }



}
