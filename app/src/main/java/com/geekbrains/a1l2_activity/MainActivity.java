package com.geekbrains.a1l2_activity;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Objects;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener
{
        private Toolbar toolbar;
        private DrawerLayout drawer;
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
        initSideMenu();
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
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initSideMenu() {
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main, menu);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
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
