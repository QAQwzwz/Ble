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

fun getIndexOfWeek(day: String): Int {
    return when (day) {
        "星期一" -> 1
        "星期二" -> 2
        "星期三" -> 3
        "星期四" -> 4
        "星期五" -> 5
        "星期六" -> 6
        "星期日" -> 7
        else -> 1
    }
}

fun getDay(int: Int): String {
    return when (int) {
        1 -> "星期一"
        2 -> "星期二"
        3 -> "星期三"
        4 -> "星期四"
        5 -> "星期五"
        6 -> "星期六"
        7 -> "星期日"
        else -> ""
    }
}