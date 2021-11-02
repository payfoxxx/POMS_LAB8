package com.example.lab2.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.R;

public class IntentCatcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_catcher);

        ImageView imageView = findViewById(R.id.shared_picture);
        Intent intent = getIntent();
        if(intent!=null){
            Uri imageUrl = intent.getParcelableExtra(Intent.EXTRA_STREAM);
            Bundle extras = getIntent().getExtras();
            imageView.setImageURI(imageUrl);
        }
    }
}
