package com.beetleware.task.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.BuildConfig;

import com.beetleware.task.R;
import com.beetleware.task.ui.home.HomeActivity;
import com.beetleware.task.ui.login.Login;
import com.beetleware.task.utils.UserInfo;

public class Splash extends AppCompatActivity {
    boolean isUserLogin =true;
    private int delayForSplash = BuildConfig.DEBUG ? 1000 : 1500;
    UserInfo userInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        userInfo=new UserInfo(this);
        navigateScreen();
    }
    private void navigateScreen() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (userInfo.getAccessToken().isEmpty()) {
                    Intent intent = new Intent(Splash.this, Login.class);
                    startActivity(intent);
                    finish();


                } else {
                    Intent intent = new Intent(Splash.this, HomeActivity.class);
                    startActivity(intent);
                    finish();


                }
            }
        }, delayForSplash);
    }
}
