package com.srtianxia.bleattendance.entity;

import java.util.List;
import java.util.Map;

/**
 * Created by xiezh on 2018/3/9.
 */

public class InstituteList {

    public String message;
    public String status;
    public List<InstituteList.Institute> data;

    public class Institute {
        public String name;
        public String code;
        public List<Major> major;
    }

    public class Major {
        public String majorCode;
        public String majorName;
    }

}
