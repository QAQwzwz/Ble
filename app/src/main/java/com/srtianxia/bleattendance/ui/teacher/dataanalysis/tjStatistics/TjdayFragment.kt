package com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.attDetail.AttDetailActivity
import kotlinx.android.synthetic.main.fragment_tjday.*

/**
 * 日统计页面
 *
 */
class TjdayFragment : Fragment() {

    private lateinit var jxbId: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        circleDataView.update(60, 100)
        circleDataView.setOnClickListener {
            val intent = Intent(activity, AttDetailActivity::class.java)
            intent.putExtra("jxbId", jxbId)
            startActivity(intent)
        }
    }

    public fun setJxbId(jxbId: String) {
        this.jxbId = jxbId
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tjday, container, false)
    }


}
