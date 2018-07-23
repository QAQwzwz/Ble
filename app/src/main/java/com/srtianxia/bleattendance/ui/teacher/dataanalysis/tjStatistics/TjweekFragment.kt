package com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.attChoose.Course
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.AttendanceAdapter.NORMAL
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.CourseStatisticsActivity
import com.srtianxia.bleattendance.utils.getIndexOfWeek
import kotlinx.android.synthetic.main.fragment_tjweek.*
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils


/**
 * A simple [Fragment] subclass.
 *
 */
class TjweekFragment : Fragment() {

    private lateinit var course: Course

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init2()
    }

    public fun setCourse(course: Course) {
        this.course = course
    }

    private fun init2() {
        val s = course.day.split(",")
        val indexs = s.map {
            getIndexOfWeek(it)
        }
        val columnList = ArrayList<Column>() //柱子列表
        var subcolumnValueList: MutableList<SubcolumnValue>     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        for (i in 1..5) {
            subcolumnValueList = ArrayList()
            val v = Math.random().toFloat() * 4f + 55
            subcolumnValueList.add(SubcolumnValue(v, ChartUtils.pickColor()))
            subcolumnValueList.add(SubcolumnValue(60 - v, Color.parseColor("#bfbfbf")))
            Log.e("zia", subcolumnValueList.toString())
            val column = Column(subcolumnValueList)
            column.setHasLabels(true)
            columnList.add(column)
        }
        val mColumnChartData = ColumnChartData(columnList)

        val axisX = Axis()
        val axisY = Axis()
        val axisValueList = ArrayList<AxisValue>()
        for ((i, str) in arrayOf("19周", "18周", "17周", "16周", "15周").withIndex()) {
            val axisValue = AxisValue(i.toFloat())
            axisValue.setLabel(str)
            axisValueList.add(axisValue)
        }
        axisX.values = axisValueList
        axisX.textColor = Color.parseColor("#333333")
        axisY.name = "考勤学生人次"    //设置竖轴名称
        axisY.textColor = Color.parseColor("#333333")
        mColumnChartData.axisXBottom = axisX //设置横轴
        mColumnChartData.axisYLeft = axisY   //设置竖轴
        mColumnChartData.isStacked = true
        columnChart.onValueTouchListener = object : ColumnChartOnValueSelectListener {
            override fun onValueDeselected() {

            }

            override fun onValueSelected(p0: Int, p1: Int, p2: SubcolumnValue?) {
                val intent = Intent(context, CourseStatisticsActivity::class.java)
                intent.putExtra("course", course)
                intent.putExtra("flag", NORMAL)
                startActivity(intent)
            }

        }
        columnChart.columnChartData = mColumnChartData
        columnChart.isFocusableInTouchMode = false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tjweek, container, false)
    }


}
