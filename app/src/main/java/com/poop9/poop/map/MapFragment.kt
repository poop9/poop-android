package com.poop9.poop.map

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.observe
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.SupportMapFragment
import com.poop9.poop.R
import com.poop9.poop.base.BaseFragment
import com.poop9.poop.databinding.FragmentMapBinding
import com.poop9.poop.getViewModel
import com.poop9.poop.model.LocationData
import pub.devrel.easypermissions.EasyPermissions

@SuppressLint("MissingPermission")
class MapFragment : BaseFragment() {
    override val layoutId: Int
        get() = R.layout.fragment_map

    private lateinit var mapDelegate: MapDelegate

    private lateinit var binding: FragmentMapBinding

    private val vm by lazy { getViewModel<MapViewModel>() }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        checkPermission {
            // Should have permission already. Close the app.
            activity?.finishAffinity()
        }
    }

    private fun checkPermission(ifNotGranted: () -> Unit) {
        val perms = arrayOf(ACCESS_FINE_LOCATION)
        val activity = activity as FragmentActivity
        if (!EasyPermissions.hasPermissions(activity, *perms)) {
            ifNotGranted()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)

        vm.start(getLocationClient())

        vm.location.observe(this) { locationData ->
            loadMap(locationData)
        }
    }

    private fun getLocationClient(): FusedLocationProviderClient {
        val activity = activity ?: throw IllegalStateException()
        return LocationServices.getFusedLocationProviderClient(activity)
    }

    private fun loadMap(locationData: LocationData) {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mapDelegate = MapDelegateImpl(googleMap)
            mapDelegate.changeCamera(locationData)
            mapDelegate.placeMyMark(locationData)

            for (i in 1..10) {
                mapDelegate.placePoopMark(randomLocationDataFrom(locationData))
            }
        }
    }

    private fun randomLocationDataFrom(locationData: LocationData): LocationData {
        return locationData.translate(
            makeRandomRange(),
            makeRandomRange()
        )
    }

    private fun makeRandomRange() = Math.random() * 0.02 - 0.01
}