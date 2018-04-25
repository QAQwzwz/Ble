package com.srtianxia.bleattendance.entity;

import java.util.List;

/**
 * Created by xiezh on 2018/3/5
 */

public class JXBListEntity {
    public String status;
    public String message;
    public List<JXB> data;

    @Override
    public String toString() {
        return "JXBListEntity{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class JXB{
        public String jxbID;
        public String year;
        public String teacher;
        public String type;
        public String classroom;
        public String course;
        public String day;

        @Override
        public String toString() {
            return "JXB{" +
                    "jxbID='" + jxbID + '\'' +
                    ", year='" + year + '\'' +
                    ", teacher='" + teacher + '\'' +
                    ", type='" + type + '\'' +
                    ", classroom='" + classroom + '\'' +
                    ", course='" + course + '\'' +
                    ", day='" + day + '\'' +
                    '}';
        }
    }

}
