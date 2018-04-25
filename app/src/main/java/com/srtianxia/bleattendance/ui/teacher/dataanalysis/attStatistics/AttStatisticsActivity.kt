package com.srtianxia.bleattendance.ui.teacher.dataanalysis.attStatistics

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.srtianxia.bleattendance.R
import com.srtianxia.bleattendance.StaticData
import com.srtianxia.bleattendance.entity.ClassAttendEntity
import com.srtianxia.bleattendance.entity.InstituteList
import com.srtianxia.bleattendance.entity.JXBListEntity
import com.srtianxia.bleattendance.entity.TeaCourseEntity
import com.srtianxia.bleattendance.http.ApiFactory
import com.srtianxia.bleattendance.http.ApiUtil
import com.srtianxia.bleattendance.http.Service
import com.srtianxia.bleattendance.http.api.Api
import com.srtianxia.bleattendance.utils.RxSchedulersHelper
import com.srtianxia.bleattendance.utils.log
import rx.Observer

/**
 * 考勤统计
 */
class AttStatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_att_statistics)
        log("考勤统计")
        Service.instance.api
                .getJXBByTrid(StaticData.instance.token, StaticData.instance.number)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(object : Observer<JXBListEntity> {
                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                    override fun onNext(t: JXBListEntity?) {
                        log(t.toString())
                    }

                    override fun onCompleted() {
                    }

                })
        Service.instance.api
                .getInstituteList(StaticData.instance.token,StaticData.instance.number)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(object : Observer<InstituteList> {
                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                    override fun onNext(t: InstituteList?) {
                        log(t.toString())
                    }

                    override fun onCompleted() {
                    }

                })
        Service.instance.api
                .getTeaCourse(StaticData.instance.token,"8",StaticData.instance.number)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(object : Observer<TeaCourseEntity> {
                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                    override fun onNext(t: TeaCourseEntity?) {
                        log(t.toString())
                    }

                    override fun onCompleted() {
                    }

                })
        Service.instance.api.getPushesClass(StaticData.instance.token,StaticData.instance.number)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(object : Observer<ClassAttendEntity> {
                    override fun onError(e: Throwable?) {
                        e?.printStackTrace()
                    }

                    override fun onNext(t: ClassAttendEntity?) {
                        log(t.toString())
                    }

                    override fun onCompleted() {
                    }

                })
    }
}
