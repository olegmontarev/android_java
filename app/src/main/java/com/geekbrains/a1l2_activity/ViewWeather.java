package com.geekbrains.a1l2_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewWeather extends AppCompatActivity {

    private TextView weather;
    private TextView additional;
    private boolean wind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        boolean wind = intent.getBooleanExtra("wind", false);
        boolean humidity = intent.getBooleanExtra("humidity", false);
        boolean pressure = intent.getBooleanExtra("pressure", false);
        showWeather(city);
        showWind(wind);
        showHumidity(humidity);
        showPressure(pressure);
    }

    private void initViews() {
        weather = findViewById(R.id.textView);
        additional = findViewById(R.id.textView2);
    }

    private void showWeather(String city) {
        switch (city){
            case "Moscow":
                weather.setText("Погода в Москве 15 градусов");
                break;
            case "Berlin":
                weather.setText("Погода в Берлине 20 градусов");
                break;
            case "London":
                weather.setText("Погода в Лондоне 18 градусов");
                break;
                default:
                    weather.setText("Такого города нет в базе");
                    break;
        }


        if (city.equals("Moscow")) {
            weather.setText("Погода в Москве 15 градусов");
        } else if (city.equals("Berlin")) {
            weather.setText("Погода в Берлине 20 градусов");
        } else if (city.equals("London")) {
            weather.setText("Погода в Лондоне 18 градусов");
        } else weather.setText("Такого города нет в базе");

    }

    private void showWind(boolean wind){
        if (wind) {
            additional.setText("Скорость ветра 17 метров в секунду");
        }
    }

    private void showHumidity(boolean humidity){
        if (humidity) {
            additional.setText("Влажность 20 процентов");
        }
    }

    private void showPressure(boolean pressure){
        if (pressure) {
            additional.setText("Давление 10 процентов");
        }
    }
}
