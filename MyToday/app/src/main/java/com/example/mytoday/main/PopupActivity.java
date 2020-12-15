package com.example.mytoday.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;

public class PopupActivity extends Activity {
    private EditText et_myplan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        //에딧텍스트 초기화
        et_myplan = findViewById(R.id.et_myplan);

        //캔슬 버튼
        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //(뒤로가기=) 액티비티(팝업) 닫기
                finish();
            }
        });

//OK 버튼
        Button btn_ok = findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 계획을 프리퍼런스-> myPlan+날짜+총갯수로 저장됨
                //각 계획의 인덱스는 1번째부터 존재함 (추가됨)
                //myPlanNun날짜 >= 0 일 경우 총갯수가 1 더하여 저장됨
                //myPlanNun날짜   = null 일 경우 총갯수가 1됨

                //유저가 클릭한 날짜 가져오기
                final String planDate;
                planDate = PreferenceManager.getString(PopupActivity.this, "userClickDate");

                //계획 텍스트 가져오기
                String myplan = et_myplan.getText().toString();
                if(myplan != null) {
                    Integer myPlanNum = null;
                    myPlanNum = PreferenceManager.getInt(PopupActivity.this, "myPlanNum" + planDate);
                    if (myPlanNum >= 0) {
                        myPlanNum = myPlanNum + 1;
                        PreferenceManager.setInt(PopupActivity.this, "myPlanNum" + planDate,
                                myPlanNum);
                        PreferenceManager.setString(PopupActivity.this,
                                "myPlan" + planDate + myPlanNum, //"myPlan"+클릭한 날짜+총갯수
                                myplan
                        );
                    } else { //값이 없을 경우
                        myPlanNum = 1;
                        PreferenceManager.setString(PopupActivity.this,
                                "myPlan" + planDate + myPlanNum,
                                myplan);
                        PreferenceManager.setInt(PopupActivity.this, "myPlanNum" + planDate,
                                myPlanNum);
                    }

                    Toast.makeText(PopupActivity.this, "저장했습니다", Toast.LENGTH_SHORT).show();
                    //바깥 프레임 가지고 있는 액티비티에게 인텐트로 신호 보냄
                    giveSignal();
                }
                else{
                    Toast.makeText(PopupActivity.this, "계획을 입력하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
//OK 버튼 끝
    }

    public void giveSignal(){
        Intent intent = new Intent();
        setResult(0,intent); //popupactivity가 종료되면 메인화면에서 onResume 메서드가 실행, 뷰를 갱신
        finish();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
}
