package com.srtianxia.bleattendance.ui.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.srtianxia.bleattendance.StaticData
import com.srtianxia.bleattendance.entity.StudentEntity
import com.srtianxia.bleattendance.entity.TeacherEntity
import com.srtianxia.bleattendance.http.Service
import com.srtianxia.bleattendance.ui.MainActivity
import com.srtianxia.bleattendance.ui.student.home.StudentHomeActivity
import com.srtianxia.bleattendance.ui.teacher.home.TeacherHomeActivity
import com.srtianxia.bleattendance.utils.PreferenceManager
import com.srtianxia.bleattendance.utils.RxSchedulersHelper
import com.srtianxia.bleattendance.utils.log
import com.srtianxia.bleattendance.utils.toast
import rx.Observer

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        log(StaticData.instance.toString())
        goPage()
    }

    private fun goPage() {
        //如果没有保存账号密码，直接进入登录界面
        if (StaticData.instance.number == "" || StaticData.instance.password == "") {
            startActivity(Intent(this@StartActivity, MainActivity::class.java))
            finish()
            return
        }
        //有账号密码，登录，刷新token
        val flag = PreferenceManager.getInstance().getString(PreferenceManager.SP_LOGIN_FLAG, "")
        if (flag == PreferenceManager.SP_LOGIN_FLAG_STU) {
            Service.instance.api
                    .loginStudent(StaticData.instance.number, StaticData.instance.password)
                    .compose(RxSchedulersHelper.io2main())
                    .subscribe(object : Observer<StudentEntity> {
                        override fun onError(e: Throwable?) {
                            if (e != null) {
                                e.printStackTrace()
                                toast(this@StartActivity, e.toString())
                            }
                            startActivity(Intent(this@StartActivity, MainActivity::class.java))
                            finish()
                            return
                        }

                        override fun onNext(t: StudentEntity?) {
                            if (t?.message == "success") {
                                StaticData.instance.token = t.data
                                startActivity(Intent(this@StartActivity, StudentHomeActivity::class.java))
                            } else {
                                toast(this@StartActivity, "请重新输入账号密码")
                                startActivity(Intent(this@StartActivity, MainActivity::class.java))
                            }
                            finish()
                            return
                        }

                        override fun onCompleted() {
                        }

                    })
        } else if (flag == PreferenceManager.SP_LOGIN_FLAG_TEA) {
            Service.instance.api
                    .loginTeacher(StaticData.instance.number, StaticData.instance.password)
                    .compose(RxSchedulersHelper.io2main())
                    .subscribe(object : Observer<TeacherEntity> {
                        override fun onError(e: Throwable?) {
                            if (e != null) {
                                e.printStackTrace()
                                toast(this@StartActivity, e.toString())
                            }
                            startActivity(Intent(this@StartActivity, MainActivity::class.java))
                            finish()
                            return
                        }

                        override fun onNext(t: TeacherEntity?) {
                            if (t?.message == "success") {
                                StaticData.instance.token = t.data
                                startActivity(Intent(this@StartActivity, TeacherHomeActivity::class.java))
                            } else {
                                toast(this@StartActivity, "请重新输入账号密码")
                                startActivity(Intent(this@StartActivity, MainActivity::class.java))
                            }
                            finish()
                            return
                        }

                        override fun onCompleted() {
                        }

                    })
        }
    }

    /**
     * 蓝牙请求
     * 登录时不请求，在连接时请求
     */
//    private fun openBlueTooth() {
//        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//        enableBtIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(enableBtIntent)
//    }
//
//    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == Constant.REQUEST_CODE_BLUE_OPEN) {
//            if (resultCode == Activity.RESULT_OK) {
//                ToastUtil.show(this, "蓝牙已开启", true)
//                log("ok")
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                ToastUtil.show(this, "必须使用蓝牙", true)
//                log("cancel")
//            }else{
//                log("else")
//            }
//        }
//    }
}
