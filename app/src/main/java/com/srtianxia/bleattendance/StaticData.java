package com.srtianxia.bleattendance;

import com.srtianxia.bleattendance.entity.TeaCourseEntity;
import com.srtianxia.bleattendance.utils.PreferenceManager;

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
    }

    public String getToken() {
        return token;
    }

    public String getNumber() {
        return number;
    }

    public void setToken(String token) {
        this.token = token;
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
        return teaCourseEntity;
    }

    public void setTeaCourseEntity(TeaCourseEntity teaCourseEntity) {
        this.teaCourseEntity = teaCourseEntity;
        this.nowWeek = teaCourseEntity.nowWeek;
    }
}
