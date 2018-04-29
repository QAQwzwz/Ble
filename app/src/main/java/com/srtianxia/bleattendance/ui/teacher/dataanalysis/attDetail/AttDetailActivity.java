package com.srtianxia.bleattendance.ui.teacher.dataanalysis.attDetail;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.srtianxia.bleattendance.R;

/**
 * 考勤详细
 * 学生列表
 */

public class AttDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setContentView(R.layout.activity_att_detail);
        TextView title = findViewById(R.id.tv_toolbar_title);
        title.setText("打卡明细");
        String jxbId = getIntent().getStringExtra("jxbId");
    }
}
