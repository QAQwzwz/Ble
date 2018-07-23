package com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.attChoose.Course
import kotlinx.android.synthetic.main.activity_detail_att.*
import kotlinx.android.synthetic.main.appbar_layout.*

/**
 * 统计页面
 * TjdayFragment 日统计fragment
 * TjweekFragment 周统计fragment
 * TjmonthFragment 月统计fragment
 */
class TjActivity : AppCompatActivity() {

    private lateinit var course: Course
    //直接写了三个fragment，因为打卡明细在日统计和周统计的接口可能不一样
    private val tjdayFragment = TjdayFragment()
    private val tjweekFragment = TjweekFragment()
    private val tjmonthFragment = TjmonthFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        setContentView(R.layout.activity_detail_att)

        tv_toolbar_title.text = "考勤统计"

        course = intent.getSerializableExtra("course") as Course

        //传入course
        tjdayFragment.setCourse(course)
        tjweekFragment.setCourse(course)


        //设置viewPager
        tj_viewPager.adapter = MyAdapter(supportFragmentManager)
        tj_tabLayout.setSelectedTabIndicatorColor(resources.getColor(R.color.colorPrimary))
        tj_tabLayout.setupWithViewPager(tj_viewPager)
    }

    internal inner class MyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private var list: ArrayList<Fragment> = ArrayList()
        private var titles = arrayOf("日统计", "周统计", "月统计")

        init {

            list.add(tjdayFragment)
            list.add(tjweekFragment)
            list.add(tjmonthFragment)
        }

        override fun getItem(position: Int): Fragment {
            return list[position]
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titles[position]
        }
    }
}
