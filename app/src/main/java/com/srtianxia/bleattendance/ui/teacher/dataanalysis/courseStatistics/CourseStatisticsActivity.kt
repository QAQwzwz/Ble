package com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.srtianxia.bleattendance.R
import kotlinx.android.synthetic.main.appbar_layout.*

/**
 * 课程数据
 */
class CourseStatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        setContentView(R.layout.activity_course_statistics)
        tv_toolbar_title.text = "课程数据"
    }
}
