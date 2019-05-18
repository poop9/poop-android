package com.poop9.poop

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

fun <T : ViewDataBinding> Activity.bind(@LayoutRes layoutId: Int, initializer: (T.() -> Unit)? = null): T =
    DataBindingUtil.setContentView<T>(this, layoutId).apply {
        initializer?.invoke(this)
    }

inline fun <reified T : Activity> Activity.startActivity(intentModifier: (Intent).() -> Unit = {}) {
    Intent(this, T::class.java).let {
        intentModifier(it)
        startActivity(it)
    }
}

/**
 * Example:
 * replaceWhen(R.id.fragment_container, MyFragment()) {
 *     a == 4
 * }
 */
fun FragmentActivity.replaceWhen(
    @IdRes containerId: Int, fragment: Fragment,
    predicate: () -> Boolean
) {
    if (predicate())
        replace(containerId, fragment)
}

/**
 * Example:
 * replace(R.id.fragment_container, MyFragment())
 */
fun FragmentActivity.replace(@IdRes containerId: Int, fragment: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment)
        .commitNow()
}

fun Activity.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}