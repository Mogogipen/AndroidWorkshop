package com.example.navigationguide

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class HomeFragment : Fragment() {

    private val args: HomeFragmentArgs by navArgs()
    private lateinit var colorLabel: TextView
    private lateinit var colorField: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        colorField = root.findViewById(R.id.et_color)
        colorLabel = root.findViewById(R.id.tv_color)
        val button1 = root.findViewById<Button>(R.id.button_add_blue)
        button1.setOnClickListener { nextScreenClicked() }
        button1.text = "Colors"
        val button2 = root.findViewById<Button>(R.id.button_add_red)
        button2.setOnClickListener { colorFragmentClicked() }
        button2.text = "Select Color"
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val controller = findNavController()
        controller.currentBackStackEntry?.savedStateHandle?.getLiveData<Int>("color")?.observe(
            viewLifecycleOwner) { result ->
                colorLabel.text = "#${result.toString(16)}"
            }
    }

    private fun nextScreenClicked() {
        val navController = findNavController()
        val action = HomeFragmentDirections.actionHomeFragmentToYellowFragment()
        navController.navigate(action)
    }

    private fun colorFragmentClicked() {
        val navController = findNavController()
        var colorString = colorField.text.toString().replace("#", "")
        if (colorString.trim().isEmpty()) colorString = "ff888888"
        try {
            val color = colorString.toLong(16).toInt()
            val action = HomeFragmentDirections.actionHomeFragmentToColorFragment(color)
            navController.navigate(action)
        } catch (e: NumberFormatException) {
            val message = "Please enter a valid hex number"
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}