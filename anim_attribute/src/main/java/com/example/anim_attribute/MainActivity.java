package com.example.anim_attribute;

/**
 * 杨博钦
 */

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        text = (TextView) findViewById(R.id.text);

        ValueAnimator animator = ValueAnimator.ofInt(5, 0);
        animator.setDuration(6000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                text.setText(value+"");
            }
        });
        animator.start();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(text, "alpha", 1.0f, 0.0f, 1.0f);
        animator1.setInterpolator(new LinearInterpolator());
        animator1.setDuration(10000);
        animator1.start();
    }
}
