package com.poop9.poop

import android.os.Bundle
import android.util.Log
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.poop9.poop.base.BaseActivity
import com.poop9.poop.data.api.PoopRepository
import com.poop9.poop.data.request.SocketRequest
import org.json.JSONException
import org.json.JSONObject
import org.koin.android.ext.android.inject
import java.net.URISyntaxException


open class SocketActivity : BaseActivity() {
    private val repo: PoopRepository by inject()

    private var mSocket: Socket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSocket = IO.socket("https://poop-server.herokuapp.com/")

        //socket connect
        mSocket!!.connect()
//        mSocket!!.on("new%20message", onNewMessage)
        mSocket!!.on("newmessage", messageReceiver)
    }

    private val messageReceiver = Emitter.Listener {
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
    suspend fun attemptSend() {
        toast("yo!")
        mSocket!!.emit("newmessage", SocketRequest(repo.getToken() , "yo!"))
    }

    suspend fun attemptSend2(){
        /*
        toast("yo!")
        val data = JSONObject()
        try {
            data.put("token", repo.getToken())
            data.put("message", "yo!")
            mSocket!!.emit("new message", data)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        */




    }

    public override fun onDestroy() {
        super.onDestroy()

        mSocket!!.disconnect()
        mSocket!!.off("new message", messageReceiver)
    }
}
