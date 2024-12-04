package com.example.weatherapp.Repository

import com.example.weatherapp.BuildConfig
import com.example.weatherapp.Server.ApiServices

class CityRepository(val api: ApiServices) {
    val apiKey = BuildConfig.WEATHER_API_KEY
    fun getCities(q: String, limit: Int) = api.getCitiesList(q, limit, apiKey)
}