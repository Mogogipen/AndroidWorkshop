package com.example.navigationguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.toUpperCase
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import java.util.*

class ColorDetailsFragment : Fragment() {

    private val colorViewModel: ColorViewModel by lazy {
        ViewModelProvider(requireActivity())[ColorViewModel::class.java]
    }

    private lateinit var navController: NavController

    private lateinit var color: TextView
    private lateinit var body: TextView
    private lateinit var swapButton: ImageButton

    private var colorString = ""
    private var bodyString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_color_details, container, false)
        color = root.findViewById(R.id.tv_description_color)
        body = root.findViewById(R.id.tv_description_body)
        swapButton = root.findViewById(R.id.button_details_swap)
        handleColorChange(colorViewModel.liveColor.value ?: 0x0)
        bindData()
        setupListeners()
        setupObserver()
        return root
    }

    private fun bindData() {
        color.text = colorString
        body.text = bodyString
    }

    private fun setupListeners() {
        swapButton.setOnClickListener { swapPushed() }
    }

    private fun setupObserver() {
        colorViewModel.liveColor.observe(viewLifecycleOwner) {
            handleColorChange(it)
        }
    }

    private fun swapPushed() {
        val action = ColorDetailsFragmentDirections
            .actionColorDetailsFragmentToColorCommentsFragment()
        navController.navigate(action)
    }

    private fun handleColorChange(newColor: Int) {
        colorString = UtilColor.colorIntToString(newColor)
        bindData()
    }

}