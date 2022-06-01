package com.example.navigationguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ColorFragment : Fragment() {

    private val args: ColorFragmentArgs by navArgs()
    private lateinit var navController: NavController
    private var color: Int = 0xFF888888.toInt()

    private lateinit var root: View
    private lateinit var detailsView: FragmentContainerView

    private var showingComments = false

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
        root.findViewById<Button>(R.id.button_add_blue).setOnClickListener { addBlueClicked() }
        root.findViewById<Button>(R.id.button_add_red).setOnClickListener { addRedClicked() }
        root.findViewById<ImageButton>(R.id.button_details).setOnClickListener { infoClicked() }
        detailsView = root.findViewById(R.id.embedded_nav_fragment)
        handleColorChange()
        return root
    }

    private fun addBlueClicked() {
        color += 0x00000011
        handleColorChange()
    }

    private fun addRedClicked() {
        color += 0x00110000
        handleColorChange()
    }

    private fun infoClicked() {
        if (detailsView.visibility == View.GONE || detailsView.visibility == View.INVISIBLE)
            detailsView.visibility = View.VISIBLE
        else {
            val action = if (showingComments) {
                //switch to showing details
                ColorDetailsFragmentDirections.actionColorDetailsFragmentToColorCommentsFragment()
            } else {
                //switch to showing comments
                ColorCommentsFragmentDirections.actionColorCommentsFragmentToColorDetailsFragment()
            }
            navController.navigate(action)
            showingComments = !showingComments
        }
    }

    private fun handleColorChange() {
        root.setBackgroundColor(color)
        val controller = findNavController()
        controller.previousBackStackEntry?.savedStateHandle?.set("color", color)
    }
}