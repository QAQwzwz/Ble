package com.srtianxia.bleattendance.ui.teacher.beforeattendance;

import com.srtianxia.bleattendance.BleApplication;
import com.srtianxia.bleattendance.base.presenter.BasePresenter;
import com.srtianxia.bleattendance.base.view.BaseView;
import com.srtianxia.bleattendance.entity.AttInfoEntity;
import com.srtianxia.bleattendance.entity.TeaCourse;
import com.srtianxia.bleattendance.entity.TeaCourseEntity;
import com.srtianxia.bleattendance.http.ApiUtil;
import com.srtianxia.bleattendance.http.api.Api;
import com.srtianxia.bleattendance.utils.NetWorkUtils;
import com.srtianxia.bleattendance.utils.PreferenceManager;
import com.srtianxia.bleattendance.utils.RxSchedulersHelper;
import com.srtianxia.bleattendance.utils.database.DataBaseManager;
import com.srtianxia.bleattendance.utils.database.OnQueryTeaSuccessListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 梅梅 on 2017/2/9.
 */
public class TeaBeforeAttendancePresenter extends BasePresenter<TeaBeforeAttendancePresenter.IBeforeAttendanceView> {

    private Api mApi;

    private TeaCourseEntity teaCourseEntity;

    public TeaBeforeAttendancePresenter(IBeforeAttendanceView baseView) {
        super(baseView);
        mApi = ApiUtil.createApi(Api.class, ApiUtil.getBaseUrl());
    }

    @Override
    public IBeforeAttendanceView getViewType() {
        return getView();
    }

    /**
     * 获取课程数据
     */
    public void getData() {

        getView().loading();

        if (!NetWorkUtils.isNetworkConnected(BleApplication.getContext())){
            //如果没有网络连接
            getView().hideCourseName();
            getView().showFailureForNetWork();
            getView().loadFinish();
            return;
        }

        if (!DataBaseManager.getInstance().isTeaCourse(0)) {
            requestTeaDataForNet("0");
        } else {
            DataBaseManager.getInstance().queryTeaCourse(0, new OnQueryTeaSuccessListener() {
                @Override
                public void onSuccess(TeaCourseEntity teaCourse) {
                    requestTeaDataSuccess(teaCourse);
                }
            });
        }

        /*List<TeaCourse> teaCourseList = classFilter(teaCourseEntity);

        if (teaCourseList != null) {
            for (int i = 0; i < teaCourseList.size(); i++) {
                teaCourseList.get(i).course_class = (teaCourseList.get(i).course + " ( " + teaCourseList.get(i).scNum + ")");
            }
            return teaCourseList;
        }*/
    }

    public List<TeaCourse> classFilter(TeaCourseEntity teaCourseEntity) {
        if (teaCourseEntity != null) {
            List<TeaCourse> teaCourseList = new ArrayList<>();
            List<String> stringList = new ArrayList<>();
            for (int i = 0; i < teaCourseEntity.data.size(); i++) {
                String str = teaCourseEntity.data.get(i).course + " ( " + teaCourseEntity.data.get(i).scNum + ")";
                if (!stringList.contains(str)) {
                    stringList.add(str);
                    teaCourseList.add(teaCourseEntity.data.get(i));
                }
            }
            return teaCourseList;
        }
        return null;
    }

    public TeaCourseEntity getTeaCourseEntity() {
        if (teaCourseEntity != null)
            return teaCourseEntity;
        return null;
    }

    /**
     * 从服务器回调教师第x周课表
     * @param week
     */
    public void requestTeaDataForNet(String week) {

        String teaToken = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER,"");
        mApi.getTeaCourse(teaToken, week,trid)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(this::requestTeaDataSuccess, this::loadTeaDataFailure);

    }

    private void requestTeaDataSuccess(TeaCourseEntity teaCourseEntity) {

        this.teaCourseEntity = teaCourseEntity;

        List<TeaCourse> teaCourseList = classFilter(teaCourseEntity);

        if (teaCourseList != null) {
            for (int i = 0; i < teaCourseList.size(); i++) {
                teaCourseList.get(i).course_class = (teaCourseList.get(i).course + " ( " + teaCourseList.get(i).scNum + ")");
            }

        }

        getView().loadData(teaCourseList);
        getView().loadFinish();
    }

    public void requestAttInfoForNet(String jxbID) {
        getView().loading();

        if (!NetWorkUtils.isNetworkConnected(BleApplication.getContext())){
            getView().hideCourseName();
            getView().showFailureForNetWork();
            getView().loadFinish();
            return;
        }

        String token = PreferenceManager.getInstance().getString(PreferenceManager.SP_TOKEN_TEACHER, "");
        String trid = PreferenceManager.getInstance().getString(PreferenceManager.SP_TEACHER_NUMBER,"");
        mApi.getAttendanceInfo(token, jxbID, 0, 0, 0,trid)
                .compose(RxSchedulersHelper.io2main())
                .subscribe(this::requestAttInfoSuccess, this::loadAttInfoFailure);
    }

    private void requestAttInfoSuccess(AttInfoEntity entity) {
        getView().saveAttInfoEntity(entity);
        getView().showAttInfoFragment();
        getView().loadFinish();

    }

    private void loadTeaDataFailure(Throwable throwable) {
        getView().showTeaDataFailure();
        getView().loadFinish();
    }

    private void loadAttInfoFailure(Throwable throwable) {
        getView().showAttInfoFailure();
        getView().loadFinish();
    }

    public interface IBeforeAttendanceView extends BaseView {

        void loading();

        void loadFinish();

        void saveAttInfoEntity(AttInfoEntity entity);

        void showAttInfoFragment();

        void showAttInfoFailure();

        void showTeaDataFailure();

        void loadData(List<TeaCourse> teaCourseList);

        void showFailureForNetWork();

        void hideCourseName();
    }

}
