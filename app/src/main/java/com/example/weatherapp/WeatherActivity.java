package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends AppCompatActivity {

    @BindView(R.id.editTextEnterCityNameWeather)
    EditText enterCityName;
    @BindView(R.id.imageViewWeather)
    ImageView imageView;
    @BindView(R.id.textViewCityWeather)
    TextView city;
    @BindView(R.id.textViewTemperatureWeather)
    TextView temperature;
    @BindView(R.id.textViewWeatherConditionWeather)
    TextView condition;
    @BindView(R.id.textViewHumidityStateWeather)
    TextView humidity;
    @BindView(R.id.textViewMaxTempStateWeather)
    TextView maxTemp;
    @BindView(R.id.textViewMinTempStateMain)
    TextView minTemp;
    @BindView(R.id.textViewPressureStateWeather)
    TextView pressure;
    @BindView(R.id.textViewWindStateWeather)
    TextView wind;

    @OnClick(R.id.buttonSearchWeather) void onClickSearchWeather(View view){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
    }
}