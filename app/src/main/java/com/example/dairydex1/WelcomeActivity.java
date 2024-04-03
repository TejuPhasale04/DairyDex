package com.example.dairydex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dairydex1.R;
import com.example.dairydex1.Registration;
import com.example.dairydex1.activity_login;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void login(View view) {
        startActivity(new Intent(WelcomeActivity.this, activity_login.class));
    }

    public void registration(View view) {
        startActivity(new Intent(WelcomeActivity.this, Registration.class));
    }
}
