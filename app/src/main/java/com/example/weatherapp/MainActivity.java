package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

   @BindView(R.id.textViewCityMain)
   TextView cityName;
   @BindView(R.id.imageViewMain)
   ImageView imageView;
   @BindView(R.id.textViewTemperatureMain)
   TextView temperature;
   @BindView(R.id.textViewWeatherConditionMain)
   TextView condition;
   @BindView(R.id.textViewHumidityStateMain)
   TextView humidity;
   @BindView(R.id.textViewMaxTempStateMain)
   TextView maxTemp;
   @BindView(R.id.textViewMinTempStateMain)
   TextView minTemp;
   @BindView(R.id.textViewPressureStateMain)
   TextView pressure;
   @BindView(R.id.textViewWindStateMain)
   TextView wind;

   @OnClick(R.id.fab_Main) void onClickFabMain(View view){

   }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}