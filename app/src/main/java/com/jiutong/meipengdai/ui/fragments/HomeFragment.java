package com.jiutong.meipengdai.ui.fragments;

import android.content.Intent;
import android.view.View;

import com.jiutong.meipengdai.R;
import com.jiutong.meipengdai.base.BaseFragment;
import com.jiutong.meipengdai.ui.activitys.EvaluateActivity;

/**
 * Created by suxi on 2017/5/24.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private View btn_apply;

    @Override
    protected int getLayoutResouceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        btn_apply = findViewById(R.id.btn_apply_loan);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        btn_apply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_apply_loan:
                mActivity.startActivity(new Intent(mActivity, EvaluateActivity.class));
                break;
        }
    }
}
