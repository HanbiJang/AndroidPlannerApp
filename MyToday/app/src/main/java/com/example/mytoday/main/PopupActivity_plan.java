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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;

public class PopupActivity_plan extends Activity {

    Integer m;
    String PlanTitle;
    String userClickDate;
    Integer myPlanNum;
    LinearLayout layout_edit;
    EditText et_editplan;
    Button btn_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup_plan);

        //정보 받아오기
        Intent intent = getIntent();
        m = intent.getIntExtra("userClickPlanNum",0);
        PlanTitle = intent.getStringExtra("data.getTitle()");
        userClickDate = PreferenceManager.getString(getApplicationContext(), "userClickDate"); //클릭 날짜
        myPlanNum = PreferenceManager.getInt(getApplicationContext(), "myPlanNum"+userClickDate); //총개수 (n)
        layout_edit = findViewById(R.id.layout_edit);

        //수정 - 텍스트뷰, 확인버튼 숨기기
        layout_edit.setVisibility(View.INVISIBLE);

        //연결
        et_editplan = findViewById(R.id.et_editplan);
        layout_edit =findViewById(R.id.layout_edit);
        et_editplan = findViewById(R.id.et_editplan);
        btn_ok = findViewById(R.id.btn_ok);

        //미루기 버튼
        Button btn_delay = findViewById(R.id.btn_delay);
        btn_delay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //현재 날짜의 계획이 내일 날짜에 추가되고, 현재 날짜 계획 + (미뤄짐)으로 표시됨

                //현재 계획을 + (미뤄짐)으로 수정
                String new_plan ="";
                String old_plan ="";
                old_plan = PreferenceManager.getString(getApplicationContext(), "myPlan"+userClickDate+(m));
                new_plan = old_plan + " (미뤄짐)";
                PreferenceManager.setString(getApplicationContext(), "myPlan"+userClickDate+(m),
                        new_plan);
                //내일 날짜에 계획을 추가
                //유저가 클릭한 날짜 가져오기
                String planDate ="";
                planDate = PreferenceManager.getString(PopupActivity_plan.this, "userClickDate");
                String nextDay = "";
                DateData dateData = new DateData();
                nextDay = dateData.getDate_future_String(planDate,1); //내일날짜

                Integer myPlanNum = null;
                myPlanNum = PreferenceManager.getInt(PopupActivity_plan.this, "myPlanNum" + nextDay);
                if (myPlanNum >= 0) {
                    myPlanNum = myPlanNum + 1;
                    PreferenceManager.setInt(PopupActivity_plan.this, "myPlanNum" + nextDay,
                            myPlanNum);
                    PreferenceManager.setString(PopupActivity_plan.this,
                            "myPlan" + nextDay + myPlanNum, //"myPlan"+클릭한 날짜+총갯수
                            old_plan
                    );
                } else { //값이 없을 경우
                    myPlanNum = 1;
                    PreferenceManager.setString(PopupActivity_plan.this,
                            "myPlan" + nextDay + myPlanNum,
                            old_plan);
                    PreferenceManager.setInt(PopupActivity_plan.this, "myPlanNum" + nextDay,
                            myPlanNum);
                }

                Toast.makeText(PopupActivity_plan.this, "내일로 미뤘습니다", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //삭제버튼
        Button btn_delete = findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = m+1; i<= myPlanNum; i++){
                    PreferenceManager.setString(getApplicationContext(), "myPlan"+userClickDate+(i-1),
                            PreferenceManager.getString(getApplicationContext(), "myPlan"+userClickDate+(i)));
                }
                PreferenceManager.removeKey(getApplicationContext(),"myPlan"+userClickDate+myPlanNum);
                PreferenceManager.setInt(getApplicationContext(),"myPlanNum"+ userClickDate , myPlanNum-1);

                Toast.makeText(getApplicationContext(), PlanTitle +" 계획이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        //수정버튼
        //클릭한 계획의 내용이 바뀌어 저장됨
        Button btn_mo = findViewById(R.id.btn_mo);
        btn_mo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                et_editplan.setVisibility(View.VISIBLE);

                //수정 - 텍스트뷰, 확인버튼 보이기
                layout_edit.setVisibility(View.VISIBLE);
                et_editplan.setVisibility(View.VISIBLE);
                btn_ok.setVisibility(View.VISIBLE);

                //다른 메뉴들 가리기
                LinearLayout layout_menu  = findViewById(R.id.layout_menu);
                layout_menu.setVisibility(View.INVISIBLE);

                //수정 에딧텍스트 텍스트 원래 계획으로 설정
                et_editplan.setText(PreferenceManager.getString(getApplicationContext(), "myPlan"+userClickDate+(m)));
            }
        });

        //수정 - 확인 버튼
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String new_plan ="";
                new_plan = et_editplan.getText().toString();
                PreferenceManager.setString(getApplicationContext(), "myPlan"+userClickDate+(m),
                        new_plan);

                Toast.makeText(getApplicationContext(), PlanTitle +" 계획이 수정되었습니다.",Toast.LENGTH_SHORT).show();
                finish();

            }
        });



        //디데이 설정 버튼
        Button btn_repeat = findViewById(R.id.btn_repeat);
        btn_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //디데이 설정 버튼을 누르면 디데이로 저장됨
                Integer myDdayNum = null;
                myDdayNum = PreferenceManager.getInt(PopupActivity_plan.this, "myDdayNum"); // 디데이 총개수 (n)

                //누른 계획의 내용을 복사
                String targerPlan = null;

                targerPlan = PreferenceManager.getString(PopupActivity_plan.this, "myPlan"+userClickDate+(m) );

                if (myDdayNum >= 0) {
                    myDdayNum = myDdayNum + 1;
                    PreferenceManager.setInt(PopupActivity_plan.this, "myDdayNum",
                            myDdayNum);
                    PreferenceManager.setString(PopupActivity_plan.this,
                            "myDday" + myDdayNum, //"myDdayNum"+총갯수
                            targerPlan
                    );
                    //누른 계획의 날짜를 저장
                    PreferenceManager.setString(PopupActivity_plan.this, "myDdayDate"+ myDdayNum ,userClickDate);
                    Log.e("디데이 추가 부분: ","myDdayNum : " +myDdayNum );
                } else { //값이 없을 경우
                    myDdayNum = 1;
                    PreferenceManager.setString(PopupActivity_plan.this,
                            "myDday" + myDdayNum, //"myDdayNum"+총갯수
                            targerPlan
                    );
                    PreferenceManager.setInt(PopupActivity_plan.this, "myDdayNum",
                            myDdayNum); //디데이 수 설정
                    //누른 계획의 날짜를 저장
                    PreferenceManager.setString(PopupActivity_plan.this, "myDdayDate"+ myDdayNum ,userClickDate);
                    }

                    Toast.makeText(PopupActivity_plan.this, "디데이로 지정했습니다", Toast.LENGTH_SHORT).show();
                    //바깥 프레임 가지고 있는 액티비티에게 인텐트로 신호 보냄
                finish();
            }
        });

    }
}
