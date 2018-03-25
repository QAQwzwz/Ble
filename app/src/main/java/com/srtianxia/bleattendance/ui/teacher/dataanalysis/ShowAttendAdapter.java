package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.entity.AttendListEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezh on 2018/3/7.
 */

public class ShowAttendAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ShowAttendAdapter";
    private List<AttendListEntity.Attend> dataList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowAttendAdapter.AttendInfoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.data_analysis_show_attendinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG,"绑定viewHolder");
        ShowAttendAdapter.AttendInfoViewHolder viewHolder = (ShowAttendAdapter.AttendInfoViewHolder) holder;
        viewHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG,"返回数量 ： " +dataList.size());
        return dataList.size();
    }

    public void loadData(List<AttendListEntity.Attend> dataList) {
        Log.i(TAG,"load数据");
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        dataList.clear();
    }

    public class AttendInfoViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mNum;
        TextView mWeek;
        TextView mClass;

        public AttendInfoViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG,"寻找view");
            mName = (TextView) itemView.findViewById(R.id.show_name);
            mNum = (TextView) itemView.findViewById(R.id.show_num);
            mWeek = (TextView) itemView.findViewById(R.id.show_count);
            mClass = (TextView) itemView.findViewById(R.id.show_class);
        }

        public void setData(int position) {
            Log.i(TAG,"设置数据"+dataList.get(position).stuName);
            mName.setText("姓名："+dataList.get(position).stuName);
            mNum.setText("学号："+dataList.get(position).stuNum);
            mWeek.setText("次数："+dataList.get(position).count + "");
            mClass.setText("班级："+dataList.get(position).class_);
        }
    }
}
