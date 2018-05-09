package com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.entity.StuInfoEntity;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {

    private List<StuInfoEntity> stuInfoEntityList = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView attName;
        TextView attStuNum;
        TextView attNum;
        ImageView headView;

        public ViewHolder(View view) {
            super(view);
            attStuNum = view.findViewById(R.id.attendance_stu_num);
            attName = view.findViewById(R.id.attendance_name);
            attNum = view.findViewById(R.id.attendance_lack);
            headView = view.findViewById(R.id.attendance_head_view);
        }
    }

    public void load(List<StuInfoEntity> stuInfoEntityList) {
        this.stuInfoEntityList = stuInfoEntityList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_attebdance, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StuInfoEntity stuInfoEntity = stuInfoEntityList.get(position);
        holder.attNum.setText("0");
        if (position < 5) {
            holder.attNum.setText("10");
        }
        holder.attName.setText(stuInfoEntity.getName());
        holder.attStuNum.setText(stuInfoEntity.getStuNum());

        if (stuInfoEntity.getGender().equals("女")) {
            holder.headView.setImageResource(R.drawable.head_girl);
        } else {
            holder.headView.setImageResource(R.drawable.boyheadview);
        }
    }

    @Override
    public int getItemCount() {
        return stuInfoEntityList.size();
    }
}
