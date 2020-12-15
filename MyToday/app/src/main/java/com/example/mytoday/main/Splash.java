package com.example.mytoday.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.example.mytoday.R;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(500); //스플래시 액티비티 보이는 시간
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        Intent intent = new Intent(Splash.this, StartActivity.class);
        startActivity(intent);
        finish();

    }
}