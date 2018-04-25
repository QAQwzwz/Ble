package com.srtianxia.bleattendance.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2017/1/27.
 */
public class TeaCourseEntity{

    public int id;
    public int status;
    public String message;
    public String version;
    public List<TeaCourse> data;
    public int nowWeek;

    @Override
    public String toString() {
        return "TeaCourseEntity{" +
                "id=" + id +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", version='" + version + '\'' +
                ", data=" + data +
                ", nowWeek=" + nowWeek +
                '}';
    }
}
