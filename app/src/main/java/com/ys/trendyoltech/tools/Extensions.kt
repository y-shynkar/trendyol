package com.ys.trendyoltech.tools

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast

const val TAG: String = "XXX"

fun Context.toast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, duration).show()
}

//inline fun <reified T> T(): String = T::class.java.simpleName

fun log(a: Any? = null, context: Context? = null, tv: TextView? = null) {
    val msg = "${a?.toString() ?: ""}: ${a ?: ""}"  //${Throwable().stackTrace[1]}  ->
    when (a) {
        is Exception -> Log.e(TAG, msg)
        else -> Log.d(TAG, msg)
    }
    tv?.apply { text = "" }?.append(msg)
}

fun l(a: Any? = null, context: Context? = null, tv: TextView? = null) {
    log(a, context, tv)
}

fun Any?.l() {
    this?.let { it.toString() }
}

fun Any?.l(a: Any? = null, context: Context? = null, tv: TextView? = null) {
    log(a, context, tv)

    /*val msg = "${this?.toString() ?: ""}: ${a ?: ""}"  //${Throwable().stackTrace[1]}  ->
    when (a) {
        is Exception -> Log.e(TAG, msg)
        else -> Log.d(TAG, msg)
    }
    tv?.apply { text = "" }?.append(msg)*/
}

fun Any?.lt(a: Any? = null, context: Context? = null, tv: TextView? = null) {
    l(a, context, tv)
    context?.toast(a.toString())
}

fun Any?.showErrorMsg(msg: String) {
    when (this) {
     is Context -> toast(msg)
    }
}