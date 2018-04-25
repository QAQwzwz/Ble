package com.srtianxia.bleattendance;

import android.text.TextUtils;

import com.srtianxia.bleattendance.entity.CourseEntity;
import com.srtianxia.bleattendance.utils.PreferenceManager;
import com.srtianxia.bleattendance.utils.ZiaUtilKt;

/**
 * Created by zia on 2018/4/24.
 * 临时数据存放类，本来想用LiveData的，内存泄漏就泄漏吧
 */
public enum StaticData {
    instance;
    private String token;
    private String number;
    private CourseEntity courseEntity;

    private StaticData() {
        //妈耶，token居然不会变，其实number和token不需要区分
        String flag = PreferenceManager.getInstance().getString(PreferenceManager.SP_LOGIN_FLAG, "");
        if (TextUtils.equals(flag, PreferenceManager.SP_LOGIN_FLAG_STU)) {
            number = PreferenceManager.getInstance().getString(PreferenceManager.SP_STUDENT_NUMBER, "");
            token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_STUDENT, "");
        } else if (TextUtils.equals(flag, PreferenceManager.SP_LOGIN_FLAG_TEA)) {
            number = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
            token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        }
        ZiaUtilKt.log(this.toString());
    }

    @Override
    public String toString() {
        return "StaticData{" +
                "token='" + token + '\'' +
                ", number='" + number + '\'' +
                ", courseEntity=" + courseEntity +
                '}';
    }

    public void cleanData() {
        token = null;
        number = null;
        courseEntity = null;
    }

    public String getToken() {
        return token;
    }


    public String getNumber() {
        return number;
    }


    public CourseEntity getCourseEntity() {
        return courseEntity;
    }

    public void setCourseEntity(CourseEntity courseEntity) {
        this.courseEntity = courseEntity;
    }
}
