package com.example.eden.facebook_login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static com.example.eden.facebook_login.MainActivity.userLogin;

public class LoginActivity extends AppCompatActivity {
    LoginButton loginButton;
    TextView textView;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button sendButton = (Button) findViewById(R.id.buttonLogin);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin = true;
                Toast.makeText(getApplicationContext(), "new login", Toast.LENGTH_LONG);

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)  findViewById(R.id.fb_login_bn);
        textView = (TextView) findViewById(R.id.textView);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                textView.setText("Login sucess \n" + loginResult.getAccessToken().getUserId() + "\n" + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                textView.setText("Login Cancelled");

            }

            @Override
            public void onError(FacebookException error) {
                textView.setText("Login Error");

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO?
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }}
