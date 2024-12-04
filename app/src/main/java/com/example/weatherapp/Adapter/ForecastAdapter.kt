package com.example.weatherapp.Adapter

import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ForecastViewholderBinding
import com.example.weatherapp.model.ForcastResponseApi
import java.text.SimpleDateFormat
import java.util.Calendar


class ForecastAdapter : RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private lateinit var binding: ForecastViewholderBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ForecastViewholderBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = ForecastViewholderBinding.bind(holder.itemView)
        val data =  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(differ.currentList[position].dtTxt.toString())
        val calendar = Calendar.getInstance()
        calendar.time = data
        val dayOfweekName = when(calendar.get(Calendar.DAY_OF_WEEK)){
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesday"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            7 -> "Saturday"
            else -> "-"
        }
        binding.nameDayTxt.text = dayOfweekName

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val amPm = if (hour < 12) "AM" else "PM"
        val hour12 = calendar.get(Calendar.HOUR)

        binding.hourTxt.text = hour12.toString() + amPm
        binding.tempTxt1.text = differ.currentList[position].main?.temp?.let {Math.round(it)}.toString() + "°"

        val icon = when(differ.currentList[position].weather?.get(0)?.icon.toString()){
            "01d", "01n" -> "sunny"
            "02d", "02n" -> "cloudy_sunny"
            "03d", "03n" -> "cloudy_sunny"
            "04d", "04n" -> "cloudy"
            "09d", "09n" -> "rainy"
            "10d", "10n" -> "rainy"
            "11d", "11n" -> "storm"
            "13d", "13n" -> "snowy"
            "50d", "50n" -> "windy"
            else -> "sunny"
        }

        val drawableResourceId: Int = binding.root.resources.getIdentifier(icon, "drawable", binding.root.context.packageName)

        Glide.with(binding.root)
            .load(drawableResourceId)
            .into(binding.pic)
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<ForcastResponseApi.Item0>() {
        override fun areItemsTheSame(
            p0: ForcastResponseApi.Item0,
            p1: ForcastResponseApi.Item0
        ): Boolean {
            return p0==p1
        }

        override fun areContentsTheSame(
            p0: ForcastResponseApi.Item0,
            p1: ForcastResponseApi.Item0
        ): Boolean {
            return p0==p1
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}