package ru.vsu.cs.farsharing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import lombok.var;
import ru.vsu.cs.farsharing.activity.welcome.WelcomeActivity;
import ru.vsu.cs.farsharing.databinding.SplashScreenBinding;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    private SplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        binding = SplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ImageView splashScreenImage = binding.splashScreenImage;
        Intent intent = new Intent(this, WelcomeActivity.class);
        var animation = AnimationUtils.loadAnimation(this, R.anim.shrink_and_opacity_transition);
        splashScreenImage.startAnimation(animation);
        splashScreenImage.getAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });

    }

}