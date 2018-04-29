package com.srtianxia.bleattendance.http.api;

import com.srtianxia.bleattendance.entity.AttInfoEntity;
import com.srtianxia.bleattendance.entity.AttendListEntity;
import com.srtianxia.bleattendance.entity.ClassAttendEntity;
import com.srtianxia.bleattendance.entity.InstituteInfoEntity;
import com.srtianxia.bleattendance.entity.InstituteList;
import com.srtianxia.bleattendance.entity.JXBListEntity;
import com.srtianxia.bleattendance.entity.NewCourseEntity;
import com.srtianxia.bleattendance.entity.PostAttResultEntity;
import com.srtianxia.bleattendance.entity.SchoolInfoEntity;
import com.srtianxia.bleattendance.entity.StuAttInfoEntity;
import com.srtianxia.bleattendance.entity.StuListEntity;
import com.srtianxia.bleattendance.entity.StudentEntity;
import com.srtianxia.bleattendance.entity.TeaCourseEntity;
import com.srtianxia.bleattendance.entity.TeacherEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by srtianxia on 2016/7/31.
 */
public interface Api {

    // teacher 部分
    @FormUrlEncoded
    @POST("teacher/login")
    Observable<TeacherEntity> loginTeacher(@Field("trid") String trid, @Field("password") String password);


    /*// post考勤信息
    @FormUrlEncoded
    @POST("teacher/attendance")
    Observable<PostAttResultEntity> postAttendanceInfo(@Field("token") String token, @Field("jxbID") String jxbID,
                                                       @Field("hash_day") int hash_day, @Field("hash_lesson") int hash_lesson,
                                                       @Field("status") String status, @Field("week") int week);


    // 根据周数获取考勤信息 todo  这里和后端再确认下 是否可以唯一的确定一节课程
    @GET("teacher/attendance")
    Observable<AttInfoEntity> getAttendanceInfo(@Query("token") String token,
                                                @Query("jxbID") String jxbID,
                                                @Query("week") int week,
                                                @Query("hash_day") int hash_day,
                                                @Query("hash_lesson") int hash_lesson);

    // 获取一个班的人列表
    @GET("teacher/stulist")
    Observable<StuListEntity> getStuList(@Query("token") String token, @Query("jxbID") String jxbID);*/

    // student 部分
    @FormUrlEncoded
    @POST("student/login")
    Observable<StudentEntity> loginStudent(@Field("stu_code") String stuNum, @Field("password") String password);



  /*  @GET("student/course")
    Observable<NewCourseEntity> getStuCourse(@Query("token") String token, @Query("week") String week);

    @GET("teacher/course")
    Observable<TeaCourseEntity> getTeaCourse(@Query("token") String token, @Query("week") String week);


    @GET("student/attendance")
    Observable<StuAttInfoEntity> getStuAttendanceInfo(@Query("token")String token,
                                                      @Query("week")int week,
                                                      @Query("hash_day")int hash_day,
                                                      @Query("hash_lesson")int hash_lesson);*/


    // post考勤信息
    @FormUrlEncoded
    @POST("teacher/attendance")
    Observable<PostAttResultEntity> postAttendanceInfo(@Field("token") String token, @Field("jxbID") String jxbID,
                                                       @Field("hash_day") int hash_day, @Field("hash_lesson") int hash_lesson,
                                                       @Field("status") String status, @Field("week") int week, @Query("trid") String trid);


    // 根据周数获取考勤信息 todo  这里和后端再确认下 是否可以唯一的确定一节课程
    @GET("teacher/attendance")
    Observable<AttInfoEntity> getAttendanceInfo(@Query("token") String token,
                                                @Query("jxbID") String jxbID,
                                                @Query("week") int week,
                                                @Query("hash_day") int hash_day,
                                                @Query("hash_lesson") int hash_lesson, @Query("trid") String trid);

    //获取某个课程的考勤
    @GET("teacher/attendance")
    Observable<AttInfoEntity> getCourseAttendance(@Query("token") String token,
                                                  @Query("jxbID") String jxbID,
                                                  @Query("week") int week);

    // 获取一个班的人列表
    @GET("teacher/stulist")
    Observable<StuListEntity> getStuList(@Query("token") String token,
                                         @Query("jxbID") String jxbID,
                                         @Query("trid") String trid);


    @GET("teacher/course")
    Observable<TeaCourseEntity> getTeaCourse(@Query("token") String token,
                                             @Query("week") String week,
                                             @Query("trid") String trid);

    @GET("student/course")
    Observable<NewCourseEntity> getStuCourse(@Query("token") String token,
                                             @Query("week") String week,
                                             @Query("stu_code") Integer stuNum);

    @GET("student/attendance")
    Observable<StuAttInfoEntity> getStuAttendanceInfo(@Query("token") String token,
                                                      @Query("week") int week,
                                                      @Query("hash_day") int hash_day,
                                                      @Query("hash_lesson") int hash_lesson,
                                                      @Query("stu_code") Integer stuNum);

    //data分析部分
    @GET("teacher/dataanalysis/course")
    Observable<JXBListEntity> getJXBByTrid(@Query("token") String token, @Query("trid") String trid);

    @GET("teacher/dataanalysis/attendance")
    Observable<AttendListEntity> getAttenceListByJXBID(@Query("token") String token,
                                                       @Query("jxbID") String jxbID,
                                                       @Query("trid") String trid);

    @GET("teacher/dataanalysis/pushesclass")
    Observable<ClassAttendEntity> getPushesClass(@Query("token") String token,
                                                 @Query("trid") String trid);

    @GET("teacher/dataanalysis/classStudentAttendence")
    Observable<AttendListEntity> getClassAttendance(@Query("token") String token,
                                                    @Query("class") String clazz,
                                                    @Query("trid") String trid);

    @GET("teacher/dataanalysis/instituteAll")
    Observable<InstituteList> getInstituteList(@Query("token") String token,
                                               @Query("trid") String trid);

    @GET("teacher/dataanalysis/instituteInfo")
    Observable<InstituteInfoEntity> getInstituteInfo(@Query("token") String token,
                                                     @Query("code") String code,
                                                     @Query("trid") String trid);
/*
    Route::get('/schoolYear','TeacherController@dataGetSchoolYear');//获取学校所有年的缺勤信息总记录
    Route::get('/schoolGrade','TeacherController@dataGetCurrentYearAttendance');//获录在校年级的缺勤信息
    Route::get('/schoolType','TeacherController@dataGetTypeAttendance');//获录在校年级的缺勤信息
    Route::get('/schoolMonth','TeacherController@dataGetMonthAttendance');//获录在校年级的缺勤信息
    Route::get('/schoolInstitute','TeacherController@dataGetSchoolInstitute');//获取学院的缺勤排名信息*/

    @GET("teacher/dataanalysis/schoolYear")
    Observable<SchoolInfoEntity> getSchoolYear(@Query("token") String token,
                                               @Query("trid") String trid);

    @GET("teacher/dataanalysis/schoolGrade")
    Observable<SchoolInfoEntity> getSchoolGrade(@Query("token") String token,
                                                @Query("trid") String trid);

    @GET("teacher/dataanalysis/schoolType")
    Observable<SchoolInfoEntity> getSchoolType(@Query("token") String token,
                                               @Query("trid") String trid);

    @GET("teacher/dataanalysis/schoolMonth")
    Observable<SchoolInfoEntity> getSchoolMonth(@Query("token") String token,
                                                @Query("trid") String trid);

    @GET("teacher/dataanalysis/schoolInstitute")
    Observable<SchoolInfoEntity> getSchoolInstitute(@Query("token") String token,
                                                    @Query("trid") String trid);

}
