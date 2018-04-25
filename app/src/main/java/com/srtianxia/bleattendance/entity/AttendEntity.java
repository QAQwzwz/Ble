package com.srtianxia.bleattendance.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2016/9/13.
 */
public class AttendEntity {
    public Integer status;
    public List<Data> data;
    public String msg;

    @Override
    public String toString() {
        return "AttendEntity{" +
                "status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static class Data{
        public Integer absenceNum;
        public List<Absences> absences;
        public Integer actual;
        public Integer all;
        public String course;
        public String lesson;
        public Integer nowWeek;
        public String type;

        @Override
        public String toString() {
            return "Data{" +
                    "absenceNum=" + absenceNum +
                    ", absences=" + absences +
                    ", actual=" + actual +
                    ", all=" + all +
                    ", course='" + course + '\'' +
                    ", lesson='" + lesson + '\'' +
                    ", nowWeek=" + nowWeek +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    public static class Absences{
        public String stuName;
        public Integer stuNum;

        @Override
        public String toString() {
            return "Absences{" +
                    "stuName='" + stuName + '\'' +
                    ", stuNum=" + stuNum +
                    '}';
        }
    }
}
