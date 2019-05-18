package com.poop9.poop

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.poop9.poop.base.BaseFragment
import com.poop9.poop.databinding.FragmentMapBinding
import pub.devrel.easypermissions.EasyPermissions


class MapFragment : BaseFragment(), OnMapReadyCallback {
    override val layoutId: Int
        get() = R.layout.fragment_map

    private lateinit var map: GoogleMap
    private lateinit var binding: FragmentMapBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val perms = arrayOf(ACCESS_FINE_LOCATION)
        val activity = activity as FragmentActivity
        if (!EasyPermissions.hasPermissions(activity, *perms)) {
            // Should have permission already. Close the app.
            activity.finishAffinity()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMapBinding.bind(view)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return
        map.mapType = GoogleMap.MAP_TYPE_NORMAL
        map.isMyLocationEnabled = true

        map.setOnMyLocationClickListener { location ->
            Log.d("MapFragment", "$location")
        }
    }
}