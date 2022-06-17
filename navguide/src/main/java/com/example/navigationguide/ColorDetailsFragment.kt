package com.example.navigationguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class ColorDetailsFragment : Fragment() {

    private lateinit var navController: NavController

    private lateinit var color: TextView
    private lateinit var body: TextView
    private lateinit var swapButton: ImageButton

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
        bindData()
        setupListeners()
        return root
    }

    private fun bindData() {

    }

    private fun setupListeners() {
        swapButton.setOnClickListener { swapPushed() }
    }

    private fun swapPushed() {
        val action = ColorDetailsFragmentDirections
            .actionColorDetailsFragmentToColorCommentsFragment()
        navController.navigate(action)
    }

}