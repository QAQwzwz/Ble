package com.srtianxia.bleattendance.entity;

import java.util.List;

/**
 * Created by 梅梅 on 2017/2/11.
 */
public class StuAttInfoEntity {

    public int status;
    public String message;
    public String stuNum;
    public String stuName;
    public Statistics statistics;
    public List<Data> data;

    @Override
    public String toString() {
        return "StuAttInfoEntity{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", stuNum='" + stuNum + '\'' +
                ", stuName='" + stuName + '\'' +
                ", statistics=" + statistics +
                ", data=" + data +
                '}';
    }

    public static class Statistics{
        public StatisInfo leave;
        public StatisInfo absence;
        public StatisInfo late;
        public StatisInfo leave_early;

        @Override
        public String toString() {
            return "Statistics{" +
                    "leave=" + leave +
                    ", absence=" + absence +
                    ", late=" + late +
                    ", leave_early=" + leave_early +
                    '}';
        }
    }

    public static class StatisInfo{
        public int num;
        public List<Data> data;
    }

    public static class Data{
        public String week;
        public String hash_day;
        public String hash_lesson;
        public String status;
        public String jxbID;
        public String course;

        @Override
        public String toString() {
            return "Data{" +
                    "week='" + week + '\'' +
                    ", hash_day='" + hash_day + '\'' +
                    ", hash_lesson='" + hash_lesson + '\'' +
                    ", status='" + status + '\'' +
                    ", jxbID='" + jxbID + '\'' +
                    ", course='" + course + '\'' +
                    '}';
        }
    }

    public static class ShowData{
        public String jxbID;
        public int att_num;
        public String course;
    }

}
