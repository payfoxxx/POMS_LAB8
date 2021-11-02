package com.example.lab2.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.R;
import com.example.lab2.viewmodel.HistoryFacade;

public class DataBaseActivity extends AppCompatActivity {
    TextView dbContent;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        dbContent = findViewById(R.id.db_text_view);

        Button showDbBtn = findViewById(R.id.show_db_btn);
        showDbBtn.setOnClickListener(v->showDb());
        Button clearDbBtn = findViewById(R.id.clear_db_btn);
        clearDbBtn.setOnClickListener(v->clearDb());
        Button clearScreen = findViewById(R.id.clear_screen_db_btn);
        clearScreen.setOnClickListener(v->clearScreenDb());
    }

    private void showDb() {
        dbContent.setText(HistoryFacade.getAllAsString(getBaseContext())); }
    private void clearDb(){
        HistoryFacade.deleteAll(getBaseContext());
    }
    private void clearScreenDb(){
        dbContent.setText("");
    }
}
