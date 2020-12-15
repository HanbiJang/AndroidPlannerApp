package com.example.mytoday.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;

public class PopupActivity_dday extends Activity {
    //정보
    Integer m;
    String PlanTitle;
    //버튼
    Button btn_delete;
    Button btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_dday);

        //정보 받아오기
        Intent intent = getIntent();
        m = intent.getIntExtra("userClickPlanNum",0);
        PlanTitle = intent.getStringExtra("data.getTitle()");
        DateData dateData = new DateData();
//        String today = dateData.getDate_future(0);
//        userClickDate = today ; //오늘 날짜
//        myPlanNum = PreferenceManager.getInt(getApplicationContext(), "myPlanNum"+userClickDate); //총개수 (n)

        //연결
        btn_delete = findViewById(R.id.btn_delete);
        btn_cancel =findViewById(R.id.btn_cancel);

        //삭제 버튼
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                for(int i = m+1; i<= myPlanNum; i++){
//                    PreferenceManager.setString(getApplicationContext(), "myPlan"+userClickDate+(i-1),
//                            PreferenceManager.getString(getApplicationContext(), "myPlan"+userClickDate+(i)));
//                }
//                PreferenceManager.removeKey(getApplicationContext(),"myPlan"+userClickDate+myPlanNum); //그 계획 번호 삭제
//                PreferenceManager.setInt(getApplicationContext(),"myPlanNum"+ userClickDate , myPlanNum-1); //총개수 줄이기
//                Toast.makeText(getApplicationContext(), PlanTitle +" 디데이가 삭제되었습니다.",Toast.LENGTH_SHORT).show();

                /////////////////////////////////////
                Integer myDdayNum = PreferenceManager.getInt(getApplicationContext(), "myDdayNum"); // 디데이 총개수 (n)
                Intent intent = getIntent();
                Integer m = intent.getIntExtra("userClickDdayNum",0);
//                Integer m = data.getThisPlanNum();
                for(int i = m+1; i<= myDdayNum; i++){
                    PreferenceManager.setString(getApplicationContext(), "myDday"+(i-1),
                            PreferenceManager.getString(getApplicationContext(), "myDday"+(i)));
                }
                PreferenceManager.removeKey(getApplicationContext(),"myDday"+myDdayNum); // 디데이 순서대로 내용 옮기고 마지막 요소 삭제
                PreferenceManager.setInt(getApplicationContext(),"myDdayNum", myDdayNum-1); //디데이 총개수 하나줄이기

                //디데이  날짜 없애기
                PreferenceManager.removeKey(getApplicationContext(), "myDdayDate"+ myDdayNum);

                Toast.makeText(getApplicationContext(),"디데이가 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }

        });


        //취소 버튼
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}