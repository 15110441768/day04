package com.example.anim_filling;

/**
 * 杨博钦
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.anim);
        animation.setDuration(5000);
        animation.setRepeatCount(-1);
        image.setAnimation(animation);
        animation.start();
    }
}
