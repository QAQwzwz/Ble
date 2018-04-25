package com.srtianxia.bleattendance.entity;

import android.support.annotation.NonNull;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;
/**
 * Created by xiezh on 2018/3/6.
 */

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class AttendListEntity {
    public String message;
    public String status;
    public List<Attend> data;

    @Override
    public String toString() {
        return "AttendListEntity{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public class Attend{
        public String stuNum;
        public String stuName;
        @SerializedName("course")
        public String course;

        @SerializedName("grade")
        public String grade;

        @SerializedName("week")
        public String week;

        @SerializedName("class")
        public String class_;
        @SerializedName("")
        public String status;//缺勤种类

        @SerializedName("created_at")
        public String date;

        public Integer count = 1;

        @Override
        public String toString() {
            return "Attend{" +
                    "stuNum='" + stuNum + '\'' +
                    ", stuName='" + stuName + '\'' +
                    ", course='" + course + '\'' +
                    ", grade='" + grade + '\'' +
                    ", week='" + week + '\'' +
                    ", class_='" + class_ + '\'' +
                    ", status='" + status + '\'' +
                    ", date='" + date + '\'' +
                    ", count=" + count +
                    '}';
        }
    }
}
