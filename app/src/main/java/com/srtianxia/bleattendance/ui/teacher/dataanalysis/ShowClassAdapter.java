package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.base.OnItemClickListener;
import com.srtianxia.bleattendance.entity.AttendListEntity;
import com.srtianxia.bleattendance.entity.ClassAttendEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezh on 2018/3/7.
 */

public class ShowClassAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ShowAttendAdapter";
    private List<ClassAttendEntity.Clazz> dataList = new ArrayList<>();
    private OnItemClickListener itemClickListener;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowClassAdapter.AttendInfoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.data_analysis_show_attendinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG,"绑定viewHolder");
        ShowClassAdapter.AttendInfoViewHolder viewHolder = (ShowClassAdapter.AttendInfoViewHolder) holder;
        viewHolder.setData(position);
        if(itemClickListener!=null){
            viewHolder.itemView.setOnClickListener(v->itemClickListener.onClick(position));
        }
    }

    @Override
    public int getItemCount() {
        Log.i(TAG,"返回数量 ： " +dataList.size());
        return dataList.size();
    }

    public void loadData(List<ClassAttendEntity.Clazz> dataList) {
        Log.i(TAG,"load数据");
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void clearData() {
        dataList.clear();
    }

    public class AttendInfoViewHolder extends RecyclerView.ViewHolder {
        TextView mCount;
        TextView mClass;

        public AttendInfoViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG,"寻找view");
            mCount = (TextView) itemView.findViewById(R.id.show_name);
            mClass = (TextView) itemView.findViewById(R.id.show_num);
        }

        public void setData(int position) {

            mClass.setText("班  级："+dataList.get(position).class_ + "");
            mCount.setText("总次数："+dataList.get(position).count);
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
