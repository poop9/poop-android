package com.poop9.poop

import android.os.Bundle
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.poop9.poop.base.BaseActivity


open class SocketActivity : BaseActivity() {
    companion object {
        const val EVENT_NAME = "newmessage"
    }

    protected lateinit var mSocket: Socket

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSocket = IO.socket("https://poop-server.herokuapp.com/")

        //socket connect
        mSocket.connect()
    }

    public override fun onDestroy() {
        super.onDestroy()

        mSocket.disconnect()
    }
}
