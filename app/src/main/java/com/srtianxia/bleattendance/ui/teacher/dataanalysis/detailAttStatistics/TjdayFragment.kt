package com.srtianxia.bleattendance.ui.teacher.dataanalysis.detailAttStatistics


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.srtianxia.bleattendance.R
import kotlinx.android.synthetic.main.fragment_tjday.*

/**
 * A simple [Fragment] subclass.
 *
 */
class TjdayFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        circleDataView.update(60, 100)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tjday, container, false)
    }


}
