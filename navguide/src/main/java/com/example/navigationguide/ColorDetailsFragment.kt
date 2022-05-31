package com.example.navigationguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ColorDetailsFragment : Fragment() {

    private lateinit var color: TextView
    private lateinit var body: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_color_details, container, false)
        color = root.findViewById(R.id.tv_description_color)
        body = root.findViewById(R.id.tv_description_body)
        bindData()
        return root
    }

    private fun bindData() {

    }

}