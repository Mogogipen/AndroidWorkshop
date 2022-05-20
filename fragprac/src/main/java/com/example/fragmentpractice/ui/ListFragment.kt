package com.example.fragmentpractice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentpractice.databinding.FragmentListBinding
import com.example.fragmentpractice.data.City
import com.example.fragmentpractice.viewmodel.CityViewModel

class ListFragment : Fragment() {

    private val cityViewModel by lazy { ViewModelProvider(requireActivity())[CityViewModel::class.java] }

    private lateinit var rootBinding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootBinding = FragmentListBinding.inflate(inflater, container, false)
        bindAdapter()
        return rootBinding.root
    }

    private fun bindAdapter() {
        rootBinding.rvCityList.layoutManager = LinearLayoutManager(requireContext())
        val activityAsCityListener = activity as? CityAdapter.CityClickListener
        activityAsCityListener?.let {
            rootBinding.rvCityList.adapter = CityAdapter(
                cityViewModel.cities,
                activityAsCityListener)
        }
    }
}