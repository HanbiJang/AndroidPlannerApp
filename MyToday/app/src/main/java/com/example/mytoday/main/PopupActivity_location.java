package com.example.mytoday.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;

public class PopupActivity_location extends Activity {

    private Button btn_gangs, btn_gwang, btn_hong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_location);


        btn_gangs = findViewById(R.id.btn_gangs);
        btn_gwang = findViewById(R.id.btn_gwang);
        btn_hong = findViewById(R.id.btn_hong);

        btn_gangs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //강서구 좌표
                String lon = "126.811960";
                String lat = "37.561038";
                PreferenceManager.setString(PopupActivity_location.this, "lon", lon);
                PreferenceManager.setString(PopupActivity_location.this, "lat", lat);

                Toast.makeText(PopupActivity_location.this, "강서구로 설정되었습니다", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(PopupActivity_location.this, StartActivity.class);
                intent.putExtra("lon",lon);
                intent.putExtra("lat",lat);

                finish();
            }
        });

        btn_gwang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //학교 좌표
                String lon = "126.976561";
                String lat = "37.571590";
                PreferenceManager.setString(PopupActivity_location.this, "lon", lon);
                PreferenceManager.setString(PopupActivity_location.this, "lat", lat);

                Toast.makeText(PopupActivity_location.this, "광화문으로 설정되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_hong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //홍대 마포구 좌표
                String lon = "126.922968";
                String lat = "37.556021";
                PreferenceManager.setString(PopupActivity_location.this, "lon", lon);
                PreferenceManager.setString(PopupActivity_location.this, "lat", lat);

                Toast.makeText(PopupActivity_location.this, "홍대입구로 설정되었습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


            }

    }
