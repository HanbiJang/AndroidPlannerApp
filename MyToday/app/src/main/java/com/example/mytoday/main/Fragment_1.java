package com.example.mytoday.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Fragment_1 extends Fragment {
    // Store instance variables
    private String title;
    private int page;

    //리사이클러뷰 _ 디데이
    RecyclerView recyclerView_dday;
    //어답터 _ 디데이
    RecyclerAdapter_dday adapter_dday;

    //***
    RecyclerView recyclerView_plan;
    RecyclerAdapter_plan_today adapter_plan;
    DateData dateData1;
    //***
    // newInstance constructor for creating fragment with arguments
    public static Fragment_1 newInstance(int page, String title) {
        Fragment_1 fragment = new Fragment_1();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        //날짜 보여주는 텍스트뷰
        TextView tv_datedate = view.findViewById(R.id.tv_datedate);
        //오늘의 날짜 셋팅하기
        DateData dateData = new DateData();
        tv_datedate.setText(dateData.getDate_future(0));

        //********
        //오늘 계획 보여줌
        recyclerView_plan = view.findViewById(R.id.recyclerView_plan_today);
        adapter_plan = new RecyclerAdapter_plan_today(getContext(),recyclerView_plan);
        init_plan(recyclerView_plan,adapter_plan);
        dateData1 = new DateData();
        getData_plan(adapter_plan,recyclerView_plan,
                dateData1.getDate_future(0) );

        //오늘 디데이 보여줌
        recyclerView_dday = view.findViewById(R.id.recyclerView_dday);
        adapter_dday = new RecyclerAdapter_dday(getContext(),recyclerView_dday);
        init_dday(recyclerView_dday,adapter_dday);
        getData_dday(adapter_dday,recyclerView_dday);

        //**********
        return view;
    }


    private void init_plan(RecyclerView recyclerView, RecyclerAdapter_plan_today adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager
                (
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        true
                )
        );

        //adapter 과 recyclerAdapter 연결하기
        recyclerView.setAdapter(adapter);

    }
    private void getData_plan(RecyclerAdapter_plan_today adapter,RecyclerView recyclerView,String clickdate) {
        //클릭한 날짜에 대해서 데이터를 변경해야함
        //기준: 현재 날짜로부터 앞 뒤로 31일
        //날짜 형식 : 2020. 10/19 (sat) => getDate_future(~)

        //정보가 쌓이지 않도록 초기화 작업
        adapter.clearItem();

        String plan = null; //계획
        Integer myPlanNum = null;
        myPlanNum = PreferenceManager.getInt(getContext(), //총갯수
                "myPlanNum" + clickdate);

        if(myPlanNum>=1){
            for(int j=1; j<=myPlanNum; j++){
                plan = PreferenceManager.getString(getContext(),
                        "myPlan" + clickdate + j);
                if (plan == null) {
                    //정보 없음
                } else {
                    Data_plan data = new Data_plan();
                    data.setTitle(plan);
                    data.setThisPlanNum(j);//(==계획번호)
                    adapter.addItem(data);
                }
            }
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
        //리사이클러뷰 스크롤 조정
        recyclerView.scrollToPosition(myPlanNum-1);
    }

    //********
    private void init_dday(RecyclerView recyclerView, RecyclerAdapter_dday adapter) {
        recyclerView.setLayoutManager(new LinearLayoutManager
                (
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        true
                )
        );

        //adapter 과 recyclerAdapter 연결하기
        recyclerView.setAdapter(adapter);
    }

    //디데이 설정 함수 dday_plan
    private void getData_dday(RecyclerAdapter_dday adapter, RecyclerView recyclerView) {

        //정보가 쌓이지 않도록 초기화 작업
        adapter.clearItem();

        String myDday = null; //계획
        Integer myDdayNum = null;
        myDdayNum = PreferenceManager.getInt(getContext(), //총갯수
                "myDdayNum");

        if(myDdayNum>=1){

            //디데이 계산 전
            DateData dateData = new DateData();
            String today= dateData.getDate_future(0);

            for(int j=1; j<=myDdayNum; j++){
                myDday = PreferenceManager.getString(getContext(),
                        "myDday" + j); //"myDday" + 번호
                Data_dday data = new Data_dday();

                data.setTitle(myDday);
                data.setThisPlanNum(j);//(==디데이 번호)

                //디데이 계산
                String dday_date = PreferenceManager.getString(getContext(),"myDdayDate"+ j);//디데이 날짜
                Integer ddayDap = Long.valueOf(dateData.gap_between_two_date(today,dday_date)).intValue();
                data.setLeaveDay(ddayDap);

                adapter.addItem(data);
            }
        }
        else{ //0개, null
        }

        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
        //리사이클러뷰 스크롤 조정
        recyclerView.scrollToPosition(myDdayNum-1);
    }


    //renew 관련 함수
    @Override
    public void onResume(){
        super.onResume();
        getData_dday(adapter_dday,recyclerView_dday);
        getData_plan(adapter_plan,recyclerView_plan,
                dateData1.getDate_future(0) );

    }
}
