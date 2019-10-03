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
                weather.setText(R.string.moscowWeather);
                break;
            case "Berlin":
                weather.setText(R.string.berlinWeather);
                break;
            case "London":
                weather.setText(R.string.londonWeather);
                break;
                default:
                    weather.setText("Такого города нет в базе");
                    break;
        }

    }

    private void showWind(boolean wind){
        if (wind) {
            additional.setText(R.string.checkWind);
        }
    }

    private void showHumidity(boolean humidity){
        if (humidity) {
            additional.setText(R.string.checkHumidity);
        }
    }

    private void showPressure(boolean pressure){
        if (pressure) {
            additional.setText(R.string.checkPressure);
        }
    }
}
