package com.srtianxia.bleattendance.entity;

import javax.annotation.Generated;

import com.google.gson.annotations.SerializedName;

import java.util.List;
/**
 * Created by xiezh on 2018/3/6.
 */


/**
 * Created by xiezh on 2018/3/7.
 */
@Generated("net.hexar.json2pojo")
public class ClassAttendEntity {

    public String message;
    public String status;
    public List<Clazz> data;

    public class Clazz {
        @SerializedName("class")
        public String class_;
        @SerializedName("COUNT(*)")
        public int count;
    }

}
