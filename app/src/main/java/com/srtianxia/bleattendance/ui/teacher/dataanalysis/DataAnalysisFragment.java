package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.ui.teacher.dataanalysis.attChoose.AttChooseActivity;

/**
 * Created by xiezh on 2018/3/3.
 */

/**
 * 老师界面的主要fragment
 */
public class DataAnalysisFragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tea_before_dataanalysis, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.teacherDataChooseRV);
        adapter = new Adapter();
        adapter.setClickListener((viewHolder, position) -> {
            Intent intent = new Intent(getContext(), AttChooseActivity.class);
            intent.putExtra("flag", position);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public static DataAnalysisFragment newInstance() {
        Bundle bundle = new Bundle();
        DataAnalysisFragment fragment = new DataAnalysisFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

}

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private String[] strings = {"考勤统计", "课程数据"};
    private int[] imgs = {R.mipmap.ic_teacher_arrivedata, R.mipmap.ic_teacher_coursedata};
    private ClickListener clickListener;

    public interface ClickListener {
        void click(ViewHolder viewHolder, int position);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher_main_choose_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(strings[position]);
        holder.imageView.setImageResource(imgs[position]);
        if (clickListener != null) {
            holder.itemView.setOnClickListener(v -> clickListener.click(holder, holder.getAdapterPosition()));
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.textView = itemView.findViewById(R.id.teacher_choose_item_text);
            this.imageView = itemView.findViewById(R.id.teacher_choose_item_img);
        }
    }
}