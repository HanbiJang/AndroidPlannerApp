package com.example.mytoday.main;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytoday.R;
import com.example.mytoday.main.preference.PreferenceManager;
import com.example.mytoday.main.weather.IconMatch;
import com.example.mytoday.main.weather.Weather_list;
import com.example.mytoday.main.weather.Weather_xml_to_android;
import com.example.mytoday.main.weather.Weather_main;
import com.example.mytoday.main.weather.Weather_to_class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.media.CamcorderProfile.get;

public class Fragment_2 extends Fragment {
    // Store instance variables
    private String title;
    private int page;
    //리사이클러뷰
    RecyclerView recyclerView;
    //어답터
    RecyclerAdapter adapter;
    //리사이클러뷰 _플랜
    RecyclerView recyclerView_plan;
    //어답터 _플랜
    RecyclerAdapter_plan adapter_plan;
    //날씨 정보
    Weather_main weather_main;

    // newInstance constructor for creating fragment with arguments
    public static Fragment_2 newInstance(int page, String title) {
        Fragment_2 fragment = new Fragment_2();
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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        //날짜 리사이클러뷰 클릭 값 초기화
        PreferenceManager.removeKey(getContext(),"userClickDate");
        //날짜 보여주는 텍스트뷰
        TextView tv_datedate = view.findViewById(R.id.tv_datedate);
        //2주간 날짜와 날씨 보여주는 리사이클러뷰
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecyclerAdapter(getContext(),tv_datedate);
        init(recyclerView,adapter);
        //각 날의 계획들 보여주는 리사이클러뷰
        recyclerView_plan = view.findViewById(R.id.recyclerView_plan);
        adapter_plan = new RecyclerAdapter_plan(getContext(),recyclerView_plan);
        init_plan(recyclerView_plan,adapter_plan);


        //날씨 데이터 관련
        int THREAD_HANDLER_SUCCESS_INFO = 1;
        Weather_xml_to_android mForeCast;
        Weather_main mThis;
        ArrayList<ContentValues> mWeatherData;
        ArrayList<Weather_to_class> mWeatherInfomation;

        Weather_main weather_main = new Weather_main();
        //날씨정보를 가져와서 리스트로 뽑아내기
        weather_main.Initialize(getContext());
        //날짜,날씨 리싸이클러뷰 데이터 셋팅
        getData(adapter,recyclerView);

        //하루의 각 계획 보여주는 리사이클러뷰 데이터 셋팅
        adapter.setAdapter_plan(adapter_plan);
        adapter.setRecyclerView_plan(recyclerView_plan);

        //계획 추가 버튼
        FloatingActionButton imgbtn_plus = view.findViewById(R.id.imgbtn_plus);
        imgbtn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //계획 추가 작업
                //글을 쓸 수 있는 팝업창이 뜨고

                if(PreferenceManager.getString(getContext(),"userClickDate") != null){
                    Intent intent = new Intent(getContext(), PopupActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(), "날짜를 선택하세요!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //위치 지정 버튼
        //위치 지정 버튼
        FloatingActionButton imgbtn_location = view.findViewById(R.id.imgbtn_location);
        imgbtn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),PopupActivity_location.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void init(RecyclerView recyclerView, RecyclerAdapter adapter) {
        //리사이클러뷰를 가로로 스크롤하게 만들기
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setLayoutManager(new LinearLayoutManager
                (
                        getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        true
                )
        );

        //adapter 과 recyclerAdapter 연결하기
        recyclerView.setAdapter(adapter);

    }

    private void init_plan(RecyclerView recyclerView, RecyclerAdapter_plan adapter) {
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

    //날짜 날씨 리사이클러 뷰에 들어갈 데이터들
    private void getData(RecyclerAdapter adapter,RecyclerView recyclerView) {
        // listTitle : 날짜 정보
        // 현재 날짜로부터 앞 뒤로 한달 보여주기
        //정보가 쌓이지 않도록 초기화 작업
        adapter.clearItem();

        DateData dateData = new DateData();
        String[] arrays_date = new String[63];
        Integer[] arrays_planNum = new Integer[63];
        for(int i = -31 ; i <= 31 ; i++){
            arrays_date[i+31] = dateData.getDate_future(i);
            arrays_planNum[i+31] =  PreferenceManager.getInt(getContext(),
                    "myPlanNum"+ arrays_date[i+31]);
        }
        List<String> listTitle = Arrays.asList(arrays_date);
        List<Integer> listPlanNum = Arrays.asList(arrays_planNum);
        // listContent : 날씨 정보 (3개의 날씨 데이터)
        List<String> listContent = Arrays.asList
                (
                PreferenceManager.getString(getContext(),"todayWeather") + " ("+
                        //최저/최고,
                        (PreferenceManager.getString(getContext(),"todayWeather_min")) +"도/"+
                        (PreferenceManager.getString(getContext(),"todayWeather_max")) +"도)"
                        ,

                PreferenceManager.getString(getContext(),"tomorrowWeather_1") + " ("+
                        //최저/최고,
                        (PreferenceManager.getString(getContext(),"tomorrowWeather_1_min")) +"도/"+
                        (PreferenceManager.getString(getContext(),"tomorrowWeather_1_max")) +"도)"
                        ,
                PreferenceManager.getString(getContext(),"tomorrowWeather_2") + " ("+
                        //최저/최고,
                        (PreferenceManager.getString(getContext(),"tomorrowWeather_2_min")) +"도/"+
                        (PreferenceManager.getString(getContext(),"tomorrowWeather_2_max")) +"도)"

                );

        //리스트뷰에 들어갈 사진 설정
        List<Integer> listResId = Arrays.asList(
                findIcon(PreferenceManager.getString(getContext(),"todayWeather")),
                findIcon(PreferenceManager.getString(getContext(),"tomorrowWeather_1")),
                findIcon(PreferenceManager.getString(getContext(),"tomorrowWeather_2"))
        );

        for (int i = 0; i < listTitle.size(); i++) {
            // 각 List의 값들을 data 객체에 set 해줍니다.
            Data data = new Data();
            //리스트뷰 아이템이 거꾸로 들어가서 수정해줌
            data.setTitle(listTitle.get(listTitle.size()-1-i)); //날짜
            if(listPlanNum != null){
                if(listPlanNum.get(listPlanNum.size()-1-i) > 0){
                    data.setPlanNum(listPlanNum.get(listPlanNum.size()-1-i));
                }
                else { //0
                        data.setPlanNum(0);
                }

            }
            else{
                data.setPlanNum(0);
            }
            if(i>=29 && i<=31){
                data.setContent(listContent.get(listContent.size()-1-(i-29))); //날씨1
                data.setResId(listResId.get(listResId.size()-1-(i-29))); //날씨그림
            }
            else{
                data.setContent(" ");
                data.setResId(R.drawable.empty);
            }
            // 각 값이 들어간 data를 adapter에 추가합니다.
            adapter.addItem(data);
        }
        // adapter의 값이 변경되었다는 것을 알려줍니다.
        adapter.notifyDataSetChanged();
        //리사이클러뷰 스크롤 위치
        recyclerView.scrollToPosition(30);

    }

    private void getData_plan(RecyclerAdapter_plan adapter,RecyclerView recyclerView,String clickdate) {
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

    Integer findIcon(String weatherName){
        IconMatch iconMatch = new IconMatch();
        for(int i =0; i<iconMatch.weathersList_Hangeul.size()-1;i++){
            if(weatherName.equals(iconMatch.weathersList_Hangeul.get(i).getMeaning())){
                return iconMatch.weathersList_Hangeul.get(i).getIcon();
            }
        }
        return R.drawable.empty;
    }

    //renew 관련 함수
    @Override
    public void onResume(){
        super.onResume();
        getData_plan(adapter_plan,recyclerView_plan,
                PreferenceManager.getString(getContext(), "userClickDate"));
        getData(adapter, recyclerView);

        //날씨 관련
        Intent intent = new Intent();
        String lon = intent.getStringExtra("lon");
        String lat = intent.getStringExtra("lat");
        Weather_main weather_main = new Weather_main();
        //날씨정보를 가져와서 리스트로 뽑아내기
        if(lon != null && lat != null){
            weather_main.Initialize(getContext(),lon,lat);
        }
        else{
            weather_main.Initialize(getContext());
        }
        //날짜,날씨 리싸이클러뷰 데이터 셋팅
        getData(adapter,recyclerView);
    }

}




