package com.example.navigationguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ColorDetailsFragment : Fragment() {

    private lateinit var et_title: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_color_details, container, false)
        et_title = root.findViewById(R.id.tv_description_title)
        et_title.text = "Some Awesome Details"
        return root
    }

}