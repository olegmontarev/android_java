package com.geekbrains.a1l2_activity;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
      private EditText editText;
      private Button toSecondActivityBtn;
      private CheckBox checkBox;
      private CheckBox checkBox2;
      private CheckBox checkBox3;
      private static final String TAG = "Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setBehaviourForTo2ndActBtn();
        Toast.makeText(getBaseContext(), "onCreate", Toast.LENGTH_SHORT).show();
    }

    private void initViews() {
        editText = findViewById(R.id.editText2);
        toSecondActivityBtn = findViewById(R.id.toSecondActivityBtn);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
    }

    private void setBehaviourForTo2ndActBtn() {
        toSecondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                String text = editText.getText().toString();
                intent.putExtra("city", text);
                intent.putExtra("wind", checkBox.isChecked());
                intent.putExtra("humidity", checkBox2.isChecked());
                intent.putExtra("pressure", checkBox3.isChecked());
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
