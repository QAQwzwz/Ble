package com.srtianxia.bleattendance.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

/**
 * Created by zia on 2018/4/25.
 */
fun log(msg: String) {
    Log.e("zzzia", msg)
}

fun toast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}