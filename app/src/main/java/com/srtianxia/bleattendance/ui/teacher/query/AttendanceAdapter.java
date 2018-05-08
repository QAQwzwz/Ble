package com.srtianxia.bleattendance.ui.teacher.query;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    private List<Attendance> mAttendanceLiat;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView attName;
        private TextView attStuNum;
        private TextView attNum;
        private ImageView headView;
        public ViewHolder(View view)
        {
            super(view);
            attStuNum = (TextView) view.findViewById(R.id.attendance_stu_num);
            attName = (TextView) view.findViewById(R.id.attendance_name);
            attNum = (TextView)view.findViewById(R.id.attendance_lack);
            //headView = (ImageView) view.findViewById(R.id.attendance_head_view);
        }
    }

    public AttendanceAdapter(List<Attendance> attendanceList)
    {
        mAttendanceLiat = attendanceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_attebdance,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Attendance attendance = mAttendanceLiat.get(position);
        holder.attNum.setText(attendance.getNum());
        holder.attName.setText(attendance.getName());
        holder.attStuNum.setText(attendance.getStuNum());
        //holder.headView.setImageResource(attendance.getImageId());
    }

    @Override
    public int getItemCount()
    {
        return mAttendanceLiat.size();
    }
}
