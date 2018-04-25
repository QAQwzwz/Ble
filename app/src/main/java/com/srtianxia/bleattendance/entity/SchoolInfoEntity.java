package com.srtianxia.bleattendance.entity;

import java.util.List;

/**
 * Created by xiezh on 2018/3/11.
 */

public class SchoolInfoEntity {
    public String message;
    public String status;
    public List<String> key;
    public List<String> value;

    @Override
    public String toString() {
        return "SchoolInfoEntity{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", key=" + key +
                ", value=" + value +
                '}';
    }
}
