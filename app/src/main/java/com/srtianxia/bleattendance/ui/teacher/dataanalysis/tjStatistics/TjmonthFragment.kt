package com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srtianxia.bleattendance.R
import kotlinx.android.synthetic.main.fragment_tjmonth.*
import lecho.lib.hellocharts.model.*
import lecho.lib.hellocharts.util.ChartUtils
import java.text.SimpleDateFormat
import java.util.*

class TjmonthFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lines = ArrayList<Line>()
        val values = ArrayList<PointValue>()
        for (j in 0 until 4) {
            values.add(PointValue(j.toFloat(), Math.random().toFloat() * 20f + 400))
        }

        tj_month_title.text = SimpleDateFormat("MM").format(Date()) + "月考勤数据"

        /*========== 设置线的一些属性 ==========*/
        val line = Line(values)               //根据值来创建一条线
        line.setColor(ChartUtils.COLORS[0])        //设置线的颜色
        line.setShape(ValueShape.CIRCLE)                 //设置点的形状
        line.setHasLines(true)               //设置是否显示线
        line.setHasPoints(true)             //设置是否显示节点
//        line.setCubic(isCubic)                     //设置线是否立体或其他效果
//        line.setFilled(isFilled)                   //设置是否填充线下方区域
        line.setHasLabels(true)       //设置是否显示节点标签
        //设置节点点击的效果
        line.setHasLabelsOnlyForSelected(true)
        //如果节点与线有不同颜色 则设置不同颜色

        lines.add(line)

        val axisX = Axis()
        val axisY = Axis()
        val axisValueList = ArrayList<AxisValue>()
        val str = arrayOf("第一周", "第二周", "第三周", "第四周")
        for (i in 0..3) {
            val axisValue = AxisValue(i.toFloat())
            axisValue.setLabel(str[i])
            axisValueList.add(axisValue)
        }
        axisX.values = axisValueList
        axisX.textColor = Color.parseColor("#333333")
        axisY.name = "考勤学生人次"    //设置竖轴名称
        axisY.textColor = Color.parseColor("#333333")
        val lineData = LineChartData(lines)
        lineData.axisYLeft = axisY
        lineData.axisXBottom = axisX
        lineData.isValueLabelBackgroundAuto = true
        lineData.baseValue = 300f

        chart.lineChartData = lineData
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tjmonth, container, false)
    }


}
