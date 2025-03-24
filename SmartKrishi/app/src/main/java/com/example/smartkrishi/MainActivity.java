package com.example.smartkrishi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.smartkrishi.utils.MenuHandler;
import com.example.smartkrishi.utils.WeatherService;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends Activity {
    private TextView weatherInfo, weatherLocation;
    private WeatherService weatherService;
    private MenuHandler menuHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        weatherInfo = findViewById(R.id.weatherInfo);
        weatherLocation = findViewById(R.id.weather_location);

        // Initialize WeatherService and MenuHandler
        weatherService = new WeatherService(this);
        menuHandler = new MenuHandler(this);

        // Fetch weather data
        weatherService.fetchWeather("Kathmandu", weatherInfo, weatherLocation);


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);


        bottomNav.setOnItemSelectedListener(item -> menuHandler.onNavigationItemSelected(item));
    }
}
