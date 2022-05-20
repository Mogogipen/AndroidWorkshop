package com.example.fragmentpractice.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentpractice.data.City
import com.example.fragmentpractice.databinding.CityItemBinding

class CityAdapter(
    private val cities: List<City>,
    private val cityClickListener: CityClickListener
): RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    interface CityClickListener {
        fun cityClicked(city: City)
    }

    class ViewHolder(private val binding: CityItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindData(city: City, cityClickListener: CityClickListener) {
            binding.tvStateName.text = city.state
            binding.tvCityName.text = city.name
            binding.root.setOnClickListener{
                cityClickListener.cityClicked(city)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CityItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(cities[position], cityClickListener)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

}