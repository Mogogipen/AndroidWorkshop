package com.example.navigationguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

class ColorFragment : Fragment() {

    private val args: ColorFragmentArgs by navArgs()
    private var color: Int = 0xFF888888.toInt()

    private lateinit var root: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        color = args.color
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_color, container, false)
        root.setBackgroundColor(color)
        root.findViewById<Button>(R.id.button_add_blue).setOnClickListener { addBlueClicked() }
        root.findViewById<Button>(R.id.button_add_red).setOnClickListener { addRedClicked() }
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

    private fun handleColorChange() {
        root.setBackgroundColor(color)
        val controller = findNavController()
        controller.previousBackStackEntry?.savedStateHandle?.set("color", color)
    }
}