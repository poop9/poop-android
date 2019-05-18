package com.poop9.poop.model

import android.location.Location
import com.google.android.gms.maps.model.LatLng

data class LocationData(
    val latitude: Double,
    val longitude: Double
) {
    companion object {
        fun from(location: Location): LocationData {
            return LocationData(
                location.latitude,
                location.longitude
            )
        }
    }

    fun toLatLng(): LatLng = LatLng(latitude, longitude)

    fun translate(latitude: Double, longitude: Double): LocationData =
        LocationData(
            this.latitude + latitude,
            this.longitude + longitude
        )
}