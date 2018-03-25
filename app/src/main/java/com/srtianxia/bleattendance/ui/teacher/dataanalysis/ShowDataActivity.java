package com.srtianxia.bleattendance.ui.teacher.dataanalysis;

import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.base.OnItemClickListener;
import com.srtianxia.bleattendance.entity.AttendListEntity;
import com.srtianxia.bleattendance.entity.ClassAttendEntity;
import com.srtianxia.bleattendance.entity.InstituteInfoEntity;
import com.srtianxia.bleattendance.entity.InstituteList;
import com.srtianxia.bleattendance.entity.JXBListEntity;
import com.srtianxia.bleattendance.entity.SchoolInfoEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;

/**
 * 数据展示的activity，展示的布局文件全在这个活动中使用
 */
public class ShowDataActivity extends AppCompatActivity implements DataAnalysisPresenter.DataConditionView, View.OnClickListener {

    private String TAG = "ShowDataActivity:";
    private DataAnalysisPresenter presenter = new DataAnalysisPresenter(this);
    private String currentJXBID;
    private RecyclerView showAttend;
    private List<AttendListEntity.Attend> attendList;
    private ShowAttendAdapter adapter;
    private int type = 1;
    private final int ACTIVITY_TYPE_ONE = 1;
    private final int ACTIVITY_TYPE_TWO = 2;
    private final int ACTIVITY_TYPE_THR = 3;
    private final int ACTIVITY_TYPE_FOR = 4;
    private RecyclerView listview;
    private MyShanXinView shanxin;

    protected void onCreate(Bundle savedInstanceState) {
        initStatusBarColor();
        super.onCreate(savedInstanceState);
        getType();
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initView();
    }

    private void getType() {
        type = getIntent().getIntExtra("choose", 1);
    }


    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        switch (type) {
            case ACTIVITY_TYPE_TWO:
                presenter.getPushesClass();
                initSearchView();
                break;
            case ACTIVITY_TYPE_THR:
                initClick_thr();
                presenter.getInstituteList();
                break;
            case ACTIVITY_TYPE_FOR:
                initClick_for();
                presenter.getSchoolInfo();
                break;
            default:
                presenter.getJXBID();
        }

    }

    /**
     * 初始化搜索按钮
     */
    private void initSearchView() {
        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        searchView.setOnClickBack(() -> ShowDataActivity.this.finish());
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                presenter.loadAttendInfoByClass(string);
            }
        });
    }

    /**
     * 设置扇形可见，隐藏缺勤最多的班级
     */
    public void showShanxin() {
        LinearLayout linear = (LinearLayout) findViewById(R.id.show_class_linear);
        shanxin = (MyShanXinView) findViewById(R.id.shanxin_view);
        linear.setVisibility(View.GONE);
        shanxin.setVisibility(View.VISIBLE);
    }

    protected int getLayoutRes() {
        switch (type) {
            case ACTIVITY_TYPE_TWO:
                return R.layout.activity_show_data_two;
            case ACTIVITY_TYPE_THR:
                return R.layout.activity_show_data_thr;
            case ACTIVITY_TYPE_FOR:
                return R.layout.activity_show_data_for;
            default:
                return R.layout.activity_show_data_one;
        }
    }

    public void loadAttendInfo(List<Object> list) {
        attendList = (List<AttendListEntity.Attend>) list.get(1);
        showAttendenceInfo(attendList);

        int[] array = (int[]) list.get(0);
        showTable(array);
    }

    public void showTable(int[] array) {
        List<Entry> entries = new ArrayList<>();
        int max = 0;
        for (int i = 0; i < 20; i++) {
            entries.add(new Entry(i, array[i]));
            if (max < array[i]) {
                max = array[i];
            }
        }
        //以10位单位向上取整
        max = max +(10 - max % 10);

        showTable(entries, 0, 20, max, 20);
    }

    public void LoadYearAttendTable(List<String> keyList, List<String> valueList) {
        if (keyList.size() == valueList.size() && keyList.size() > 0) {
            List<Entry> entries = new ArrayList<>();
            int xMin = Integer.parseInt(keyList.get(0).substring(0, 2));
            int xMax = Integer.parseInt(keyList.get(keyList.size() - 1).substring(0, 2));
            int yMax = 0;
            int i = 0;
            entries.add(new Entry(xMin - 1, 0));
            for (String key :
                    keyList) {
                int year = Integer.parseInt(key.substring(0, 2));
                int count = Integer.parseInt(valueList.get(i));

                if (yMax < count) {
                    yMax = count;
                }
                entries.add(new Entry(year, count));
                i++;
            }
            entries.add(new Entry(xMax + 1, 0));
            yMax = yMax+(10 - yMax % 10);
            Log.i(TAG,yMax+"最大y值");
            showTable(entries, xMin - 2, xMin + 8, yMax, 10);
        }
    }

    public void showTable(List<Entry> entries, int xMin, int xMax, int yMax, int total) {

        //this.data = list;
        final LineChart lineChart = (LineChart) findViewById(R.id.linechart);  //关联LineChart控件
        //初始化一条折线的数据源 一个数据点对应一个Entry对象 Entry对象包含x值和y值

        if (lineChart == null) {
            return;
        }
        lineChart.setVisibility(View.VISIBLE);
        //将数据源装进折线对象LineDataSet ，并设置折线对象的相关属性：
        LineDataSet set = new LineDataSet(entries, "BarDataSet");
        set.setColor(getResources().getColor(R.color.black));         //设置线条颜色
        set.setDrawValues(true);                                      //设置显示数据点值
        set.setValueTextColor(getResources().getColor(R.color.black));//设置显示值的字体颜色
        set.setValueTextSize(12);

        LineData lineData = new LineData(set);
        lineChart.setData(lineData);
        lineChart.invalidate();                                                //刷新显示绘图
        lineChart.setBackgroundColor(getResources().getColor(R.color.white));  //设置LineChart的背景颜色
        //设置折线图中每个数据点的选中监听
        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                String week = ((int) e.getX()) + "";
                if (adapter != null) {
                    List<AttendListEntity.Attend> list = new ArrayList<AttendListEntity.Attend>();
                    /*AttendListEntity.Attend attend1 = attendList.get(0);
                    list.add(attend1);*/
                    AttendListEntity.Attend attend;
                    for (int i = 0; i < attendList.size(); i++) {
                        attend = attendList.get(i);
                        if (attend != null)
                            if (attend.week.contains(week)) {
                                list.add(attend);
                            }
                    }
                    adapter.loadData(list);
                }
            }

            @Override
            public void onNothingSelected() {

            }
        });

        //折线图相关属性的设置：
        lineChart.setNoDataTextDescription("无数据");  //设置无数据时显示的描述信息
        lineChart.setDrawGridBackground(false);        //设置是否绘制网格背景
        lineChart.setScaleEnabled(false);              //设置图表是否缩放
        lineChart.setDescription("");            //设置图表的描述信息
        lineChart.setDescriptionColor(Color.BLACK);    //设置描述信息的字体颜色
        lineChart.setDescriptionPosition(550, 330);     //设置描述信息的显示位置
        lineChart.setDescriptionTextSize(12);

        //获得左y轴线并设置相关参数：
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);//设置坐标轴的坐标颜色
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextSize(12f);
        leftAxis.setAxisLineWidth(2);
        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(yMax);
        leftAxis.setAxisLineColor(getResources().getColor(R.color.black));//设置坐标轴颜色
        lineChart.getAxisRight().setEnabled(false); // 设置不显示右y轴  默认会显示右y轴

        //获得x轴对象并设置相关参数：
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
        xAxis.setAxisLineWidth(2);
        xAxis.setAxisMinValue(xMin);
        xAxis.setLabelCount(total);
        xAxis.setAxisMaxValue(xMax + 1);
        xAxis.setAxisLineColor(getResources().getColor(R.color.black));//设置坐标轴颜色
        xAxis.setTextColor(Color.BLACK);//设置坐标轴的坐标颜色
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);

    }

    private void initStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                Build.VERSION.SDK_INT < Build.VERSION_CODES.M
//                ? View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                : View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
//                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            );
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                getWindow().setStatusBarColor(Color.TRANSPARENT);
//            } else {
//                getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
//            }
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }
    }


    @Override
    public void loadJxbInfo(List<JXBListEntity.JXB> data) {
        loadJxb(data);
    }

    /**
     * 加载教学班信息
     *
     * @param data
     */
    public void loadJxb(List<JXBListEntity.JXB> data) {
        List<String> list = new ArrayList<>();
        for (JXBListEntity.JXB jxb :
                data) {
            list.add(jxb.course + "-" + jxb.day + "-" + jxb.classroom);
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                currentJXBID = data.get(pos).jxbID;
                presenter.getAttenceListByJxbID(currentJXBID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    /**
     * 展示缺勤信息
     *
     * @param list
     */
    public void showAttendenceInfo(List<AttendListEntity.Attend> list) {
        if (showAttend == null) {
            showAttend = (RecyclerView) findViewById(R.id.show_attendence_info);
            showAttend.setLayoutManager(new LinearLayoutManager(this));
        }

        adapter = new ShowAttendAdapter();
        showAttend.setAdapter(adapter);
        adapter.loadData(list);

    }

    @Override
    public void loadPushesClassInfo(List<ClassAttendEntity.Clazz> list) {
        listview = (RecyclerView) findViewById(R.id.show_class_info);
        listview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ShowClassAdapter adapter = new ShowClassAdapter();
        adapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                presenter.loadAttendInfoByClass(list.get(position).class_);
            }
        });
        listview.setAdapter(adapter);
        adapter.loadData(list);
    }

    @Override
    public void loadCLassAttendandce(List<AttendListEntity.Attend> list) {
        showShanxin();
        showShanXIn(list);
        showAttendenceInfo(list);
    }

    private void showShanXIn(List<AttendListEntity.Attend> list) {
        if (shanxin != null & list != null & list.size() > 0) {
            MyShanXinView.ShanXinViewData shanXinViewData;
            Map<String, Integer> map = new HashMap<>();
            for (AttendListEntity.Attend attend :
                    list) {
                String course = attend.course;
                Integer count = map.get(course);
                if (count == null) {
                    map.put(course, attend.count);
                } else {
                    map.put(course, count + attend.count);
                }
            }
            ArrayList<MyShanXinView.ShanXinViewData> dataList = new ArrayList<>();
            for (String key :
                    map.keySet()) {
                Log.i(TAG, key);
                Integer count = map.get(key);
                shanXinViewData = shanxin.new ShanXinViewData(count, key);
                dataList.add(shanXinViewData);
            }
            //展示数据
            shanxin.setData(dataList);
            shanxin.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 第三部分
     */

    @Override
    public void loadInstitute(List<InstituteList.Institute> data) {
        List<String> list = new ArrayList<>();
        list.add("请选择学院");
        for (InstituteList.Institute institute :
                data) {
            list.add(institute.name);
        }
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplication(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if (pos > 0) {
                    presenter.getInstituteInfo(data.get(pos - 1));
                    Log.i(TAG,data.get(pos - 1) + "学院id");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
    }

    /**
     * 第三部分 展示数据部分
     *
     * @param instituteInfo
     */
    @Override
    public void loadInstituteInfo(InstituteInfoEntity instituteInfo) {
        Log.i(TAG, instituteInfo.toString());
        LoadYearAttendTable(instituteInfo.yearKey, instituteInfo.yearValue);
        loadGradeBarChart(instituteInfo.gradeKey, instituteInfo.gradeValue);
        LoadTypeShanxin(instituteInfo.statusKey, instituteInfo.statusvalue);
        loadMothShanxin(instituteInfo.monthKey, instituteInfo.monthValue);
    }

    private void initClick_thr() {
        TextView year = (TextView) findViewById(R.id.year_b);
        TextView grade = (TextView) findViewById(R.id.grade_b);
        TextView status = (TextView) findViewById(R.id.status_b);
        TextView month = (TextView) findViewById(R.id.month_b);

        year.setOnClickListener(this);
        grade.setOnClickListener(this);
        status.setOnClickListener(this);
        month.setOnClickListener(this);


    }

    private void initClick_for() {
        initClick_thr();
        TextView institute = (TextView) findViewById(R.id.institute_b);
        institute.setOnClickListener(this);
    }

    private void loadMothShanxin(List<String> monthKey, List<String> monthValue) {
        try{
            if (monthKey.size() == monthValue.size() && monthKey.size() >= 0) {
                MyShanXinView shanxin = (MyShanXinView) findViewById(R.id.shanxin_view_month);
                ArrayList<MyShanXinView.ShanXinViewData> data = new ArrayList<>();
                int i = 0;
                for (String key :
                        monthKey) {
                    int i1 = Integer.parseInt(key);
                    int value = Integer.parseInt(monthValue.get(i));
                    data.add(shanxin.new ShanXinViewData(value, key + "月"));
                    i++;
                }
                shanxin.setData(data);
            }
        }catch (Exception e){

        }


    }


    /**
     * 展示考勤信息的比例
     *
     * @param statusKey
     * @param statusvalue
     */
    private void LoadTypeShanxin(List<String> statusKey, List<String> statusvalue) {
        if (statusKey.size() == statusvalue.size() && statusvalue.size() >= 0) {
            MyShanXinView shanxin = (MyShanXinView) findViewById(R.id.shanxin_view);
            ArrayList<MyShanXinView.ShanXinViewData> data = new ArrayList<>();
            String[] status = getResources().getStringArray(R.array.status);
            int i = 0;
            for (String key :
                    statusKey) {
                int i1 = Integer.parseInt(key);
                if (i1 >= 1 && i1 <= 5) {
                    key = status[i1 - 1];

                }else{
                    key= status[5];
                }
                int value = Integer.parseInt(statusvalue.get(i));
                data.add(shanxin.new ShanXinViewData(value, key));
                i++;
            }
            shanxin.setData(data);
        }

    }

    private void loadGradeBarChart(List<String> gradeKey, List<String> gradeValue) {
        if (gradeKey.size() == gradeValue.size() && gradeKey.size() >= 0) {
            List<BarEntry> entries = new ArrayList<>();   //数据点的封装类变为BarChart
            int xMin = Integer.parseInt(gradeKey.get(0)) - 2000;
            int xMax = Integer.parseInt(gradeKey.get(gradeKey.size() - 1)) - 2000;
            int yMax = 0;
            int i = 0;
            entries.add(new BarEntry(xMin - 1, 0));
            for (String key :
                    gradeKey) {
                int year = Integer.parseInt(gradeKey.get(i)) - 2000;
                int count = Integer.parseInt(gradeValue.get(i));
                if (count > yMax) {
                    yMax = count;
                }
                entries.add(new BarEntry(year, count));
                i++;
            }
            yMax = yMax+(10 - yMax % 10);
            Log.i(TAG,yMax+"最大y值");
            entries.add(new BarEntry(xMax + 1, 0));

            showGradeBarChart(entries, xMin - 3, xMin + 5, yMax, 8);
        }
    }

    private void showGradeBarChart(List<BarEntry> entries, int xMin, int xMax, int yMax, int total) {

        BarChart barChart = (BarChart) findViewById(R.id.barchart);
        //装载数据：
        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setColor(getResources().getColor(R.color.colorPrimary));  //给线条设置颜色
        set.setDrawValues(true);
        set.setValueTextColor(getResources().getColor(R.color.colorPrimary));
        set.setValueTextSize(12);
        final BarData data = new BarData(set);
        data.setBarWidth(0.9f);      // 设置条形的宽度
        barChart.setData(data);
        barChart.setFitBars(true);
        barChart.invalidate();

        //折线图相关属性的设置：
        barChart.setNoDataTextDescription("无数据");  //设置无数据时显示的描述信息
        barChart.setDrawGridBackground(false);        //设置是否绘制网格背景
        barChart.setScaleEnabled(false);              //设置图表是否缩放
        barChart.setDescription("");            //设置图表的描述信息
        barChart.setDescriptionColor(Color.BLACK);    //设置描述信息的字体颜色
        barChart.setDescriptionPosition(550, 330);     //设置描述信息的显示位置
        barChart.setDescriptionTextSize(12);

        //获得左y轴线并设置相关参数：
        YAxis leftAxis = barChart.getAxisLeft();
        leftAxis.setTextColor(Color.BLACK);//设置坐标轴的坐标颜色
        leftAxis.setDrawAxisLine(true);
        leftAxis.setDrawGridLines(false);
        leftAxis.setTextSize(12f);
        leftAxis.setAxisLineWidth(2);
        leftAxis.setAxisMinValue(0);
        leftAxis.setAxisMaxValue(yMax);
        leftAxis.setAxisLineColor(getResources().getColor(R.color.black));//设置坐标轴颜色

        barChart.getAxisRight().setEnabled(false); // 设置不显示右y轴  默认会显示右y轴

        //获得x轴对象并设置相关参数：
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(12f);
        xAxis.setAxisLineWidth(2);
        xAxis.setAxisMinValue(xMin);
        xAxis.setLabelCount(total);
        xAxis.setAxisMaxValue(xMax + 1);
        xAxis.setAxisLineColor(getResources().getColor(R.color.black));//设置坐标轴颜色
        xAxis.setTextColor(Color.BLACK);//设置坐标轴的坐标颜色
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
    }

    @Override
    public void onClick(View v) {
        showView(v.getId());
    }

    /**
     * 设置显示的图形
     *
     * @param num
     */
    public void showView(int num) {
        int[] ids = {R.id.linechart, R.id.barchart, R.id.shanxin_view, R.id.shanxin_view_month, R.id.show_institute};
        //int[] textIds = {R.id.year_b, R.id.grade_b, R.id.status_b, R.id.month_b};
        for (int id :
                ids) {
            View view = findViewById(id);
            if (view != null)
                view.setVisibility(View.GONE);
        }
        int chartId = R.id.shanxin_view_month;
        switch (num) {
            case R.id.year_b:
                chartId = R.id.linechart;
                break;
            case R.id.grade_b:
                chartId = R.id.barchart;
                break;
            case R.id.status_b:
                chartId = R.id.shanxin_view;
                break;
            case R.id.month_b:
                chartId = R.id.shanxin_view_month;
                break;
            case R.id.institute_b:
                chartId = R.id.show_institute;
                break;
        }
        View chartView = findViewById(chartId);
        if (chartView.getVisibility() == View.VISIBLE) {
            chartView.setVisibility(View.GONE);
            onResume();
        } else {
            chartView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void loadSchoolInfo(SchoolInfoEntity entity, int type) {
        switch (type) {
            case 1:
                LoadYearAttendTable(entity.key, entity.value);
                break;
            case 2:
                loadGradeBarChart(entity.key, entity.value);
                break;
            case 3:
                LoadTypeShanxin(entity.key, entity.value);
                break;
            case 4:
                loadMothShanxin(entity.key, entity.value);
                break;
            case 5:
                loadInstituteOrder(entity.key, entity.value);
                break;
        }
    }

    private void loadInstituteOrder(List<String> key, List<String> value) {
        if (key.size() == value.size() && key.size() >= 0) {
            RecyclerView recylerView = (RecyclerView) findViewById(R.id.show_institute);
            recylerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            ShowInstituteAdapter adapter = new ShowInstituteAdapter();
            recylerView.setAdapter(adapter);
            adapter.loadData(key, value);
            Log.i(TAG,key.toString());
        }

    }
}
