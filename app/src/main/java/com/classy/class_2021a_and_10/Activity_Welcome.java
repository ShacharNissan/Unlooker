package com.classy.class_2021a_and_10;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import com.google.android.material.button.MaterialButton;

public class Activity_Welcome extends AppCompatActivity {

    private final int ANIMATION_DURATION = 2000;
    private ImageView image_welcome_logo;
    private MaterialButton button_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViews();
        initViews();
        startAnimation(image_welcome_logo);
    }

    private void startAnimation(View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        view.setY(-height / 2);


//        view.setScaleX(0.0f);
//        view.setScaleY(0.0f);
//        view.setAlpha(0.0f);
        view.animate()
//                .alpha(1.0f)
//                .scaleY(1.0f)
//                .scaleX(1.0f)
//                .rotation(360)
                .translationY(0)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        button_welcome.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) { }

                    @Override
                    public void onAnimationRepeat(Animator animator) { }
                });
    }

    private void initViews() {
        button_welcome.setVisibility(View.INVISIBLE);

        button_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Activity_Welcome.this, MainActivity.class);
                startActivity(myIntent);
            }
        });
    }

    private void findViews() {
        image_welcome_logo = findViewById(R.id.image_welcome_logo);
        button_welcome = findViewById(R.id.button_welcome);
    }
}