package com.example.anim_frame;

/**
 * 杨博钦
 */

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private ImageView image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        image2 = (ImageView) findViewById(R.id.image2);

        final AnimationDrawable background = (AnimationDrawable) image.getBackground();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                background.start();
            }
        });

        AnimationDrawable background1 = (AnimationDrawable) image2.getBackground();
        background1.start();
    }
}
