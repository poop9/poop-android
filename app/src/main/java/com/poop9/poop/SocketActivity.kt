package com.poop9.poop

import android.os.Bundle
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.poop9.poop.base.BaseActivity
import com.poop9.poop.data.request.SignInRequest
import org.json.JSONException
import org.json.JSONObject
import java.net.URISyntaxException
import java.util.*


open class SocketActivity : BaseActivity() {

    private var mSocket: Socket? = null

    init {
        try {
            mSocket = IO.socket("https://poop-server.herokuapp.com/")
        } catch (e: URISyntaxException) {
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //socket connect
        mSocket!!.connect()
//        mSocket!!.on("new%20message", onNewMessage)
        mSocket!!.on("new message", onShowMessagessss)
    }

    private val onShowMessagessss = Emitter.Listener {
        run {
            this@SocketActivity.runOnUiThread {
                toast("poop!")
            }
        }
    }
    private val onNewMessage = Emitter.Listener { args ->
        this@SocketActivity.runOnUiThread(Runnable {
            val data = args[0] as JSONObject
            val username: String
            val message: String
            try {
                //TODO: username 가져오기
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
//        socket_test_text.text = String.format("%s :%s",username,message)
        toast("poop!")

    }

    //emitting message
    fun attemptSend() {
        toast("yo!")
        mSocket!!.emit("new message", SignInRequest(UUID.randomUUID().toString() , "yo!"))
    }

    public override fun onDestroy() {
        super.onDestroy()

        mSocket!!.disconnect()
//        mSocket!!.off("new message", onNewMessage)
        mSocket!!.off("new message", onShowMessagessss)
    }
}
