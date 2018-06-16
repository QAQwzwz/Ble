package com.srtianxia.bleattendance;

import android.util.Log;

import com.srtianxia.bleattendance.entity.TeaCourseEntity;
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
    private String password;
    private int nowWeek;//当前周数
    private TeaCourseEntity teaCourseEntity;

    StaticData() {
        number = PreferenceManager.getInstance().getString(PreferenceManager.NUMBER, "");
        password = PreferenceManager.getInstance().getString(PreferenceManager.PASSWORD, "");

    }

    @Override
    public String toString() {
        return "StaticData{" +
                "token='" + token + '\'' +
                ", number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", courseEntity=" + teaCourseEntity +
                '}';
    }

    public void cleanData() {
        token = null;
        number = null;
        teaCourseEntity = null;
        PreferenceManager.getInstance().setString(PreferenceManager.NUMBER, "");
        PreferenceManager.getInstance().setString(PreferenceManager.PASSWORD, "");
    }

    public String getToken() {
        return token;
    }

    public String getNumber() {
        return number;
    }

    public void setToken(String token) {
        this.token = token;
        ZiaUtilKt.log(this.toString());
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNowWeek() {
        return nowWeek;
    }

    public void setNowWeek(int nowWeek) {
        this.nowWeek = nowWeek;
    }

    public TeaCourseEntity getTeaCourseEntity() {
        ZiaUtilKt.log(teaCourseEntity.toString());
        return teaCourseEntity;
    }

    public void setTeaCourseEntity(TeaCourseEntity teaCourseEntity) {
        this.teaCourseEntity = teaCourseEntity;
        this.nowWeek = teaCourseEntity.nowWeek;
    }
}
