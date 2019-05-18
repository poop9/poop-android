package com.poop9.poop.map

import android.annotation.SuppressLint
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.MarkerOptions
import com.poop9.poop.R
import com.poop9.poop.model.LocationData

@SuppressLint("MissingPermission")
class MapDelegateImpl(private val map: GoogleMap) : MapDelegate {
    init {
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    override fun changeCamera(location: LocationData) {
        map.moveCamera(createCameraUpdate(location))
    }

    private fun createCameraUpdate(locationData: LocationData): CameraUpdate =
        CameraPosition.Builder()
            .target(locationData.toLatLng())
            .zoom(15f)
            .build()
            .let(CameraUpdateFactory::newCameraPosition)

    // TODO: 자기 자신 마크 이미지 가져오기
    override fun placeMyMark(location: LocationData) {
        map.addMarker(
            MarkerOptions()
                .position(location.toLatLng())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.img_poop))
                .infoWindowAnchor(0.5f, 0.0f)
        )
    }

    override fun placePoopMark(location: LocationData) {
        map.addMarker(
            MarkerOptions()
                .position(location.toLatLng())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_mark_toilet))
                .infoWindowAnchor(0.5f, 0.0f)
        )
    }
}