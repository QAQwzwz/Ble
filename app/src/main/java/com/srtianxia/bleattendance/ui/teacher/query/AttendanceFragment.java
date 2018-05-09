package com.srtianxia.bleattendance.ui.teacher.query;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.base.view.BaseFragment;
import com.srtianxia.bleattendance.ui.course.TabPagerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 梅梅 on 2017/1/30.
 */
public class AttendanceFragment extends BaseFragment {

    private final String TAG = "AttendanceFragment";

    //private static final String[] PARENT_TITLES = {"应出勤", "BLE接收考勤", "考勤情况"};
    private static final String[] PARENT_TITLES = {};

    @BindView(R.id.tab_attend)
    TabLayout mTab;
    @BindView(R.id.vp_attend)
    ViewPager mViewPager;
    @BindView(R.id.recycler_view_attendance)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_attendance_more)
    ImageView more;
    /*
    @BindView(R.id.attendance_name)
    TextView studentName;
    @BindView(R.id.attendance_stu_num)
    TextView studentNum;
    @BindView(R.id.attendance_lack)
    TextView lackNum;
    */


    private List<Attendance> mAttendanceList = new ArrayList<>();
    private List<AttListFragment> mFragmentList = new ArrayList<>();
    private List<PopuWindowClassId> mPopuWindowClassId = new ArrayList<>();
    private List<String> mTitles = Arrays.asList(PARENT_TITLES);

    private AttendanceAdapter mAdapter = new AttendanceAdapter(mAttendanceList);
    private PopuWindowClassIdAdapter mPopuAdapter = new PopuWindowClassIdAdapter(mPopuWindowClassId);
    private RecyclerView popuRecyclerView;

    /*
    @Override
    protected void initView() {
        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(), mFragmentList, mTitles);
        for (int i = 0; i < 3; i++) {
            mFragmentList.add(AttListFragment.newInstance(i));
        }
        mViewPager.setAdapter(adapter);
       mTab.setupWithViewPager(mViewPager);
    }
    */

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        TabPagerAdapter adapter = new TabPagerAdapter(getChildFragmentManager(), mFragmentList, mTitles);
        for (int i = 0; i < 3; i++) {
            mFragmentList.add(AttListFragment.newInstance(i));
        }
        mViewPager.setAdapter(adapter);
        mTab.setupWithViewPager(mViewPager);
        //studentName.setText("姓名：");
        //studentNum.setText("学号：");
        //lackNum.setText("缺到：");
        getData();
    }
    /*
    @Override
    public void onCreated(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_attendance,container,false);
        initData();
        return view;
    }
    */

/*
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
    */

    public List<Attendance> getData() {
        List<Attendance> attendanceList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Attendance attendance = new Attendance("王晨宇", "2016215067", "0");
            mAttendanceList.add(attendance);
        }
        return attendanceList;
    }

    public List<PopuWindowClassId> getPopuClassId() {
        List<PopuWindowClassId> popuWindowClassIdList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            PopuWindowClassId popuWindowClassId = new PopuWindowClassId("04031601");
            mPopuWindowClassId.add(popuWindowClassId);
        }
        return popuWindowClassIdList;
    }

    @OnClick(R.id.fragment_attendance_more)
    public void showClassId() {
        showPopWindow();
    }

    /**
     * 显示下拉菜单
     */
    public void showPopWindow() {
        //RecyclerView popuRecyclerView =(RecyclerView) getView().findViewById(R.id.popuwindow_recycler_view);
        //popuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //popuRecyclerView.setAdapter(mPopuAdapter);
        PopWindow popWindowe = new PopWindow(getContext(), getView(), R.layout.pop_filter);
        popWindowe.isClickOutSide(false);
        popWindowe.showAsDropDown(more);
        //getPopuClassId();
        //popWindowe.setAnimationStyle(R.anim.anim_pop);
        //getPopuClassId();
        //popWindowe.showAtLocation();
    }

    public void handleListView() {

    }

    /**
     * 跟据返回值确定视图
     *
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_attendance;
    }

    public static AttendanceFragment newInstance() {
        Bundle bundle = new Bundle();
        AttendanceFragment attendanceFragment = new AttendanceFragment();
        attendanceFragment.setArguments(bundle);
        return attendanceFragment;
    }

    public void postAttInfo() {
        mFragmentList.get(2).postAttInfo();
    }
}
