package com.example.fragmentpractice.data

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class City(
    val state: String,
    val name: String,
    val pop: Int,
    /**
     * Area of the city in square miles.
     */
    val area: Double,
    val location: LatLng
): Parcelable {

    companion object {

        fun getCities(): List<City> {
            return listOf(
                City("Utah", "Salt Lake City", 197756, 110.8, LatLng(40.7608, -111.8910)),
                City("Utah", "Cedar City", 32067, 35.95, LatLng(37.6775, -113.0619)),
                City("Utah", "Washington", 26583, 34.78, LatLng(37.1305, -113.5083)),
                City("Missouri", "St. Louis", 308174, 65.99, LatLng(38.6270, -90.1994)),
                City("Missouri", "Lake Saint Louis", 15989, 9.35, LatLng(38.7809, -90.7884)),
                City("North Dakota", "Mandan", 22301, 13.65, LatLng(46.8267, -100.8896)),
                City("North Dakota", "Rugby", 2724, 2.26, LatLng(48.3689, -99.9962)),
            )
        }

    }

}