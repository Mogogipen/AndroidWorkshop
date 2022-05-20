package com.example.fragmentpractice.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentpractice.R
import com.example.fragmentpractice.databinding.FragmentCityMapBinding
import com.example.fragmentpractice.ui.listeners.InfoClickListener
import com.example.fragmentpractice.viewmodel.CityViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class CityMapFragment() : Fragment() {

    private val cityViewModel by lazy { ViewModelProvider(requireActivity())[CityViewModel::class.java] }

    private lateinit var rootBinding: FragmentCityMapBinding
    private lateinit var mapFragment: SupportMapFragment

    private var lastMarker: Marker? = null

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        cityViewModel.getSelectedCity()?.let {
            lastMarker?.remove()
            lastMarker = googleMap.addMarker(MarkerOptions().position(it.location).title(it.name))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it.location, 6f))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootBinding = FragmentCityMapBinding.inflate(inflater, container, false)
        return rootBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(callback)
        bindData()
        setupListeners()
        setupObserver()
    }

    private fun bindData() {
        rootBinding.tvCityNameMap.text = cityViewModel.getSelectedCity()?.name
    }

    private fun setupListeners() {
        rootBinding.ivInfo.setOnClickListener { infoClicked() }
    }

    private fun setupObserver() {
        cityViewModel.liveSelectedCity.observe(requireActivity()) {
            bindData()
            mapFragment.getMapAsync(callback)
        }
    }

    private fun infoClicked() {
        val listener = activity as InfoClickListener?
        listener?.infoClicked()
    }

}