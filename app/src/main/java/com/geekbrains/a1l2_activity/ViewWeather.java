package com.geekbrains.a1l2_activity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.geekbrains.a1l2_activity.rest.OpenWeatherRepo;
import com.geekbrains.a1l2_activity.rest.entities.WeatherRequestRestModel;

import org.json.JSONException;
import org.json.JSONObject;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewWeather extends AppCompatActivity {

    private final Handler handler = new Handler();
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    private TextView cityTextView;
    private TextView updatedTextView;
    private TextView detailsTextView;
    private TextView currentTemperatureTextView;
    private TextView weatherIconTextView;
    private boolean detail;

    private TextView textTemp;
    private TextView textHumidity;
    private SensorManager sensorManager;
    private Sensor sensorTemp;
    private Sensor sensorHumidity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initViews();
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        detail = intent.getBooleanExtra("details", false);
        updateWeatherData(city);
        getSensors();
    }

    private void initViews() {
        cityTextView = findViewById(R.id.city_field);
        updatedTextView = findViewById(R.id.updated_field);
        detailsTextView = findViewById(R.id.details_field);
        currentTemperatureTextView = findViewById(R.id.current_temperature_field);
        weatherIconTextView = findViewById(R.id.weather_icon);
        textTemp = findViewById(R.id.textTemp);
        textHumidity = findViewById(R.id.textHumidity);
    }

    private void getSensors() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listenerTemp, sensorTemp,
                SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(listenerHumidity, sensorHumidity,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listenerTemp, sensorTemp);
        sensorManager.unregisterListener(listenerHumidity, sensorHumidity);
    }

    private void showTempSensors(SensorEvent event){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Temp Sensor value = ").append(event.values[0])
                .append("\n").append("=======================================").append("\n");
        textTemp.setText(stringBuilder);
    }

    SensorEventListener listenerTemp = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        @Override
        public void onSensorChanged(SensorEvent event) {
            showTempSensors(event);
        }
    };

    private void showHumiditySensors(SensorEvent event){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Humidity Sensor value = ").append(event.values[0])
                .append("\n").append("=======================================").append("\n");
        textHumidity.setText(stringBuilder);
    }

    SensorEventListener listenerHumidity = new SensorEventListener() {

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}

        @Override
        public void onSensorChanged(SensorEvent event) {
            showHumiditySensors(event);
        }
    };

    private void updateWeatherData(final String city) {
        /*final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    WeatherRequestRestModel model = OpenWeatherRepo.getSingleton().getAPI().loadWeather(city + ",ru",
                            "762ee61f52313fbd10a4eb54ae4d4de2", "metric").execute().body();
                    //берете данные из model, отправляете в другой запрос
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Обновляем вьюхи
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/
        OpenWeatherRepo.getSingleton().getAPI().loadWeather(city + ",ru",
                "762ee61f52313fbd10a4eb54ae4d4de2", "metric")
                .enqueue(new Callback<WeatherRequestRestModel>() {
                    @Override
                    public void onResponse(@NonNull Call<WeatherRequestRestModel> call,
                                           @NonNull Response<WeatherRequestRestModel> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            renderWeather(response.body());
                        } else {
                            //Похоже, код у нас не в диапазоне (200..300] и случилась ошибка
                            //обрабатываем ее
                            if(response.code() == 500) {
                                //ой, случился Internal Server Error. Решаем проблему
                            } else if(response.code() == 401) {
                                //...
                            }// и так далее
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherRequestRestModel> call, Throwable t) {
                        Toast.makeText(getBaseContext(), getString(R.string.network_error),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void renderWeather(WeatherRequestRestModel model) {
        setPlaceName(model.name, model.sys.country);
        setDetails(model.weather[0].description, model.main.humidity, model.main.pressure);
        setCurrentTemp(model.main.temp);
        setUpdatedText(model.dt);
        setWeatherIcon(model.weather[0].id,
                model.sys.sunrise * 1000,
                model.sys.sunset * 1000);
    }

    private void setPlaceName(String name, String country) {
        String cityText = name.toUpperCase() + ", " + country;
        cityTextView.setText(cityText);
    }

    private void setDetails(String description, float humidity, float pressure)  {
        String detailsText = description.toUpperCase() + "\n"
                + "Humidity: " + humidity + "%" + "\n"
                + "Pressure: " + pressure + "hPa";
        detailsTextView.setText(detailsText);
    }

    private void setCurrentTemp(float temp) {
        String currentTextText = String.format(Locale.getDefault(), "%.2f", temp) + "\u2103";
        currentTemperatureTextView.setText(currentTextText);
    }

    private void setUpdatedText(long dt) {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String updateOn = dateFormat.format(new Date(dt * 1000));
        String updatedText = "Last update: " + updateOn;
        updatedTextView.setText(updatedText);
    }

    private void setWeatherIcon(int actualId, long sunrise, long sunset) {
        int id = actualId / 100;
        String icon = "";

        if(actualId == 800) {
            long currentTime = new Date().getTime();
            if(currentTime >= sunrise && currentTime < sunset) {
                icon = "\u2600";
                //icon = getString(R.string.weather_sunny);
            } else {
                icon = getString(R.string.weather_clear_night);
            }
        } else {
            switch (id) {
                case 2: {
                    icon = getString(R.string.weather_thunder);
                    break;
                }
                case 3: {
                    icon = getString(R.string.weather_drizzle);
                    break;
                }
                case 5: {
                    icon = getString(R.string.weather_rainy);
                    break;
                }
                case 6: {
                    icon = getString(R.string.weather_snowy);
                    break;
                }
                case 7: {
                    icon = getString(R.string.weather_foggy);
                    break;
                }
                case 8: {
                    icon = "\u2601";
                    // icon = getString(R.string.weather_cloudy);
                    break;
                }
            }
        }
        weatherIconTextView.setText(icon);
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
