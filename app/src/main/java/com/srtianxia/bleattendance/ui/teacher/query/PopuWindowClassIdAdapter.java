package com.srtianxia.bleattendance.ui.teacher.query;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;

import java.util.List;

public class PopuWindowClassIdAdapter extends RecyclerView.Adapter<PopuWindowClassIdAdapter.ViewHolder> {

    private List<PopuWindowClassId> classIdList;
    static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView classId;

        public ViewHolder(View view)
        {
            super(view);
            classId = view.findViewById(R.id.popuwindow_class_id);
        }
    }

    public PopuWindowClassIdAdapter(List<PopuWindowClassId> classIdList)
    {
        this.classIdList = classIdList;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popuwindow,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position)
    {
        PopuWindowClassId popuWindowClassId = classIdList.get(position);
        holder.classId.setText(popuWindowClassId.getClassId());
    }

    @Override
    public int getItemCount()
    {
        return classIdList.size();
    }
}
