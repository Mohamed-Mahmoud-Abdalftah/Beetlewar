package com.beetleware.task.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.beetleware.task.R;
import com.beetleware.task.data.network.ApiFactory;
import com.beetleware.task.ui.home.HomeActivity;
import com.beetleware.task.utils.AppUtils;
import com.beetleware.task.utils.Dialogues;
import com.beetleware.task.utils.UserInfo;

public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    Button login;
    EditText txtEmailAddress, txtPassword;
    UserInfo userInfo;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        initViews();
        obtainViewModel();
        subscribeToUI();

    }

    private void subscribeToUI() {
        loginViewModel.getUser().observe(this, new Observer<modelLogin>() {
            @Override
            public void onChanged(@Nullable modelLogin loginModel) {
                Dialogues.DismasDialog();
                userInfo.seAccessToken(loginModel.getData().getAuthorization());
                Intent intent = new Intent(Login.this, HomeActivity.class);
                startActivity(intent);
                finishAffinity();

//                Log.d(TAG, "onChanged: ");
//                startActivity(new Intent(Login.this, CarOwner.class));
//                overridePendingTransition(R.anim.bottom_down, R.anim.nothing);
            }
        });
        loginViewModel.onFailureData().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {

                Dialogues.DismasDialog();
                Dialogues.CreatePoPup(Login.this, "Try Again", "Try Again");
            }
        });

        loginViewModel.UserDateError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Dialogues.DismasDialog();
                Dialogues.CreatePoPup(Login.this, "Try Again", "Try Again");
            }
        });
    }


    private void obtainViewModel() {
        loginViewModel = ViewModelProviders.of(Login.this).get(LoginViewModel.class);
        loginViewModel.setWebService(ApiFactory.createApi());
    }

    private void initViews() {
        userInfo = new UserInfo(this);
        login = findViewById(R.id.login);
        txtEmailAddress = findViewById(R.id.txtusername);
        txtPassword = findViewById(R.id.textPassword);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (txtEmailAddress.getText().toString().isEmpty()){
                     Dialogues.CreatePoPup(Login.this, "please Enter UserName", "please Enter UserName");
                }else if (txtPassword.getText().toString().isEmpty()){
                     Dialogues.CreatePoPup(Login.this, "please Enter UserName", "please Enter password");
                }else {
                    if (AppUtils.getInstance().checkNetWork(Login.this)) {
                        Dialogues.CreateDialog(Login.this);
                        loginViewModel.isDataValid(txtEmailAddress.getText().toString(), txtPassword.getText().toString());


                    }
                }

            }
        });


    }
}
