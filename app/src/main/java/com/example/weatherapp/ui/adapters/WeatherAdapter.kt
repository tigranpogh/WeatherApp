package com.example.weatherapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.DailyWeatherResponse
import com.example.weatherapp.databinding.ForecastItemBinding

class WeatherAdapter(
) : RecyclerView.Adapter<WeatherAdapter.WeatherHolder>() {

    var daysList = DailyWeatherResponse().days
    var tempList = DailyWeatherResponse().temp


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherHolder {
        val binding = ForecastItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherHolder, position: Int) {
        holder.bind(daysList!![position], tempList!![position])
    }

    override fun getItemCount(): Int {
        return daysList!!.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(daysList: List<String>, tempList: List<String>) {
        this.daysList = daysList
        this.tempList = tempList
        notifyDataSetChanged()
    }

    inner class WeatherHolder(private val binding: ForecastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(day: String, temp: String) {
            binding.txtDay.text = day
            binding.txtTemp.text = temp
        }
    }
}