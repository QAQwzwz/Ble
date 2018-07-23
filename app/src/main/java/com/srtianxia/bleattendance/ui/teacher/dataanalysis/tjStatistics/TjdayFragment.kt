package com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.attChoose.Course
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.AttendanceAdapter.DAY
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.CourseStatisticsActivity
import kotlinx.android.synthetic.main.fragment_tjday.*
import java.util.*

/**
 * 日统计页面
 */
class TjdayFragment : Fragment() {

    private lateinit var course: Course

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tj_day_date.text = getWeek()

        circleDataView.update(57, 60)
        circleDataView.setOnClickListener {
            val intent = Intent(activity, CourseStatisticsActivity::class.java)
            intent.putExtra("course", course)
            intent.putExtra("flag",DAY)
            startActivity(intent)
        }
        counterView.load(3, 2, 1)
    }

    public fun setCourse(course: Course) {
        this.course = course
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tjday, container, false)
    }

    private fun getWeek(): String {
        val cal = Calendar.getInstance()
        var day: String = ""
        for (i in 0..7) {
            cal.add(Calendar.DATE, -i)
            val d = getDay(cal.get(Calendar.DAY_OF_WEEK))
            if (course.day.contains(d)) {
                return d
            }
        }
        return ""
    }

    private fun getDay(i: Int): String {
        when (i) {
            1 ->
                return "星期日"
            2 ->
                return "星期一"
            3 ->
                return "星期二"
            4 ->
                return "星期三"
            5 ->
                return "星期四"
            6 ->
                return "星期五"
            7 ->
                return "星期六"
            else ->
                return ""
        }
    }
}
