package com.poop9.poop.map

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.SupportMapFragment
import com.poop9.poop.MainActivity
import com.poop9.poop.R
import com.poop9.poop.base.BaseFragment
import com.poop9.poop.data.api.PoopRepository
import com.poop9.poop.databinding.FragmentMapBinding
import com.poop9.poop.getViewModel
import com.poop9.poop.model.LocationData
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import pub.devrel.easypermissions.EasyPermissions
import kotlin.random.Random




@SuppressLint("MissingPermission")
class MapFragment : BaseFragment() {

    private val repo: PoopRepository by inject()

    override val layoutId: Int
        get() = R.layout.fragment_map

    private lateinit var mapDelegate: MapDelegate
    private lateinit var binding: FragmentMapBinding

    private var activate: Boolean = false

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

        binding.poopStartFab.setOnClickListener {
            if (!activate) {
                activate()
                vm.sendGpsData()
            } else {
                deactivate()
            }
        }

        vm.start(getLocationClient())

        vm.initialize.observe(this) { locationData ->
            loadMap(locationData)
        }

        vm.sendGpsData.observe(this) { locationData ->
            lifecycleScope.launch {
                try {
                    repo.gps(locationData)
                    Log.e("asdf", "GPS Complete")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        map_screen.setOnClickListener {
            if (activate) {
                screenTouch()
                for (i in 1..10) {
                    showPoopText()}
                showPoopImage()
            }

        }
    }

    private fun activate() {
        binding.poopStartFab.supportBackgroundTintList =
            ColorStateList.valueOf(
                ResourcesCompat.getColor(resources, R.color.lightish_blue, null)
            )
        binding.poopStartFab.supportImageTintList =
            ColorStateList.valueOf(
                ResourcesCompat.getColor(resources, R.color.white, null)
            )
        binding.poopStartFab.rippleColor =
            ResourcesCompat.getColor(resources, R.color.white, null)
        binding.poopLabel.visibility = View.VISIBLE
        activate = true
    }

    private fun deactivate() {
        binding.poopStartFab.supportBackgroundTintList =
            ColorStateList.valueOf(
                ResourcesCompat.getColor(resources, R.color.white, null)
            )
        binding.poopStartFab.supportImageTintList =
            ColorStateList.valueOf(
                ResourcesCompat.getColor(resources, R.color.black, null)
            )
        binding.poopStartFab.rippleColor =
            ResourcesCompat.getColor(resources, R.color.lightish_blue, null)
        binding.poopLabel.visibility = View.INVISIBLE
        activate = false
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

            for (i in 1..20) {
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

    val width = 1100
    val height = 300
    private fun showPoopText(){

        //create Image
        val textView = TextView(context)
        textView.text = "POOP"

        //random location
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(Random.nextInt(width), Random.nextInt(height),0,0)
        textView.layoutParams = layoutParams
        map_screen_show_poop.addView(textView)
        map_screen_show_poop.invalidate()

        //load animation
        val animation = AnimationSet(true)
        animation.addAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out_zoom_out))
        textView.animation = animation
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                textView.visibility = View.GONE
                map_screen_show_poop.invalidate()
            }
            // All the other override functions
        })
    }
    /**
     * show Poop and auto fade out
     */
    private fun showPoopImage() {

        //create Image
        val imageView = ImageView(context)
        imageView.setImageResource(R.drawable.img_poop)

        //random location
        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(Random.nextInt(width), Random.nextInt(height),0,0)
        imageView.layoutParams = layoutParams
        map_screen_show_poop.addView(imageView)
        map_screen_show_poop.invalidate()

        //load animation
        val animation = AnimationSet(true)
        animation.addAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_out_zoom_out))
        imageView.animation = animation
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                imageView.visibility = View.GONE
                map_screen_show_poop.invalidate()
            }
            // All the other override functions
        })
    }

    private fun screenTouch() {
        val activity = activity as MainActivity
        lifecycleScope.launch {
            activity.attemptSend2()
        }
    }

    private fun makeRandomRange() = Math.random() * 0.02 - 0.01
}