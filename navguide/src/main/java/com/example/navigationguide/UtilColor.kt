package com.example.navigationguide

import androidx.compose.ui.graphics.Color

class UtilColor {

    companion object {

        /**
         * Formats a color (of type int) as a string in the format "#0F0F0F0F"
         *
         * @param color A color with an alpha channel
         * @return A string representation of the color ("#0F0F0F0F")
         */
        fun colorIntToString(color: Int): String {
            return with(Color(color)) {
                val a = (alpha * 255).toInt()
                val r = (red * 255).toInt()
                val g = (green * 255).toInt()
                val b = (blue * 255).toInt()
                "#%02X%02X%02X%02X".format(a, r, g, b)
            }
        }
    }

}