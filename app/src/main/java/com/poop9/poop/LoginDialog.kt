package com.poop9.poop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginDialog : DialogFragment(), Dimm {

    private var listener: (String) -> Unit = {}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_go.setOnClickListener {
            val nickname = login_edit.text.toString()
            listener(nickname)
            dismiss()
        }
    }

    fun onLogin(listener: (String) -> Unit): LoginDialog {
        this.listener = listener
        return this
    }
}