package com.srtianxia.bleattendance.entity;

import java.util.List;

/**
 * Created by srtianxia on 2017/1/28.
 */

public class AttInfoEntity{
    public String status;
    public String message;
    public int data_num;
    public List<AttInfo> data;

    @Override
    public String toString() {
        return "AttInfoEntity{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data_num=" + data_num +
                ", data=" + data +
                '}';
    }

    public static class AttInfo implements Comparable<AttInfo>{
        public String stuNum;
        public String stuName;
//        public String status;
        public List<String> week;
        public List<String> hash_day;
        public List<String> hash_lesson;
        public List<String> status;
        public Integer absence;

        @Override
        public int compareTo(AttInfo attInfo) {
            return attInfo.absence.compareTo(absence);
        }

        @Override
        public String toString() {
            return "AttInfo{" +
                    "stuNum='" + stuNum + '\'' +
                    ", stuName='" + stuName + '\'' +
                    ", week=" + week +
                    ", hash_day=" + hash_day +
                    ", hash_lesson=" + hash_lesson +
                    ", status=" + status +
                    ", absence=" + absence +
                    '}';
        }
    }
}
