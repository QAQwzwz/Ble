package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.base.OnItemClickListener;
import com.srtianxia.bleattendance.entity.ClassAttendEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiezh on 2018/3/7.
 */

public class ShowInstituteAdapter extends RecyclerView.Adapter {
    private static final String TAG = "ShowAttendAdapter";
    private List<String> dataList = new ArrayList<>();
    private List<String> countList = new ArrayList<>();
    private OnItemClickListener itemClickListener;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowInstituteAdapter.AttendInfoViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.data_analysis_show_attendinfo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.i(TAG,"绑定viewHolder");
        ShowInstituteAdapter.AttendInfoViewHolder viewHolder = (ShowInstituteAdapter.AttendInfoViewHolder) holder;
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

    public void loadData(List<String> dataList,List<String> countList) {
        Log.i(TAG,"load数据");
        this.dataList.clear();
        this.dataList.addAll(dataList);
        this.countList.clear();
        this.countList.addAll(countList);
        notifyDataSetChanged();
    }

    public void clearData() {
        dataList.clear();
    }

    public class AttendInfoViewHolder extends RecyclerView.ViewHolder {
        TextView mCount;
        TextView mClass;
        TextView mOrder;

        public AttendInfoViewHolder(View itemView) {
            super(itemView);
            Log.i(TAG,"寻找view");
            mCount = (TextView) itemView.findViewById(R.id.show_name);
            mClass = (TextView) itemView.findViewById(R.id.show_num);
            mOrder = (TextView) itemView.findViewById(R.id.show_class);
        }

        public void setData(int position) {

            mClass.setText("学  院："+dataList.get(position));
            mCount.setText("总次数："+countList.get(position));
            mOrder.setText("总排名："+ (position + 1) +"名");
        }
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
