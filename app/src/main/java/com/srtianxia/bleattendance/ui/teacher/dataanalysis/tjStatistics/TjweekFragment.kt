package com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.utils.toast
import kotlinx.android.synthetic.main.fragment_tjweek.*
import lecho.lib.hellocharts.listener.ColumnChartOnValueSelectListener
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils


/**
 * A simple [Fragment] subclass.
 *
 */
class TjweekFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val columnList = ArrayList<Column>() //柱子列表
        var subcolumnValueList: MutableList<SubcolumnValue>     //子柱列表（即一个柱子，因为一个柱子可分为多个子柱）
        for (i in 1..7) {
            subcolumnValueList = ArrayList()
            subcolumnValueList.add(SubcolumnValue(Math.random().toFloat() * 10f + 60, ChartUtils.pickColor()))
            val column = Column(subcolumnValueList)
            column.setHasLabels(true)
            columnList.add(column)
        }
        val mColumnChartData = ColumnChartData(columnList)

        val axisX = Axis()
        val axisY = Axis()
        val axisValueList = ArrayList<AxisValue>()
        val str = arrayOf("星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期天")
        for (i in 0..6) {
            val axisValue = AxisValue(i.toFloat())
            axisValue.setLabel(str[i])
            axisValueList.add(axisValue)
        }
        axisX.values = axisValueList
        axisX.textColor = Color.parseColor("#333333")
        axisY.name = "考勤学生人次"    //设置竖轴名称
        axisY.textColor = Color.parseColor("#333333")
        mColumnChartData.axisXBottom = axisX //设置横轴
        mColumnChartData.axisYLeft = axisY   //设置竖轴
        columnChart.onValueTouchListener = object : ColumnChartOnValueSelectListener {
            override fun onValueDeselected() {

            }

            override fun onValueSelected(p0: Int, p1: Int, p2: SubcolumnValue?) {
                toast(context!!, "$p0: Int, $p1: Int, $p2: SubcolumnValue")
            }

        }
        columnChart.columnChartData = mColumnChartData

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tjweek, container, false)
    }


}
