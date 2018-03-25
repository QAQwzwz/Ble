package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xiezh on 2018/3/3.
 */

public class DataAnalysisAdapter extends RecyclerView.Adapter {

    private String[] menus = new String[4];
    private int[] colors = {R.color.course_Blue,R.color.course_Cyan1,R.color.course_Green1,R.color.course_Gray};

    private OnclickListener onclickListener;

    public void setOnclickListener(OnclickListener onclickListener) {
        this.onclickListener = onclickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataAnalysisViewHolder(
                LayoutInflater.from( parent.getContext()).inflate(R.layout.data_analysis_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataAnalysisViewHolder viewHolder = (DataAnalysisViewHolder) holder;
        viewHolder.setData(position);

        viewHolder.textView.setOnClickListener(view->onclickListener.onClick(position));
    }

    @Override
    public int getItemCount() {
        return menus.length;
    }

    public void setData(String[] menus) {
        this.menus = menus;
    }

    public class DataAnalysisViewHolder extends RecyclerView.ViewHolder{

        TextView textView;

        public DataAnalysisViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        /**
         * 设置显示的菜单选项显示文字和颜色
         * @param position
         */
        public void setData(int position){
            textView.setText(menus[position]);
            position = position%4;
            textView.setBackgroundColor(colors[position]);

        }

    }

    public interface OnclickListener{
        public void onClick(int postion);
    }

}
