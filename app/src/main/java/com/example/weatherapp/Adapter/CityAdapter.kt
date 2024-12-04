package com.example.weatherapp.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.Activity.MainActivity
import com.example.weatherapp.databinding.CityViewholderBinding
import com.example.weatherapp.databinding.ForecastViewholderBinding
import com.example.weatherapp.model.CityResponseApi
import com.example.weatherapp.model.ForcastResponseApi
import java.text.SimpleDateFormat
import java.util.Calendar


class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    private lateinit var binding: CityViewholderBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CityViewholderBinding.inflate(inflater, parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = CityViewholderBinding.bind(holder.itemView)
        binding.cityTxt1.text = differ.currentList[position].name
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, MainActivity::class.java)
            intent.putExtra("lat", differ.currentList[position].lat)
            intent.putExtra("lon", differ.currentList[position].lon)
            intent.putExtra("name", differ.currentList[position].name)
            binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<CityResponseApi.CityResponseApiItem>() {
        override fun areItemsTheSame(
            p0: CityResponseApi.CityResponseApiItem,
            p1: CityResponseApi.CityResponseApiItem
        ): Boolean {
            return p0==p1
        }

        override fun areContentsTheSame(
            p0: CityResponseApi.CityResponseApiItem,
            p1: CityResponseApi.CityResponseApiItem
        ): Boolean {
            return p0==p1
        }

    }

    val differ = AsyncListDiffer(this, differCallback)
}