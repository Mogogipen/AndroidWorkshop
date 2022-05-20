package com.example.fragmentpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentpractice.R
import com.example.fragmentpractice.data.City
import com.example.fragmentpractice.ui.listeners.InfoClickListener
import com.example.fragmentpractice.viewmodel.CityViewModel

class MainActivity : AppCompatActivity(), CityAdapter.CityClickListener, InfoClickListener {

    private val cityViewModel by lazy { ViewModelProvider(this)[CityViewModel::class.java] }

    private var viewingMap = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.list_view,
                ListFragment(),
                ListFragment::class.simpleName)
            .commit()
    }

    override fun cityClicked(city: City) {
        val firstSelection = (cityViewModel.getSelectedCity() == null)
        cityViewModel.selectCity(city)
        if (firstSelection) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.detail_view,
                    DetailsFragment(),
                    DetailsFragment::class.simpleName)
                .commit()
        }
    }

    override fun infoClicked() {
        if (viewingMap) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.detail_view,
                    DetailsFragment(),
                    DetailsFragment::class.simpleName)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.detail_view,
                    CityMapFragment(),
                    CityMapFragment::class.simpleName
                )
                .commit()
        }
        viewingMap = !viewingMap
    }

}