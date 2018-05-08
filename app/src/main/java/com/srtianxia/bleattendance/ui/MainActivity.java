package com.srtianxia.bleattendance.ui;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v4.app.FragmentManager;

import com.srtianxia.bleattendance.R;
import com.srtianxia.bleattendance.base.view.BaseActivity;
import com.srtianxia.bleattendance.config.Constant;
import com.srtianxia.bleattendance.ui.enter.LoginFragment;

public class MainActivity extends BaseActivity {
    private LoginFragment mLoginFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
        initFragment();

    }

//<<<<<<< Updated upstream
//=======
    /**
     * 打开蓝牙
     */
    /*
    private void openBlueTooth() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!mBluetoothAdapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, Constant.REQUEST_CODE_BLUE_OPEN);
        }
    }
    */

    /**
     * 登录界面
     */
//>>>>>>> Stashed changes
    private void initFragment() {
        mLoginFragment = new LoginFragment();
        mFragmentManager.beginTransaction().add(R.id.fragment_container, mLoginFragment).commit();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

}
