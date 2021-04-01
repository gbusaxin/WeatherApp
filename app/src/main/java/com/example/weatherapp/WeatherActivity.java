package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @BindView(R.id.textViewMinTempStateWeather)
    TextView minTemp;
    @BindView(R.id.textViewPressureStateWeather)
    TextView pressure;
    @BindView(R.id.textViewWindStateWeather)
    TextView wind;

    @OnClick(R.id.buttonSearchWeather)
    void onClickSearchWeather(View view) {
        getWeatherData(enterCityName.getText().toString());
        enterCityName.setText("");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
    }

    public void getWeatherData(String name) {
        WeatherAPI weatherAPI = RetrofitWeather.getClient().create(WeatherAPI.class);
        Call<OpenWeatherMap> call = weatherAPI.getWeatherByCityName(name);

        call.enqueue(new Callback<OpenWeatherMap>() {
            @Override
            public void onResponse(Call<OpenWeatherMap> call, Response<OpenWeatherMap> response) {

                if (response.isSuccessful()) {
                    city.setText(response.body().getName() + " , " + response.body().getSys().getCountry());
                    temperature.setText(response.body().getMain().getTemp() + " °C");
                    condition.setText(response.body().getWeather().get(0).getDescription());
                    humidity.setText(" : " + response.body().getMain().getHumidity() + "%");
                    maxTemp.setText(" : " + response.body().getMain().getTempMax() + "°C");
                    minTemp.setText(" : " + response.body().getMain().getTempMin() + "°C");
                    pressure.setText(" : " + response.body().getMain().getPressure());
                    wind.setText(" : " + response.body().getWind().getSpeed());

                    String iconCode = response.body().getWeather().get(0).getIcon();
                    Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                            .placeholder(R.drawable.ic_launcher_background).into(imageView);
                    Toast.makeText(WeatherActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(WeatherActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OpenWeatherMap> call, Throwable t) {

            }
        });
    }
}