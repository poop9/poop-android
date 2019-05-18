package com.poop9.poop.map

import android.annotation.SuppressLint
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.poop9.poop.model.LocationData

@SuppressLint("MissingPermission")
class MapViewModel : ViewModel() {
    private val _location = MutableLiveData<LocationData>()
    val location: LiveData<LocationData>
        get() = _location

    var locationClient: FusedLocationProviderClient? = null

    fun start(locationClient: FusedLocationProviderClient) {
        this.locationClient = locationClient

        locationClient.lastLocation
            .addOnFailureListener(Exception::printStackTrace)
            .addOnSuccessListener { location: Location? ->
                location?.let(LocationData.Companion::from)?.let {
                    _location.postValue(it)
                }
            }
    }
}