package com.example.lab2.model;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {HistoryEntry.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase{
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "local_data";
    private static AppDatabase sInstance;

    public static AppDatabase getInstance(Context context){
        if(sInstance==null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries().addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db){
                                super.onCreate(db);
                            }
                        }).build();
            }
        }
        return sInstance;
    }
    public abstract HistoryDao historyDao();
}