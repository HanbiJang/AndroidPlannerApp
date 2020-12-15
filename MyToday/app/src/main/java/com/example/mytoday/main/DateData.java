package com.example.mytoday.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateData {
    SimpleDateFormat format1;
    TimeZone time_kr;

    //Weather_main에서 날짜 비교용
    SimpleDateFormat format_for_weather;

    public String getDate_future(int daygap){
        format1 = new SimpleDateFormat("yyyy. MM/dd (E)", Locale.KOREA);
        //한국 시간
        time_kr = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(time_kr);
        cal.add(Calendar.DATE,daygap);

        return format1.format(cal.getTime());
    }

    public String getDate_future_for_weather(int daygap){
        format_for_weather = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        //한국 시간
        time_kr = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(time_kr);
        cal.add(Calendar.DATE,daygap);
        return format_for_weather.format(cal.getTime());
    }

    //문자로 미래날짜 구하기
    public String getDate_future_String(String string_date,int daygap){
        Calendar cal = String_to_cal(string_date);
        cal.setTimeZone(time_kr);
        format1 = new SimpleDateFormat("yyyy. MM/dd (E)", Locale.KOREA);
        cal.add(Calendar.DATE,daygap);
        return format1.format(cal.getTime());
    }

    //문자로 날짜 구하기
    public Calendar String_to_cal(String target_date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy. MM/dd (E)", Locale.KOREA);
        Date date = null;
        try {
            date = format.parse(target_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        time_kr = TimeZone.getTimeZone("Asia/Seoul");
        cal.setTimeZone(time_kr);
        cal.setTime(date);
        return cal;
    }

    //두 String 형 날짜 사이의 gap 을 구하기
    public long gap_between_two_date(String date1, String date2){
        long gap = 0L;

        time_kr = TimeZone.getTimeZone("Asia/Seoul");

        Calendar cal_date1 = String_to_cal(date1);
        Calendar cal_date2 = String_to_cal(date2);

        cal_date1.setTimeZone(time_kr);
        cal_date2.setTimeZone(time_kr);
        gap = (cal_date2.getTimeInMillis() - cal_date1.getTimeInMillis()) / (24*60*60*1000);

        return gap;
    }

}
