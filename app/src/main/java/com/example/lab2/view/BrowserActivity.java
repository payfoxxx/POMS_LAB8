package com.example.lab2.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.R;

public class BrowserActivity extends AppCompatActivity {

    EditText browserLink;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        browserLink = findViewById(R.id.browser_link);
        Button browserOpen = findViewById(R.id.open_browser_link);
        //Button cameraOpen = findViewById(R.id.open_camera_btn);
        browserOpen.setOnClickListener(v->openBrowser());
        //cameraOpen.setOnClickListener(v->openCamera());
    }

    public void openBrowser(){
        String link = browserLink.getText().toString();
        String substring = "http://";
        if(!link.contains(substring)){
            link = substring+link;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(link));
        startActivity(intent);
    }

    /*public void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    } */
}
