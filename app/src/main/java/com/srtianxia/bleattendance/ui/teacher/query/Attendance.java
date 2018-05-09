package com.srtianxia.bleattendance.ui.teacher.query;

public class Attendance {
    private String name, stuNum, num;
    private int classId, imageId;

    public Attendance(String name, String stuNum, String num) {
        // this.imageId = imageId;
        this.num = num;
        this.stuNum = stuNum;
        //this.classId = classId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getClassId() {
        return classId;
    }

    public String getStuNum() {
        return stuNum;
    }

    public String getNum() {
        return num;
    }

    public int getImageId() {
        return imageId;
    }

}
