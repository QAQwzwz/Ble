package com.srtianxia.bleattendance.ui.teacher.dataanalysis.detailAttStatistics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.srtianxia.bleattendance.R
import kotlinx.android.synthetic.main.activity_detail_att.*
import kotlinx.android.synthetic.main.appbar_layout.*

class DetailAttActivity : AppCompatActivity() {

    private lateinit var jxbId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_att)

        tv_toolbar_title.text = "统计"

        jxbId = intent.getStringExtra("jxbId")
        tj_viewPager.adapter = MyAdapter(supportFragmentManager)
        tj_tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.colorPrimary))
        tj_tabLayout.setupWithViewPager(tj_viewPager)
    }

    internal inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var list: ArrayList<Fragment> = ArrayList()
        private var titles = arrayOf("日统计", "周统计", "月统计")

        init {
            list.add(TjdayFragment())
            list.add(TjweekFragment())
            list.add(TjmonthFragment())
        }

        override fun getItem(position: Int): Fragment {
            return list[position]
        }

        override fun getCount(): Int {
            return list.size
        }

        //重写这个方法，将设置每个Tab的标题
        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}
