package com.example.weatherapp.Repository

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.Server.ApiServices

class WeatherRepository(val api: ApiServices) {
    val apiKey = BuildConfig.WEATHER_API_KEY


    fun getCurrentWeather(lat: Double, lon: Double, unit: String) = api.getCurrentWeather(lat, lon, unit, apiKey)

    fun getForecastWeather(lat: Double, lon: Double, unit: String) = api.getForecastWeather(lat, lon, unit, apiKey)

}