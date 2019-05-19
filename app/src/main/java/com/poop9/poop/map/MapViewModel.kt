package com.poop9.poop.map

import android.annotation.SuppressLint
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.poop9.poop.model.LocationData
import kotlinx.coroutines.launch

@SuppressLint("MissingPermission")
class MapViewModel : ViewModel() {

    private val _initialize = MutableLiveData<LocationData>()
    val initialize: LiveData<LocationData>
        get() = _initialize

    private val _sendGpsData = MutableLiveData<LocationData>()
    val sendGpsData: LiveData<LocationData>
        get() = _sendGpsData

    var locationClient: FusedLocationProviderClient? = null

    fun start(locationClient: FusedLocationProviderClient) {
        this.locationClient = locationClient

        lastLocation(_initialize::postValue)
    }

    private fun lastLocation(locationListener: (LocationData) -> Unit) {
        locationClient?.lastLocation
            ?.addOnFailureListener(Exception::printStackTrace)
            ?.addOnSuccessListener { location: Location? ->
                location
                    ?.let(LocationData.Companion::from)
                    ?.let(locationListener)
            }
    }

    fun sendGpsData() {
        viewModelScope.launch {
            lastLocation {
                _sendGpsData.postValue(it)
            }
        }
    }
}