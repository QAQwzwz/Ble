package com.srtianxia.bleattendance.ui.teacher.dataanalysis.attChoose

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.StaticData
import com.srtianxia.bleattendance.entity.TeaCourseEntity
import com.srtianxia.bleattendance.http.Service
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics.CourseStatisticsActivity
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.tjStatistics.TjActivity
import com.srtianxia.bleattendance.utils.RxSchedulersHelper
import com.srtianxia.bleattendance.utils.log
import com.srtianxia.bleattendance.utils.toast
import kotlinx.android.synthetic.main.activity_att_choose.*
import kotlinx.android.synthetic.main.appbar_layout.*
import rx.Observer

class AttChooseActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.navigationBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        setContentView(R.layout.activity_att_choose)
        val flag = intent.getIntExtra("flag", -1)
        tv_toolbar_title.text = "选择课程"

        //todo 假定周数是8
        val week = "8"

        progressDialog = ProgressDialog.show(this@AttChooseActivity, "正在加载", "正在获取课程信息")

        adapter = Adapter()
        adapter.setOnItemClicked(object : Adapter.OnItemClicked {
            override fun onClick(course: Course, holder: Adapter.ViewHolder) {
                if (flag == -1) return
                holder.room.text = course.room
                holder.day.text = course.day
                holder.name.text = course.name
                holder.itemView.setOnClickListener {
                    if (flag == 0) {
                        val intent = Intent(this@AttChooseActivity, TjActivity::class.java)
                        intent.putExtra("jxbId", course.jxbId)
                        startActivity(intent)
                    } else if (flag == 1) {
                        val intent = Intent(this@AttChooseActivity, CourseStatisticsActivity::class.java)
                        intent.putExtra("jxbId", course.jxbId)
                        startActivity(intent)
                    }

                }


            }
        })

        att_statistics_rv.layoutManager = LinearLayoutManager(this@AttChooseActivity)
        att_statistics_rv.adapter = adapter

        Service.instance.api
                .getTeaCourse(StaticData.instance.token, week, StaticData.instance.number)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(object : Observer<TeaCourseEntity> {
                    private val list = ArrayList<Course>()
                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                        progressDialog.dismiss()
                        toast(this@AttChooseActivity, "获取失败，请打检查网络")
                    }

                    override fun onNext(t: TeaCourseEntity?) {
                        StaticData.instance.teaCourseEntity = t
                        t?.data?.forEach {
                            val name = it.course
                            val room = it.classroom
                            var day = it.day
                            val jxbId = it.jxbID
                            val iterator = list.iterator()
                            while (iterator.hasNext()) {
                                val old = iterator.next()
                                if (jxbId == old.jxbId) {
                                    if (!old.day.contains(day)) {
                                        day = "${old.day},$day"
                                        iterator.remove()
                                    }
                                }
                            }
                            list.add(Course(name, day, room, jxbId))
                        }
                    }

                    override fun onCompleted() {
                        adapter.update(list)
                        progressDialog.dismiss()
                    }

                })
    }
}

internal class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val teaCourseList = ArrayList<Course>()
    private var onItemClicked: OnItemClicked? = null

    interface OnItemClicked {
        fun onClick(course: Course, holder: ViewHolder)
    }

    fun setOnItemClicked(onItemClicked: OnItemClicked) {
        this.onItemClicked = onItemClicked
    }

    fun update(teaCourseList: ArrayList<Course>) {
        this.teaCourseList.clear()
        this.teaCourseList.addAll(teaCourseList)
        log(this.teaCourseList.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onItemClicked?.onClick(teaCourseList[position], holder)
    }

    override fun getItemCount(): Int {
        return teaCourseList.size
    }

    internal inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView
        val day: TextView
        val room: TextView

        init {
            this.name = itemView.findViewById(R.id.item_course_name)
            this.day = itemView.findViewById(R.id.item_course_day)
            this.room = itemView.findViewById(R.id.item_course_classroom)
        }
    }
}

data class Course(val name: String, val day: String, val room: String, val jxbId: String)