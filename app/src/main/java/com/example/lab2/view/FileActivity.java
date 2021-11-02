package com.example.lab2.view;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.R;
import com.example.lab2.viewmodel.FileProcessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileActivity extends AppCompatActivity {
    TextView fileText;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        Button fileBtn = findViewById(R.id.show_file_btn);
        fileBtn.setOnClickListener(v->loadFile());
        fileText = findViewById(R.id.file_text);
    }

    private void loadFile(){
        fileText.setText(FileProcessor.loadFile("file.txt"));
    }
}
