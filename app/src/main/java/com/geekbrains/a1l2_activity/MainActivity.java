package com.geekbrains.a1l2_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.geekbrains.a1l2_activity.rest.OpenWeatherRepo;
import com.geekbrains.a1l2_activity.rest.entities.WeatherRequestRestModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
      TextInputEditText textInputEditText;
      private Button toSecondActivityBtn;
      private CheckBox details;
      private static final String TAG = "Log";
      String city = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setBehaviourForTo2ndActBtn();
        Toast.makeText(getBaseContext(), "onCreate", Toast.LENGTH_SHORT).show();
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Узнать ветер, влажность и давление", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
            private void initViews() {
        toSecondActivityBtn = findViewById(R.id.toSecondActivityBtn);
        textInputEditText = findViewById(R.id.textInput);
        details = findViewById(R.id.checkBox);
    }

    private void setBehaviourForTo2ndActBtn() {
        toSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city = Objects.requireNonNull(textInputEditText.getText()).toString();
                Intent intent = new Intent(MainActivity.this, ViewWeather.class);
                intent.putExtra("city", city);
                intent.putExtra("details", details.isChecked());
                startActivity(intent);
                finish();
            }
        });
    }
}
