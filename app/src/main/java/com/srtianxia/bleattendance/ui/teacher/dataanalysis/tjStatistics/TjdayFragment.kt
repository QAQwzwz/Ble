package com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.CourseStatisticsActivity
import kotlinx.android.synthetic.main.fragment_tjday.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * 日统计页面
 *
 */
class TjdayFragment : Fragment() {

    private lateinit var jxbId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tj_day_date.text = SimpleDateFormat("yyyy/MM/dd").format(Date())
        circleDataView.update(67, 73)
        circleDataView.setOnClickListener {
            val intent = Intent(activity, CourseStatisticsActivity::class.java)
            intent.putExtra("jxbId", jxbId)
            startActivity(intent)
        }
        counterView.load(1, 3, 3)
    }

    public fun setJxbId(jxbId: String) {
        this.jxbId = jxbId
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tjday, container, false)
    }
}
