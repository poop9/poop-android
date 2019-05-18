package com.poop9.poop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_socket.*
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException


class SocketActivity : AppCompatActivity() {

    private var mSocket: Socket? = null

    init {
        try {
            mSocket = IO.socket("https://poop-server.herokuapp.com/")
        } catch (e: URISyntaxException) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)

        //socket connect
        mSocket!!.connect()
        mSocket!!.on("new message", onNewMessage)

        //button listener
        socket_test_button.setOnClickListener {attemptSend()}
    }

    private val onNewMessage = Emitter.Listener { args ->
        this@SocketActivity.runOnUiThread(Runnable {
            val data = args[0] as JSONObject
            val username: String
            val message: String
            try {
                username = data.getString("username")
                message = data.getString("message")
            } catch (e: JSONException) {
                return@Runnable
            }

            // add the message to view
            addMessage(username, message)
        })
    }

    //show message
    private fun addMessage(username: String, message: String){
        socket_test_text.text = String.format("%s :%s",username,message)
    }

    //emitting message
    private fun attemptSend() {
        mSocket!!.emit("new message", "yo!")
    }

    public override fun onDestroy() {
        super.onDestroy()

        mSocket!!.disconnect()
        mSocket!!.off("new message", onNewMessage)
    }
}
