package com.example.fragmentpractice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentpractice.data.City

class CityViewModel: ViewModel() {

    val cities by lazy { City.getCities() }

    val liveSelectedCity: MutableLiveData<City?> = MutableLiveData(null)

    fun selectCity(city:City) {
        liveSelectedCity.value = city
    }

    fun getSelectedCity(): City? {
        return liveSelectedCity.value
    }

}