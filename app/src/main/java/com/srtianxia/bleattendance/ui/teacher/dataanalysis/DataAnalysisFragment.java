package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.base.view.BaseFragment;

import butterknife.BindView;

/**
 * Created by xiezh on 2018/3/3.
 */

public class DataAnalysisFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.data_menu_one)
    TextView menuOne;
    @BindView(R.id.data_menu_two)
    TextView menuTwo;
    @BindView(R.id.data_menu_thr)
    TextView menuThr;
    @BindView(R.id.data_menu_for)
    TextView menuFor;

    @Override
    protected void initView() {
        menuOne.setOnClickListener(this);
        menuTwo.setOnClickListener(this);
        menuThr.setOnClickListener(this);
        menuFor.setOnClickListener(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tea_before_dataanalysis;
    }

    public static DataAnalysisFragment newInstance() {
        Bundle bundle = new Bundle();
        DataAnalysisFragment fragment = new DataAnalysisFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onClick(View v) {
        int choose = 0;
        switch (v.getId()){
            case R.id.data_menu_one:
                choose =1;
                break;
            case R.id.data_menu_two:
                choose =2;
                break;
            case R.id.data_menu_thr:
                choose =3;
                break;
            case R.id.data_menu_for:
                choose =4;
                break;
        }

        Intent intent = new Intent(this.getContext(),ShowDataActivity.class);
        intent.putExtra("choose",choose);
        startActivity(intent);

    }
}
