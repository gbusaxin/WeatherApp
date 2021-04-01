package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private LocationListener locationListener;
    private double lat, lon;

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

    @OnClick(R.id.fab_Main)
    void onClickFabMain(View view) {
        startActivity(new Intent(this,WeatherActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = location -> {
            lat = location.getLatitude();
            lon = location.getLongitude();

            Log.e("lat - ", String.valueOf(lat));
            Log.e("lon - ", String.valueOf(lon));

            getWeatherData(lat, lon);
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
                    , 500
                    , 50
                    , locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && permissions.length > 0 && ContextCompat.checkSelfPermission(this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER
                    , 500
                    , 50
                    , locationListener);
        }
    }

    public void getWeatherData(double lat, double lon) {
        WeatherAPI weatherAPI = RetrofitWeather.getClient().create(WeatherAPI.class);
        Call<OpenWeatherMap> call = weatherAPI.getWeatherByLocation(lat, lon);

        Log.e("hyperText",call.toString());

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {
                cityName.setText(response.body().getName() + " , " + response.body().getSys().getCountry());
                temperature.setText(response.body().getMain().getTemp() + " °C");
                Log.e("Value - ", temperature.getText().toString());
                condition.setText(response.body().getWeather().get(0).getDescription());
                humidity.setText(" : " + response.body().getMain().getHumidity() + "%");
                maxTemp.setText(" : " + response.body().getMain().getTempMax() + "°C");
                minTemp.setText(" : " + response.body().getMain().getTempMin() + "°C");
                pressure.setText(" : " + response.body().getMain().getPressure());
                wind.setText(" : " + response.body().getWind().getSpeed());

                String iconCode = response.body().getWeather().get(0).getIcon();
                Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                        .placeholder(R.drawable.ic_launcher_background).into(imageView);
            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

            }
        });
    }
}

