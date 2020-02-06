package com.example.ahmadbarazi_lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ahmadbarazi_lab8.googleAuth.login_auth;

public class SplashScreen extends AppCompatActivity {

private TextView mSlide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        mSlide = findViewById(R.id.Slide);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.left_to_right);
        mSlide.startAnimation(animation);

new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
        Intent intent = new Intent(SplashScreen.this, login_auth.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
},4500);

//        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
//                .withTargetActivity(login_auth.class)
//                .withSplashTimeOut(1500)
//                .withBackgroundColor(Color.parseColor("#1a1b29"))
//                .withBeforeLogoText("Book Store")
//                .withLogo(R.drawable.booklogo);
//
//        config.getBeforeLogoTextView().setTextSize(30);
//
//        View easySplashScreen = config.create();
//        setContentView(easySplashScreen);
    }
}
