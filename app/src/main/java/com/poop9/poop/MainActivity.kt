package com.poop9.poop

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.github.nkzawa.emitter.Emitter
import com.poop9.poop.data.api.PoopRepository
import com.poop9.poop.data.request.SocketRequest
import com.poop9.poop.databinding.ActivityMainBinding
import com.poop9.poop.map.MapFragment
import com.poop9.poop.report.ReportFragment
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : SocketActivity() {

    private val repo: PoopRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = bind<ActivityMainBinding>(R.layout.activity_main)

        binding.bottomNavigation.setOnNavigationItemSelectedListener(this::handleMenuItemSelected)
        binding.bottomNavigation.setOnNavigationItemReselectedListener {} // For prevent recreating fragments

        showMapFragment()

        lifecycleScope.launch {
            showLoginDialogWhenFirst()
        }

        mSocket.on(EVENT_NAME, messageReceiver)
    }

    private val messageReceiver = Emitter.Listener { item ->
        Log.e("asdf", "received")
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket.off(EVENT_NAME, messageReceiver)
    }

    private fun handleMenuItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.main_map -> showMapFragment()
            R.id.main_report -> showReportFragment()
            else -> return false
        }

        return true
    }

    private suspend fun showLoginDialogWhenFirst() {
        if (repo.getToken().isEmpty())
            showLoginDialog()
    }

    fun sendPoop() {
        toast("보내Yo!")
        mSocket.emit(EVENT_NAME, "Message")
        repo.addPoopCount()
    }

    suspend fun attemptSend() {
        toast("yo!")
        mSocket.emit(EVENT_NAME, SocketRequest(repo.getToken(), "yo!"))
    }

    private fun showLoginDialog() {
        val dialog = LoginDialog().onLogin { nickname ->
            lifecycleScope.launch {
                val tokenResult = repo.signUp(nickname)
                Log.e("MainActivity", tokenResult.token)
            }
        }

        showDialogFragment(dialog, "Login")
    }

    private fun showMapFragment() {
        replace(R.id.fragment_container, MapFragment())
    }

    private fun showReportFragment() {
        replace(R.id.fragment_container, ReportFragment())
    }
}
