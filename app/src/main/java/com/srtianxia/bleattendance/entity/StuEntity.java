package com.srtianxia.bleattendance.entity;

/**
 * Created by srtianxia on 2016/7/31.
 */
public class StuEntity {
    public String sessionId;
    public Integer status;
    public Student data;
    public String msg;

    @Override
    public String toString() {
        return "StuEntity{" +
                "sessionId='" + sessionId + '\'' +
                ", status=" + status +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static class Student {
        public Integer sid;
        public String stuAcad;
        public String stuMajor;
        public String stuName;
        public Integer stuNum;
        public String stuPass;
        public String stuSex;

        @Override
        public String toString() {
            return "Student{" +
                    "sid=" + sid +
                    ", stuAcad='" + stuAcad + '\'' +
                    ", stuMajor='" + stuMajor + '\'' +
                    ", stuName='" + stuName + '\'' +
                    ", stuNum=" + stuNum +
                    ", stuPass='" + stuPass + '\'' +
                    ", stuSex='" + stuSex + '\'' +
                    '}';
        }
    }
}
