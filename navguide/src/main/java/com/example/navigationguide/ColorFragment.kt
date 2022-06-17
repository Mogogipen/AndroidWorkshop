package com.example.navigationguide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ColorFragment : Fragment() {

    private val colorViewModel: ColorViewModel by lazy {
        ViewModelProvider(requireActivity())[ColorViewModel::class.java]
    }

    private val args: ColorFragmentArgs by navArgs()
    private lateinit var navController: NavController

    private var color: Int = 0xFF888888.toInt()

    private lateinit var root: View
    private lateinit var detailsView: FragmentContainerView
    private lateinit var bottomBarButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        color = args.color
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_color, container, false)
        root.setBackgroundColor(color)
        bottomBarButton = root.findViewById(R.id.button_bottom_bar)
        detailsView = root.findViewById(R.id.embedded_nav_fragment)

        setupListeners()
        setupObserver()

        colorViewModel.setColor(color)

        return root
    }

    private fun setupListeners() {
        root.findViewById<Button>(R.id.button_add_blue).setOnClickListener { addBlueClicked() }
        root.findViewById<Button>(R.id.button_add_red).setOnClickListener { addRedClicked() }
        bottomBarButton.setOnClickListener { bottomBarPushed() }
    }

    private fun setupObserver() {
        colorViewModel.liveColor.observe(viewLifecycleOwner) {
            handleColorChange(it)
        }
    }

    private fun addBlueClicked() {
        colorViewModel.setColor(color + 0x00000011)
    }

    private fun addRedClicked() {
        colorViewModel.setColor(color + 0x00110000)
    }

    private fun bottomBarPushed() {
        detailsView.visibility = if (detailsView.visibility == View.VISIBLE) {
            bottomBarButton.setImageResource(R.drawable.ic_up_drawer)
            View.GONE
        } else {
            bottomBarButton.setImageResource(R.drawable.ic_down_drawer)
            View.VISIBLE
        }
    }

    private fun handleColorChange(newColor: Int) {
        color = newColor
        root.setBackgroundColor(color)
        val controller = findNavController()
        controller.previousBackStackEntry?.savedStateHandle?.set("color", color)
    }
}