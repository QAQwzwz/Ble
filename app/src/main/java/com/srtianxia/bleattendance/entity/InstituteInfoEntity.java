package com.srtianxia.bleattendance.entity;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Generated;


/**
 * Created by xiezh on 2018/3/9.
 */

@Generated("net.hexar.json2pojo")
public class InstituteInfoEntity {

    public String message;
    public String status;

    public List<String> classKey;
    public List<String> classValue;
    public List<String> gradeKey;
    public List<String> gradeValue;
    public List<String> majorKey;
    public List<String> majorvalue;
    public List<String> courseKey;
    public List<String> coursevalue;
    public List<String> statusKey;
    public List<String> statusvalue;
    public List<String> tridKey;
    public List<String> tridvalue;

    public List<String> monthKey;
    public List<String> monthValue;

    public List<String> yearKey;
    public List<String> yearValue;


    @Override
    public String toString() {
        return "InstituteInfoEntity{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", classKey=" + classKey +
                ", classValue=" + classValue +
                ", gradeKey=" + gradeKey +
                ", gradeValue=" + gradeValue +
                ", majorKey=" + majorKey +
                ", majorvalue=" + majorvalue +
                ", courseKey=" + courseKey +
                ", coursevalue=" + coursevalue +
                ", statusKey=" + statusKey +
                ", statusvalue=" + statusvalue +
                ", tridKey=" + tridKey +
                ", tridvalue=" + tridvalue +
                ", monthKey=" + monthKey +
                ", monthValue=" + monthValue +
                ", yearKey=" + yearKey +
                ", yearValue=" + yearValue +
                '}';
    }
}
