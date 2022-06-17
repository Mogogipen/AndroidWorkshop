package com.example.navigationguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

class ColorCommentsFragment : Fragment() {

    private val colorViewModel: ColorViewModel by lazy {
        ViewModelProvider(requireActivity())[ColorViewModel::class.java]
    }

    private lateinit var navController: NavController

    private lateinit var color: TextView
    private lateinit var swapButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_color_comments, container, false)
        color = root.findViewById(R.id.tv_comments_color)
        swapButton = root.findViewById(R.id.button_comments_swap)
        setupListeners()
        setupObserver()
        return root
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
        val action = ColorCommentsFragmentDirections
            .actionColorCommentsFragmentToColorDetailsFragment()
        navController.navigate(action)
    }

    private fun handleColorChange(newColor: Int) {
        color.text = UtilColor.colorIntToString(newColor)
    }

}