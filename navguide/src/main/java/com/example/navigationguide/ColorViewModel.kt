package com.example.navigationguide

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorViewModel: ViewModel() {

    val liveColor = MutableLiveData<Int>()

    fun setColor(color: Int) {
        liveColor.value = color
    }

}