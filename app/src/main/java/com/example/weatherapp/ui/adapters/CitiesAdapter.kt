package com.example.weatherapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CitiesItemBinding

class CitiesAdapter(
    private val itemClick: ((String) -> Unit)? = null
) : RecyclerView.Adapter<CitiesAdapter.CitiesHolder>() {

    var citiesList: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitiesHolder {
        val binding = CitiesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CitiesHolder(binding)
    }

    override fun getItemCount(): Int {
        return citiesList.size
    }

    override fun onBindViewHolder(holder: CitiesHolder, position: Int) {
        citiesList[position].let { holder.bind(it, itemClick ?: { _ -> print("clicked") }) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(list: List<String>) {
        this.citiesList = list
        notifyDataSetChanged()
    }

   inner class CitiesHolder(private val binding: CitiesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String, itemClick: ((String) -> Unit)) {
            itemView.setOnClickListener {
                itemClick(item)
            }

            binding.txtCity.text = item
        }
    }
}