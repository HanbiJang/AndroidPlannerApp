package com.example.mytoday.main.weather;

public class Weather_to_class {
    String weather_Day;
    String weather_Name;
    String weather_Number;
//    String weather_Much;
//    String weather_Type;
//    String wind_Direction;
//    String wind_SortNumber;
//    String wind_SortCode;
//    String wind_Speed;
//    String wind_Name;
    String temp_Min;
    String temp_Max;
//    String humidity;
//    String clouds_Value;
//    String clouds_Sort;
//    String clouds_Per;
    String weather_Day_time;

    public Weather_to_class
            (
                    String weather_Name,
                    String weather_Number,
//                            String weather_Much,
//                            String weather_Type,
//                            String wind_Direction,
//                            String wind_SortNumber,
//                            String wind_SortCode,
//                            String wind_Speed,
//                            String wind_Name,

                    String temp_Min,
                    String temp_Max,
//                            String humidity,
//                            String clouds_Value,
//                            String clouds_Sort,
//                            String clouds_Per,
                    String weather_Day


            )
    {
        this.weather_Name = weather_Name;
        this.weather_Number = weather_Number;
//        this.weather_Much = weather_Much;
//        this.weather_Type = weather_Type;
//        this.wind_Direction = wind_Direction;
//        this.wind_SortNumber = wind_SortNumber;
//        this.wind_SortCode = wind_SortCode;
//        this.wind_Speed = wind_Speed;

//        if(wind_Name.equals("")) this.wind_Name = "No Info";
//        else this.wind_Name = wind_Name;

        this.temp_Min = temp_Min;
        this.temp_Max = temp_Max;
//        this.humidity = humidity;
//        this.clouds_Value = clouds_Value;
//        this.clouds_Sort = clouds_Sort;
//        this.clouds_Per = clouds_Per;

        //시간을 자른 문자열 weather_Day_1을 값으로 넣음
        int idx = weather_Day.indexOf("T");
        String weather_Day_day = weather_Day.substring(0,idx);
        String weather_Day_time_1 = weather_Day.substring(idx+1);
        this.weather_Day = weather_Day_day;
        this.weather_Day_time=weather_Day_time_1;
    }

    public String getWeather_Day_time() {
        return weather_Day_time;
    }

    public String getWeather_Name() {
        return weather_Name;
    }

    public void setWeather_Name(String weather_Name) { this.weather_Name = weather_Name; }

//    public String getWind_Speed() {
//        return wind_Speed;
//    }

//    public String getWind_Name() {
//        return wind_Name;
//    }

    public String getTemp_Min() {
        return temp_Min;
    }

    public String getTemp_Max() {
        return temp_Max;
    }

//    public String getHumidity() {
//        return humidity;
//    }
//
//    public String getClouds_Value() {
//        return clouds_Value;
//    }
//
//    public String getClouds_Sort() {
//        return clouds_Sort;
//    }
//
//    public String getClouds_Per() {
//        return clouds_Per;
//    }

    public String getWeather_Day() { return weather_Day; }


//    public void setWind_Name(String wind_Name) { this.wind_Name = wind_Name; }
//
//    public void setClouds_Sort(String clouds_Sort) {this.clouds_Sort = clouds_Sort;}

}
