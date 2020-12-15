package com.example.mytoday.main.weather;

import com.example.mytoday.R;

import java.util.ArrayList;

public class Weather_list {
    public ArrayList<WeatherCondition> mListSnow;
    public ArrayList<WeatherCondition> mListClearSky;
    public ArrayList<WeatherCondition> mListFew_Clouds;
    public ArrayList<WeatherCondition> mListScattered_Clouds;
    public ArrayList<WeatherCondition> mListBroken_Clouds;
    public ArrayList<WeatherCondition> mListShower_Rain;
    public ArrayList<WeatherCondition> mListRain;
    public ArrayList<WeatherCondition> mListThunderStorm;
    public ArrayList<WeatherCondition> mListMist;
    public ArrayList<WeatherCondition> mListWind;
    public ArrayList<WeatherCondition> mListWindDirection;

    public ArrayList<WeatherCondition> mListSnowToHangeul;
    public ArrayList<WeatherCondition> mListClearSkyToHangeul;
    public ArrayList<WeatherCondition> mListFew_CloudsToHangeul;
    public ArrayList<WeatherCondition> mListScattered_CloudsToHangeul;
    public ArrayList<WeatherCondition> mListBroken_CloudsToHangeul;
    public ArrayList<WeatherCondition> mListShower_RainToHangeul;
    public ArrayList<WeatherCondition> mListRainToHangeul;
    public ArrayList<WeatherCondition> mListThunderStormToHangeul;
    public ArrayList<WeatherCondition> mListMistToHangeul;
    public ArrayList<WeatherCondition> mListWindToHangeul;
    public ArrayList<WeatherCondition> mListWindDirectionToHangeul;

    public Weather_list() {
        //http://openweathermap.org/weather-conditions
        mListThunderStorm = new ArrayList<WeatherCondition>(); // 11
        mListMist = new ArrayList<WeatherCondition>();        // 50
        mListRain = new ArrayList<WeatherCondition>();       // 10
        mListShower_Rain = new ArrayList<WeatherCondition>(); // 09
        mListBroken_Clouds = new ArrayList<WeatherCondition>(); // 04
        mListScattered_Clouds = new ArrayList<WeatherCondition>(); // 03
        mListFew_Clouds = new ArrayList<WeatherCondition>(); // 02
        mListClearSky = new ArrayList<WeatherCondition>(); // 01
        mListSnow = new ArrayList<WeatherCondition>(); // 13
        mListWind = new ArrayList<WeatherCondition>();
        mListWindDirection = new ArrayList<WeatherCondition>();
        mListThunderStormToHangeul = new ArrayList<WeatherCondition>(); // 11
        mListMistToHangeul = new ArrayList<WeatherCondition>();        // 50
        mListRainToHangeul = new ArrayList<WeatherCondition>();       // 10
        mListShower_RainToHangeul = new ArrayList<WeatherCondition>(); // 09
        mListBroken_CloudsToHangeul = new ArrayList<WeatherCondition>(); // 04
        mListScattered_CloudsToHangeul = new ArrayList<WeatherCondition>(); // 03
        mListFew_CloudsToHangeul = new ArrayList<WeatherCondition>(); // 02
        mListClearSkyToHangeul = new ArrayList<WeatherCondition>(); // 01
        mListSnowToHangeul = new ArrayList<WeatherCondition>(); // 13
        mListWindToHangeul = new ArrayList<WeatherCondition>();
        mListWindDirectionToHangeul = new ArrayList<WeatherCondition>();
        //-------------ThunderStrom------------------//
        mListThunderStorm.add(new WeatherCondition("200","thunderstorm with light rain", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("201","thunderstorm with rain", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("202","thunderstorm with heavy rain", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("210","light thunderstorm", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("211","thunderstorm", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("212","heavy thunderstorm", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("221","ragged thunderstorm", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("230","thunderstorm with light drizzle", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("231","thunderstorm with drizzle", R.drawable.thunder));
        mListThunderStorm.add(new WeatherCondition("232","thunderstorm with heavy drizzle", R.drawable.thunder));

        mListThunderStormToHangeul.add(new WeatherCondition("200","번개와 보슬비", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("201","번개와 비", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("202","번개와 집중 호우", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("210","천둥", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("211","천둥 번개", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("212","강한 천둥 번개", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("221","매우 강한 천둥 번개", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("230","번개와 가벼운 이슬비", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("231","번개와 이슬비", R.drawable.thunder));
        mListThunderStormToHangeul.add(new WeatherCondition("232","번개와 집중 호우", R.drawable.thunder));

        //------------Drizzle-------------------//
        mListShower_Rain.add(new WeatherCondition("300","light intensity drizzle",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("301","drizzle",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("302","heavy intensity drizzle",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("310","light intensity drizzle rain",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("311","drizzle rain",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("312","heavy intensity drizzle rain",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("313","shower rain and drizzle",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("314","heavy shower rain and drizzle",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("321","shower drizzle",R.drawable.rain));

        mListShower_RainToHangeul.add(new WeatherCondition("300","약한 이슬비",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("301","이슬비",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("302","강한 이슬비",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("310","약한 이슬비",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("311","이슬비",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("312","강한 이슬비",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("313","소나기",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("314","강한 소나기",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("321","소나기",R.drawable.rain));
        //------------Rain----------------------//
        mListRain.add(new WeatherCondition("500","light rain",R.drawable.rain));
        mListRain.add(new WeatherCondition("501","moderate rain",R.drawable.rain));
        mListRain.add(new WeatherCondition("502","heavy intensity rain",R.drawable.rain));
        mListRain.add(new WeatherCondition("503","very heavy rain",R.drawable.rain));
        mListRain.add(new WeatherCondition("504","extreme rain",R.drawable.rain));
        mListSnow.add(new WeatherCondition("511","freezing rain",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("520","light intensity shower rain",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("521","shower rain",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("522","heavy intensity shower rain",R.drawable.rain));
        mListShower_Rain.add(new WeatherCondition("531","ragged shower rain",R.drawable.rain));

        mListRainToHangeul.add(new WeatherCondition("500","가벼운 비",R.drawable.rain));
        mListRainToHangeul.add(new WeatherCondition("501","비",R.drawable.rain));
        mListRainToHangeul.add(new WeatherCondition("502","강한 비",R.drawable.rain));
        mListRainToHangeul.add(new WeatherCondition("503","집중 호우",R.drawable.rain));
        mListRainToHangeul.add(new WeatherCondition("504","집중 호우",R.drawable.rain));
        mListSnowToHangeul.add(new WeatherCondition("511","어는 비",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("520","가벼운 소나기",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("521","소나기",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("522","강한 소나기",R.drawable.rain));
        mListShower_RainToHangeul.add(new WeatherCondition("531","매우 강한 소나기",R.drawable.rain));

        //------------Snow----------------------//
        mListSnow.add(new WeatherCondition("600","light snow",R.drawable.snow));
        mListSnow.add(new WeatherCondition("601","snow",R.drawable.snow));
        mListSnow.add(new WeatherCondition("602","heavy snow",R.drawable.snow));
        mListSnow.add(new WeatherCondition("611","sleet",R.drawable.snow));
        mListSnow.add(new WeatherCondition("612","shower sleet",R.drawable.snow));
        mListSnow.add(new WeatherCondition("615","light rain and snow",R.drawable.snow));
        mListSnow.add(new WeatherCondition("616","rain and snow",R.drawable.snow));
        mListSnow.add(new WeatherCondition("620","light shower snow",R.drawable.snow));
        mListSnow.add(new WeatherCondition("621","shower snow",R.drawable.snow));
        mListSnow.add(new WeatherCondition("622","heavy shower snow",R.drawable.snow));

        mListSnowToHangeul.add(new WeatherCondition("600","약한 눈",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("601","눈",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("602","거센 눈",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("611","진눈 깨비",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("612","급 진눈 깨비",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("615","약한 눈과 비",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("616","눈과 비",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("620","눈",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("621","소낙눈",R.drawable.snow));
        mListSnowToHangeul.add(new WeatherCondition("622","강한 소낙눈",R.drawable.snow));
        //------------Atmosphere----------------------//
        mListMist.add(new WeatherCondition("701","mist",R.drawable.cloud));
        mListMist.add(new WeatherCondition("711","smoke",R.drawable.cloud));
        mListMist.add(new WeatherCondition("721","haze",R.drawable.cloud));
        mListMist.add(new WeatherCondition("731","sand, dust whirls",R.drawable.cloudda));
        mListMist.add(new WeatherCondition("741","fog",R.drawable.cloudda));
        mListMist.add(new WeatherCondition("751","sand",R.drawable.cloudda));
        mListMist.add(new WeatherCondition("761","dust",R.drawable.cloudda));
        mListMist.add(new WeatherCondition("762","volcanic ash",R.drawable.cloudda));
        mListMist.add(new WeatherCondition("771","squalls",R.drawable.cloudda));
        mListMist.add(new WeatherCondition("781","tornado",R.drawable.cloudda));

        mListMistToHangeul.add(new WeatherCondition("701","안개",R.drawable.snow));
        mListMistToHangeul.add(new WeatherCondition("711","연기",R.drawable.snow));
        mListMistToHangeul.add(new WeatherCondition("721","실안개",R.drawable.snow));
        mListMistToHangeul.add(new WeatherCondition("731","황사 바람",R.drawable.cloudda));
        mListMistToHangeul.add(new WeatherCondition("741","안개",R.drawable.cloudda));
        mListMistToHangeul.add(new WeatherCondition("751","황사",R.drawable.cloudda));
        mListMistToHangeul.add(new WeatherCondition("761","황사",R.drawable.cloudda));
        mListMistToHangeul.add(new WeatherCondition("762","화산재",R.drawable.cloudda));
        mListMistToHangeul.add(new WeatherCondition("771","돌풍",R.drawable.cloudda));
        mListMistToHangeul.add(new WeatherCondition("781","태풍",R.drawable.cloudda));

        //------------clouds----------------------//
        mListClearSky.add(new WeatherCondition("800","clear sky",R.drawable.sunny));
        mListFew_Clouds.add(new WeatherCondition("801","few clouds",R.drawable.cloud));
        mListScattered_Clouds.add(new WeatherCondition("802","scattered clouds",R.drawable.cloud));
        mListBroken_Clouds.add(new WeatherCondition("803","broken clouds",R.drawable.cloud));
        mListBroken_Clouds.add(new WeatherCondition("804","overcast clouds",R.drawable.cloud));

        mListClearSkyToHangeul.add(new WeatherCondition("800","맑은 하늘",R.drawable.sunny));
        mListFew_CloudsToHangeul.add(new WeatherCondition("801","구름 조금",R.drawable.cloud));
        mListScattered_CloudsToHangeul.add(new WeatherCondition("802","조각 구름",R.drawable.cloud));
        mListBroken_CloudsToHangeul.add(new WeatherCondition("803","조각 구름",R.drawable.cloud));
        mListBroken_CloudsToHangeul.add(new WeatherCondition("804","흐림",R.drawable.cloud));

        //-----------------Additional----------//
        mListWind.add(new WeatherCondition("951","calm",R.drawable.sunny));
        mListWind.add(new WeatherCondition("952","light breeze",R.drawable.sunny));
        mListWind.add(new WeatherCondition("953","gentle breeze",R.drawable.sunny));
        mListWind.add(new WeatherCondition("954","moderate breeze",R.drawable.sunny));
        mListWind.add(new WeatherCondition("955","fresh breeze",R.drawable.sunny));
        mListWind.add(new WeatherCondition("956","strong breeze",R.drawable.wind));
        mListWind.add(new WeatherCondition("957","high wind, near gale",R.drawable.wind));
        mListWind.add(new WeatherCondition("958","gale",R.drawable.wind));
        mListWind.add(new WeatherCondition("959","severe gale",R.drawable.wind));
        mListWind.add(new WeatherCondition("960","storm",R.drawable.wind));
        mListWind.add(new WeatherCondition("961","violent storm",R.drawable.wind));
        mListWind.add(new WeatherCondition("962","hurricane",R.drawable.wind));

        mListWindToHangeul.add(new WeatherCondition("951","바람 없음",R.drawable.sunny));
        mListWindToHangeul.add(new WeatherCondition("952","남실 바람",R.drawable.sunny));
        mListWindToHangeul.add(new WeatherCondition("953","산들 바람",R.drawable.sunny));
        mListWindToHangeul.add(new WeatherCondition("954","건들 바람",R.drawable.sunny));
        mListWindToHangeul.add(new WeatherCondition("955","흔들 바람",R.drawable.sunny));
        mListWindToHangeul.add(new WeatherCondition("956","된바람",R.drawable.wind));
        mListWindToHangeul.add(new WeatherCondition("957","센바람",R.drawable.wind));
        mListWindToHangeul.add(new WeatherCondition("958","강풍",R.drawable.wind));
        mListWindToHangeul.add(new WeatherCondition("959","극심한 강풍",R.drawable.wind));
        mListWindToHangeul.add(new WeatherCondition("960","폭풍우",R.drawable.rain));
        mListWindToHangeul.add(new WeatherCondition("961","폭풍",R.drawable.rain));
        mListWindToHangeul.add(new WeatherCondition("962","허리케인",R.drawable.rain));
    }



    public class WeatherCondition {
        String id;
        String meaning;
        Integer icon;

        public WeatherCondition(String id, String meaning, Integer icon) {
            this.id = id;
            this.meaning = meaning;
            this.icon = icon;
        }
        public String getId() {
            return id;
        }
        public String getMeaning() {
            return meaning;
        }

        public Integer getIcon() {
            return icon;
        }
    }
}