package com.srtianxia.bleattendance.ui.teacher.dataanalysis.attDetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

import com.srtianxia.bleattendance.R
import kotlinx.android.synthetic.main.appbar_layout.*

/**
 * 考勤详细
 * 学生列表
 */

class AttDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        setContentView(R.layout.activity_att_detail)
        tv_toolbar_title.text = "打卡明细"
        val jxbId = intent.getStringExtra("jxbId")


    }
}
