package com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.StaticData
import com.srtianxia.bleattendance.entity.StuListEntity
import com.srtianxia.bleattendance.http.Service
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.attChoose.Course
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.AttendanceAdapter.DAY
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.AttendanceAdapter.NORMAL
import com.srtianxia.bleattendance.utils.RxSchedulersHelper
import com.srtianxia.bleattendance.utils.log
import kotlinx.android.synthetic.main.activity_course_statistics.*
import kotlinx.android.synthetic.main.appbar_layout.*
import rx.Observer

/**
 * 课程数据
 */
class CourseStatisticsActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var adapter: AttendanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        setContentView(R.layout.activity_course_statistics)
        tv_toolbar_title.text = "课程数据"

        progressDialog = ProgressDialog.show(this@CourseStatisticsActivity, "正在加载", "正在获取学生信息")

        //recyclerView
        adapter = AttendanceAdapter()
        course_statistics_rv.layoutManager = LinearLayoutManager(this@CourseStatisticsActivity)
        course_statistics_rv.adapter = adapter

        //loadData
        val course = intent.getSerializableExtra("course") as Course
        var flag = intent.getStringExtra("flag")
        if (flag == null){
            flag = NORMAL
        }
        val t = StaticData.instance.teaCourseEntity.data ?: return
        val teaCourse = t.first { it.jxbID == course.jxbId }
        course_statistics_courseName.text = teaCourse.course
        course_statistics_classId.text = teaCourse.jxbID
        Service.instance
                .api
                .getStuList(StaticData.instance.token, course.jxbId, teaCourse.trid)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(object : Observer<StuListEntity> {
                    private var stuListEntity: StuListEntity? = null

                    override fun onError(e: Throwable?) {
                        progressDialog.dismiss()
                        e?.printStackTrace()
                    }

                    override fun onNext(t: StuListEntity?) {
                        log(t.toString())
                        stuListEntity = t
                    }

                    override fun onCompleted() {
                        adapter.load(stuListEntity?.data, flag)
                        progressDialog.dismiss()
                    }
                })
    }
}
