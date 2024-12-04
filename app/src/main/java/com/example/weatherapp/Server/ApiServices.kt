package com.example.weatherapp.Server

import com.example.weatherapp.model.CityResponseApi
import retrofit2.Call
import com.example.weatherapp.model.CurrentResponseApi
import com.example.weatherapp.model.ForcastResponseApi
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") Apikey: String
    ) : Call<CurrentResponseApi>

    @GET("data/2.5/forecast")
    fun getForecastWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("appid") Apikey: String
    ) : Call<ForcastResponseApi>

    @GET("geo/1.0/direct")
    fun getCitiesList(
        @Query("q") cityName: String,
        @Query("limit") limit: Int,
        @Query("appid") Apikey: String
    ): Call<CityResponseApi>

}