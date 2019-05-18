package com.poop9.poop.map

import com.poop9.poop.model.LocationData

interface MapDelegate {
    fun changeCamera(location: LocationData)
    fun placeMyMark(location: LocationData)
    fun placePoopMark(location: LocationData)
}
