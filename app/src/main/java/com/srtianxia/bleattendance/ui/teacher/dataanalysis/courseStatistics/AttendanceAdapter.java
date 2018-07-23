package com.srtianxia.bleattendance.ui.teacher.dataanalysis.courseStatistics;

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
    public static final String DAY = "day";
    public static final String NORMAL = "normal";
    private String flag = DAY;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView attName;
        TextView attStuNum;
        TextView attNum;
        TextView textView;
        ImageView headView;

        public ViewHolder(View view) {
            super(view);
            attStuNum = view.findViewById(R.id.attendance_stu_num);
            attName = view.findViewById(R.id.attendance_name);
            attNum = view.findViewById(R.id.attendance_lack);
            headView = view.findViewById(R.id.attendance_head_view);
            textView = view.findViewById(R.id.attendance_text);
        }
    }

    public void load(List<StuInfoEntity> stuInfoEntityList,String flag) {
        this.stuInfoEntityList = stuInfoEntityList;
        this.flag = flag;
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
        if (flag.equals("day")) {
            holder.textView.setText("出勤情况");
            holder.attNum.setText("正常");
            if (position == 0) {
                holder.attNum.setText("迟到");
            } else if (position == 1) {
                holder.attNum.setText("外勤");
            } else if (position == 2) {
                holder.attNum.setText("缺勤");
            }
        } else {
            holder.textView.setText("缺勤：");
            holder.attNum.setText("0");
            if (position == 0) {
                holder.attNum.setText("6");
            } else if (position == 1) {
                holder.attNum.setText("5");
            } else if (position == 2) {
                holder.attNum.setText("2");
            } else if (position == 3) {
                holder.attNum.setText("1");
            }
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
