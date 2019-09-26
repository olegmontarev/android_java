package com.geekbrains.a1l2_activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
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
//        textView.setText(city);
    }

    private void initViews() {
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    private void showWeather(String city) {
        if (city.equals("Moscow")) {
            textView.setText("Погода в Москве 15 градусов");
        } else if (city.equals("Berlin")) {
            textView.setText("Погода в Берлине 20 градусов");
        } else if (city.equals("London")) {
            textView.setText("Погода в Лондоне 18 градусов");
        } else textView.setText("Такого города нет в базе");

    }

    private void showWind(boolean wind){
        if (wind = true) {
            textView2.setText("Скорость ветра 17 метров в секунду");
        }
    }

    private void showHumidity(boolean humidity){
        if (humidity = true) {
            textView2.setText("Влажность 20 процентов");
        }
    }

    private void showPressure(boolean pressure){
        if (pressure = true) {
            textView2.setText("Давление 10 процентов");
        }
    }
}
