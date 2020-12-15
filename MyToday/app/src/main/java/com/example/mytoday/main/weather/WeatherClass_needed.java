package com.example.mytoday.main.weather;

public class WeatherClass_needed {
    //날씨의 날짜, 날씨, 최고기온, 최저기온이 나타나있음
    String weatherDate;
    String weather;
    String max;
    String min;

    public void setMax(String max) {
        this.max = max;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public void setWeatherDate(String weatherDate) {
        this.weatherDate = weatherDate;
    }

    public String getMax() {
        return max;
    }

    public String getMin() {
        return min;
    }

    public String getWeather() {
        return weather;
    }

    public String getWeatherDate() {
        return weatherDate;
    }
}
