package com.example.mytoday.main.weather;

import android.content.ContentValues;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.mytoday.main.DateData;
import com.example.mytoday.main.preference.PreferenceManager;

import java.util.ArrayList;

public class Weather_main extends AppCompatActivity {
    public static final int THREAD_HANDLER_SUCCESS_INFO = 1;

    Weather_xml_to_android mForeCast;
    Context context;

    Weather_main mThis;
    ArrayList<ContentValues> mWeatherData;
    ArrayList<Weather_to_class> mWeatherInfomation;

    public void Initialize(Context context)
    {
        this.context = context;
        mWeatherInfomation = new ArrayList<>();
        mThis = this;

        //위치 설정
        String lon = PreferenceManager.getString(context, "lon");
        String lat = PreferenceManager.getString(context, "lat");

        if(lon != null && lat != null){
            mForeCast = new Weather_xml_to_android(lon, lat, mThis);
        }
        else{
            mForeCast = new Weather_xml_to_android("0", "0", mThis); //날씨정보 없음
        }

        mForeCast.run();
    }

    public void Initialize(Context context,String lon, String lat)
    {
        this.context = context;
        mWeatherInfomation = new ArrayList<>();
        mThis = this;

        //위치 설정 (인텐트)
        mForeCast = new Weather_xml_to_android(lon, lat, mThis);

        mForeCast.run();
    }

    public String PrintValue()
    {
        String mData = "";
        for(int i = 0; i < mWeatherInfomation.size(); i ++)
        {
            mData = mData + mWeatherInfomation.get(i).getWeather_Day() + "\r\n"
                    +"시간: "+ mWeatherInfomation.get(i).getWeather_Day_time() + "\r\n"
                    +  mWeatherInfomation.get(i).getWeather_Name() + "\r\n"
//                    +  mWeatherInfomation.get(i).getClouds_Sort()
//                    +  " /Cloud amount: " + mWeatherInfomation.get(i).getClouds_Value()
//                    +  mWeatherInfomation.get(i).getClouds_Per() +"\r\n"
//                    +  mWeatherInfomation.get(i).getWind_Name()
//                    +  " /WindSpeed: " + mWeatherInfomation.get(i).getWind_Speed() + " mps" + "\r\n"
                    +  "최고 기온: " + mWeatherInfomation.get(i).getTemp_Max() + "℃"
                    +  " /최소 기온: " + mWeatherInfomation.get(i).getTemp_Min() + "℃" +"\r\n";

//                    +  "Humidity: " + mWeatherInfomation.get(i).getHumidity() + "%";

            mData = mData + "\r\n" + "----------------------------------------------" + "\r\n";
        }
        return mData;
    }

    public ArrayList DataChangedToHangeul()
    {
        ArrayList<WeatherClass_needed> allWeathers = new ArrayList<>();
        for(int i = 0 ; i <mWeatherInfomation.size(); i ++)
        {
            Weather_to_hangeul mHangeul = new Weather_to_hangeul(mWeatherInfomation.get(i));
            mWeatherInfomation.set(i,mHangeul.getHangeulWeather());

        }
        //한글화된 정보를 받아옴
        //클래스에 날씨 정보를 저장해서 반환하기
        return mWeatherInfomation;
    }


    public ArrayList DataToInformation() {
        //날씨정보
        ArrayList<WeatherClass_needed> allWeathers = new ArrayList();
        for (int i = 0; i < mWeatherData.size(); i++) {
            mWeatherInfomation.add
                    (
                            new Weather_to_class(

                                    String.valueOf(mWeatherData.get(i).get("weather_Name")),
                                    String.valueOf(mWeatherData.get(i).get("weather_Number")),
//                    String.valueOf(mWeatherData.get(i).get("weather_Much")),
//                    String.valueOf(mWeatherData.get(i).get("weather_Type")),
//                    String.valueOf(mWeatherData.get(i).get("wind_Direction")),
//                    String.valueOf(mWeatherData.get(i).get("wind_SortNumber")),
//                    String.valueOf(mWeatherData.get(i).get("wind_SortCode")),
//                    String.valueOf(mWeatherData.get(i).get("wind_Speed")),
//                    String.valueOf(mWeatherData.get(i).get("wind_Name")),
                                    String.valueOf(mWeatherData.get(i).get("temp_Min")),
                                    String.valueOf(mWeatherData.get(i).get("temp_Max")),
//                    String.valueOf(mWeatherData.get(i).get("humidity")),
//                    String.valueOf(mWeatherData.get(i).get("Clouds_Value")),
//                    String.valueOf(mWeatherData.get(i).get("Clouds_Sort")),
//                    String.valueOf(mWeatherData.get(i).get("Clouds_Per")),
                                    String.valueOf(mWeatherData.get(i).get("weather_Day"))
                            )
                    );
        }
        return mWeatherInfomation;
        //수정한곳
    }
    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case THREAD_HANDLER_SUCCESS_INFO :
                    mWeatherData = mForeCast.getmWeather();

                    if(mWeatherData.size() ==0) {
                        Toast.makeText(context, "위치를 설정해주세요", Toast.LENGTH_SHORT).show();
                        Log.e("날씨조회 결과", ": 데이터가 없습니다");
                    }
                    else{
                        DataToInformation(); // 자료 클래스로 저장, ArrayList로 반환


                        String data = "";
                        data = PrintValue();

                        //한글 번역과정
                        ArrayList<Weather_to_class> allWeather_t = new ArrayList();
                        allWeather_t = DataChangedToHangeul();
                        data = PrintValue();

                        //한글화된 정보 표시하기
                        //한글화된 날씨중 어제,오늘,내일 날씨 중 첫번째 가져오기 , 기온포함
                        String todayWeather,tomorrowWeather_1,tomorrowWeather_2;
                        String todayWeather_max,tomorrowWeather_1_max,tomorrowWeather_2_max;
                        String todayWeather_min,tomorrowWeather_1_min,tomorrowWeather_2_min;
                        int todayWeather_index =0;
                        int tomorrowWeather_1_index =0;
                        int tomorrowWeather_2_index =0;
                        todayWeather = tomorrowWeather_1=tomorrowWeather_2= "정보가 없습니다";
                        todayWeather_max = todayWeather_min= tomorrowWeather_1_max=tomorrowWeather_1_min=
                                tomorrowWeather_2_max= tomorrowWeather_2_min= "--";
                        DateData dateData = new DateData();
                        for(int i=0; i<allWeather_t.size(); i++){
                            if(allWeather_t.get(i).getWeather_Day().equals(dateData.getDate_future_for_weather(0)
                            ))
                            {
                                todayWeather = allWeather_t.get(i).getWeather_Name();
                                todayWeather_max = allWeather_t.get(i).getTemp_Max();
                                todayWeather_min =allWeather_t.get(i).getTemp_Min();
                                todayWeather_index = i;
                                break;
                            }
                        }
                        for(int i= todayWeather_index+1; i<allWeather_t.size(); i++){
                            if(allWeather_t.get(i).getWeather_Day().equals(dateData.getDate_future_for_weather(1)
                            ))
                            {
                                tomorrowWeather_1 = allWeather_t.get(i).getWeather_Name();
                                tomorrowWeather_1_max = allWeather_t.get(i).getTemp_Max();
                                tomorrowWeather_1_min = allWeather_t.get(i).getTemp_Min();
                                tomorrowWeather_1_index = i;
                                break;
                            }
                        }
                        for(int i= tomorrowWeather_1_index+1; i<allWeather_t.size(); i++){
                            if(allWeather_t.get(i).getWeather_Day().equals(dateData.getDate_future_for_weather(2)
                            ))
                            {
                                tomorrowWeather_2 = allWeather_t.get(i).getWeather_Name();
                                tomorrowWeather_2_max = allWeather_t.get(i).getTemp_Max();
                                tomorrowWeather_2_min = allWeather_t.get(i).getTemp_Min();
                                tomorrowWeather_2_index = i;

                                break;
                            }
                        }
                        //어제, 오늘, 내일의 첫번째 날씨, 각 최고 최저기온 프리퍼런스에 저장하기
                        PreferenceManager.setString(context, "todayWeather",todayWeather);
                        PreferenceManager.setString(context, "todayWeather_max",todayWeather_max);
                        PreferenceManager.setString(context, "todayWeather_min",todayWeather_min);
                        PreferenceManager.setString(context, "tomorrowWeather_1",tomorrowWeather_1);
                        PreferenceManager.setString(context, "tomorrowWeather_1_max",tomorrowWeather_1_max);
                        PreferenceManager.setString(context, "tomorrowWeather_1_min",tomorrowWeather_1_min);
                        PreferenceManager.setString(context, "tomorrowWeather_2",tomorrowWeather_2);
                        PreferenceManager.setString(context, "tomorrowWeather_2_max",tomorrowWeather_2_max);
                        PreferenceManager.setString(context, "tomorrowWeather_2_min",tomorrowWeather_2_min);


                        break;
                    }
                default:
                    break;
            }
        }
    };
}
