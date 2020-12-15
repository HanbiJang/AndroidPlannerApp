package com.example.mytoday.main.weather;

import com.example.mytoday.R;

import java.util.ArrayList;

public class IconMatch {

        public ArrayList<com.example.mytoday.main.weather.IconMatch.WeatherCondition> weathersList_Hangeul;

        public IconMatch() {
            //http://openweathermap.org/weather-conditions
            weathersList_Hangeul = new ArrayList<com.example.mytoday.main.weather.IconMatch.WeatherCondition>(); // 11
            //-------------ThunderStrom------------------//

            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("200","번개와 보슬비", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("201","번개와 비", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("202","번개와 집중 호우", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("210","천둥", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("211","천둥 번개", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("212","강한 천둥 번개", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("221","매우 강한 천둥 번개", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("230","번개와 가벼운 이슬비", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("231","번개와 이슬비", R.drawable.thunder));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("232","번개와 집중 호우", R.drawable.thunder));

            //------------Drizzle-------------------//
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("300","약한 이슬비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("301","이슬비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("302","강한 이슬비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("310","약한 이슬비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("311","이슬비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("312","강한 이슬비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("313","소나기",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("314","강한 소나기",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("321","소나기",R.drawable.rain));
            //------------Rain----------------------//
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("500","가벼운 비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("501","비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("502","강한 비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("503","집중 호우",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("504","집중 호우",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("511","어는 비",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("520","가벼운 소나기",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("521","소나기",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("522","강한 소나기",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("531","매우 강한 소나기",R.drawable.rain));

            //------------Snow----------------------//
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("600","약한 눈",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("601","눈",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("602","거센 눈",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("611","진눈 깨비",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("612","급 진눈 깨비",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("615","약한 눈과 비",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("616","눈과 비",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("620","눈",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("621","소낙눈",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("622","강한 소낙눈",R.drawable.snow));
            //------------Atmosphere----------------------//
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("701","안개",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("711","연기",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("721","실안개",R.drawable.snow));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("731","황사 바람",R.drawable.cloudda));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("741","안개",R.drawable.cloudda));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("751","황사",R.drawable.cloudda));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("761","황사",R.drawable.cloudda));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("762","화산재",R.drawable.cloudda));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("771","돌풍",R.drawable.cloudda));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("781","태풍",R.drawable.cloudda));

            //------------clouds----------------------//
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("800","맑은 하늘",R.drawable.sunny));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("801","구름 조금",R.drawable.cloud));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("802","조각 구름",R.drawable.cloud));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("803","조각 구름",R.drawable.cloud));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("804","흐림",R.drawable.cloud));

            //-----------------Additional----------//
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("951","바람 없음",R.drawable.sunny));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("952","남실 바람",R.drawable.sunny));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("953","산들 바람",R.drawable.sunny));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("954","건들 바람",R.drawable.sunny));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("955","흔들 바람",R.drawable.sunny));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("956","된바람",R.drawable.wind));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("957","센바람",R.drawable.wind));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("958","강풍",R.drawable.wind));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("959","극심한 강풍",R.drawable.wind));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("960","폭풍우",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("961","폭풍",R.drawable.rain));
            weathersList_Hangeul.add(new com.example.mytoday.main.weather.IconMatch.WeatherCondition("962","허리케인",R.drawable.rain));
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
