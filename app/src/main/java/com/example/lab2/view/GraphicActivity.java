package com.example.lab2.view;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2.R;

public class GraphicActivity extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic);

        image = findViewById(R.id.ImageView_for_anim);

        final Animation animationScale = AnimationUtils.loadAnimation(this,R.anim.anim_scale);
        final Animation animationRotate = AnimationUtils.loadAnimation(this,R.anim.anim_rotate);
        final Animation animationTranslate = AnimationUtils.loadAnimation(this,R.anim.anim_translate);
        final Animation animationAlpha = AnimationUtils.loadAnimation(this,R.anim.anim_alpha);


        Button scaleBtn = findViewById(R.id.scale_btn);
        scaleBtn.setOnClickListener(v->animate(animationScale));
        Button rotateBtn = findViewById(R.id.rotate_btn);
        rotateBtn.setOnClickListener(v->animate(animationRotate));
        Button translateBtn = findViewById(R.id.translate_btn);
        translateBtn.setOnClickListener(v->animate_btn(animationTranslate,translateBtn));
        Button alphaBtn = findViewById(R.id.alpha_btn);
        alphaBtn.setOnClickListener(v->animate_btn(animationAlpha,alphaBtn));
    }

    private void animate(Animation animation) {
        image.startAnimation(animation);
    }

    private void animate_btn(Animation animation, Button btn){
        btn.startAnimation(animation);
    }
}
