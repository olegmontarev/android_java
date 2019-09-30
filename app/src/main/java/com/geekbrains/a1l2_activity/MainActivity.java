package com.geekbrains.a1l2_activity;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
      private EditText editText;
      private Button toSecondActivityBtn;
      private CheckBox wind;
      private CheckBox humidity;
      private CheckBox pressure;
      private static final String TAG = "Log";
      String[] data = {"Moscow", "Berlin", "London"};
      String city = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setBehaviourForTo2ndActBtn();
        Toast.makeText(getBaseContext(), "onCreate", Toast.LENGTH_SHORT).show();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Выберите город");
        spinner.setSelection(2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                city = spinner.getSelectedItem().toString();
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });}

            private void initViews() {
        editText = findViewById(R.id.editText2);
        toSecondActivityBtn = findViewById(R.id.toSecondActivityBtn);
        wind = findViewById(R.id.checkBox);
        humidity = findViewById(R.id.checkBox2);
        pressure = findViewById(R.id.checkBox3);
    }

    private void setBehaviourForTo2ndActBtn() {
        toSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewWeather.class);
                intent.putExtra("city", city);
                intent.putExtra("wind", wind.isChecked());
                intent.putExtra("humidity", humidity.isChecked());
                intent.putExtra("pressure", pressure.isChecked());
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getBaseContext(), "onStart", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getBaseContext(), "onResume", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getBaseContext(), "onPause", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getBaseContext(), "onStop", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getBaseContext(), "onDestroy", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getBaseContext(), "onRestart", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "OnRestart");
    }

}
