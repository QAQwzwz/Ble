package com.srtianxia.bleattendance.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2017/1/30.
 */
public class TeaCourse{

    public String trid;
    public String scNum;
    public String jxbID;
    public String hash_day;
    public String hash_lesson;
    public String begin_lesson;
    public String day;
    public String lesson;
    public String course;
    public String teacher;
    public String type;
    public String classroom;
    public String rawWeek;
    public String period;
    public List<Integer> week;
//    public int priod;
    //操作系统（04011403）
    public String course_class;

//    public String toString(){
//        return course + "@" + classroom;
//    }

    @Override
    public String toString() {
        return "TeaCourse{" +
                "trid='" + trid + '\'' +
                ", scNum='" + scNum + '\'' +
                ", jxbID='" + jxbID + '\'' +
                ", hash_day='" + hash_day + '\'' +
                ", hash_lesson='" + hash_lesson + '\'' +
                ", begin_lesson='" + begin_lesson + '\'' +
                ", day='" + day + '\'' +
                ", lesson='" + lesson + '\'' +
                ", course='" + course + '\'' +
                ", teacher='" + teacher + '\'' +
                ", type='" + type + '\'' +
                ", classroom='" + classroom + '\'' +
                ", rawWeek='" + rawWeek + '\'' +
                ", period='" + period + '\'' +
                ", week=" + week +
                ", course_class='" + course_class + '\'' +
                '}';
    }
}
