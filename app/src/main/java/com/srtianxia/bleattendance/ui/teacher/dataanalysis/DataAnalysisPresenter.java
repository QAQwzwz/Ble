package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.util.Log;

import com.srtianxia.bleattendance.base.presenter.BasePresenter;
import com.srtianxia.bleattendance.base.view.BaseView;
import com.srtianxia.bleattendance.entity.AttendListEntity;
import com.srtianxia.bleattendance.entity.ClassAttendEntity;
import com.srtianxia.bleattendance.entity.InstituteInfoEntity;
import com.srtianxia.bleattendance.entity.InstituteList;
import com.srtianxia.bleattendance.entity.JXBListEntity;
import com.srtianxia.bleattendance.entity.SchoolInfoEntity;
import com.srtianxia.bleattendance.http.ApiUtil;
import com.srtianxia.bleattendance.http.api.Api;
import com.srtianxia.bleattendance.utils.PreferenceManager;
import com.srtianxia.bleattendance.utils.RxSchedulersHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.functions.Func1;

/**
 * Created by xiezh on 2018/3/6.
 */

public class DataAnalysisPresenter extends BasePresenter<DataAnalysisPresenter.DataConditionView> {
    private Api mApi;
    private static final String TAG = "DataAnalysisPresenter:";

    public DataAnalysisPresenter(DataConditionView baseView) {
        super(baseView);
        mApi = ApiUtil.createApi(Api.class, ApiUtil.getBaseUrl());
    }

    /**
     * 获取教师当年所教的班级
     */
    public void getJXBID() {
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        mApi.getJXBByTrid(token, trid)
                .map(e -> JXBListEntity2ListString(e))
                .compose(RxSchedulersHelper.io2main())
                .subscribe(list -> getView().loadJxbInfo(list));
    }

    public void getAttenceListByJxbID(String jxbID) {
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        mApi.getAttenceListByJXBID(token, jxbID, trid)
                .map((entity) -> formatData(entity))
                .compose(RxSchedulersHelper.io2main())
                .subscribe(list -> getView().loadAttendInfo(list));
    }

    public void getPushesClass() {

        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        mApi.getPushesClass(token, trid)
                .map(e -> {
                    return e.data;
                })
                .compose(RxSchedulersHelper.io2main())
                .subscribe(list -> getView().loadPushesClassInfo(list));
    }


    public void loadAttendInfoByClass(String class_) {
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        mApi.getClassAttendance(token, class_, trid)
                .map((entity) -> formatData2AttendList(entity))
                .compose(RxSchedulersHelper.io2main())
                .subscribe(list -> getView().loadCLassAttendandce(list));

    }

    public void getInstituteList() {
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        mApi.getInstituteList(token, trid)
                .map(entity -> entity.data)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(list -> getView().loadInstitute(list));
    }

    public void getInstituteInfo(InstituteList.Institute institute) {
        while (institute.code.length() < 2) {
            institute.code = "0" + institute.code;
        }
        Log.i(TAG,institute.code);
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        mApi.getInstituteInfo(token, institute.code, trid)
                .map(entity -> entity)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(info -> getView().loadInstituteInfo(info));
    }


    public void getSchoolInfo() {

        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER, "");
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        mApi.getSchoolYear(token,trid)
                .map(entity -> entity)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(entity -> getView().loadSchoolInfo(entity,1));

        mApi.getSchoolGrade(token,trid)
                .map(entity -> entity)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(entity -> getView().loadSchoolInfo(entity,2));

        mApi.getSchoolType(token,trid)
                .map(entity -> entity)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(entity -> getView().loadSchoolInfo(entity,3));

        mApi.getSchoolMonth(token,trid)
                .map(entity -> entity)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(entity -> getView().loadSchoolInfo(entity,4));

        mApi.getSchoolInstitute(token,trid)
                .map(entity -> entity)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(entity -> getView().loadSchoolInfo(entity,5));
    }


 /*   public void getAllStuList(Course course) {
        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER,"");
        mApi.getStuList(token, course.jxbID,trid)
                .map((entity) -> stuListEntity2StringList(entity))
                .compose(RxSchedulersHelper.io2main())
                .subscribe(numberList -> getView().loadAllAttendanceInfoSuccess(numberList), throwable -> Logger.d(throwable));
    }*/

    public List<JXBListEntity.JXB> JXBListEntity2ListString(JXBListEntity entity) {
        return entity.data;
    }

    public List<Object> formatData(AttendListEntity entity) {
        int[] array = new int[20];
        for (AttendListEntity.Attend attend :
                entity.data) {
            Log.i(TAG, "接收到数据" + attend.stuName);
            if (Integer.parseInt(attend.week) >= 0 && Integer.parseInt(attend.week) < 20)
                array[Integer.parseInt(attend.week)]++;
            //Log.i(TAG, attend.stuName);

        }

        List<AttendListEntity.Attend> attends = formatData2AttendList(entity);
        List<Object> list = new ArrayList<>();
        list.add(array);
        list.add(attends);
        return list;
    }

    public List<AttendListEntity.Attend> formatData2AttendList(AttendListEntity entity) {
        Map<String, AttendListEntity.Attend> map = new HashMap();
        for (AttendListEntity.Attend attend :
                entity.data) {
            AttendListEntity.Attend newAttend = map.get(attend.stuNum + "-" + attend.stuName);
            if (newAttend == null) {
                newAttend = entity.new Attend();
                newAttend.stuName = attend.stuName;
                newAttend.stuNum = attend.stuNum;
                newAttend.week = attend.week;
                newAttend.class_ = attend.class_;
                newAttend.grade = attend.grade;
                newAttend.course = attend.course;
                newAttend.status = attend.status;
                newAttend.grade = attend.grade;

                map.put(attend.stuNum + "-" + attend.stuName, newAttend);
            } else {
                newAttend.count++;
                newAttend.week += "," + attend.week;
                map.put(attend.stuNum + "-" + attend.stuName, newAttend);
            }
        }
        List<AttendListEntity.Attend> attends = new ArrayList<>(map.values());

        Collections.sort(attends, new Comparator<AttendListEntity.Attend>() {
            @Override
            public int compare(AttendListEntity.Attend o1, AttendListEntity.Attend o2) {
                if (o1.count > o2.count) {
                    return -1;
                } else if (o1.count == o2.count) {
                    if (Integer.parseInt(o1.stuNum) > Integer.parseInt(o2.stuNum)) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return 1;
                }

            }
        });
        return attends;
    }

    /*public InstituteInfoEntity.InstituteInfo formatData2InstituteInfo(String str){
        InstituteInfoEntity.InstituteInfo instituteInfo = new InstituteInfoEntity().new InstituteInfo();

       return null;
    }*/

    public ShowDataActivity getViewType() {
        return (ShowDataActivity) getView();
    }


    public interface DataConditionView extends BaseView {
        void loadJxbInfo(List<JXBListEntity.JXB> data);

        void loadAttendInfo(List<Object> list);

        void loadPushesClassInfo(List<ClassAttendEntity.Clazz> list);

        void loadCLassAttendandce(List<AttendListEntity.Attend> list);

        void loadInstitute(List<InstituteList.Institute> list);

        void loadInstituteInfo(InstituteInfoEntity instituteInfo);

        void loadSchoolInfo(SchoolInfoEntity entity,int type);

    }

}
