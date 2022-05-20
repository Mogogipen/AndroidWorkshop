package com.example.fragmentpractice.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentpractice.data.City
import com.example.fragmentpractice.databinding.FragmentDetailsBinding
import com.example.fragmentpractice.ui.listeners.InfoClickListener
import com.example.fragmentpractice.viewmodel.CityViewModel
import java.text.DecimalFormat

class DetailsFragment : Fragment() {

    private val cityViewModel by lazy { ViewModelProvider(requireActivity())[CityViewModel::class.java] }

    private lateinit var rootBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return rootBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindData()
        setupListeners()
        setupObserver()
    }

    private fun bindData() {
        cityViewModel.getSelectedCity()?.let {
            val areaString = "${DecimalFormat("###,###,##0.##").format(it.area)} Sq. Miles"
            rootBinding.tvCityName.text = it.name
            rootBinding.tvStateName.text = it.state
            rootBinding.tvAreaValue.text = areaString
            rootBinding.tvPopValue.text = DecimalFormat("###,###,##0").format(it.pop)
        }
    }

    private fun setupListeners() {
        rootBinding.ivInfo.setOnClickListener { infoClicked() }
    }

    private fun setupObserver() {
        cityViewModel.liveSelectedCity.observe(requireActivity()) {
            bindData()
        }
    }

    private fun infoClicked() {
        val listener = activity as? InfoClickListener
        listener?.infoClicked()
    }
}