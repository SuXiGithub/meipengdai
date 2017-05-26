package com.jiutong.meipengdai.ui.fragments;

import com.jiutong.meipengdai.R;
import com.jiutong.meipengdai.base.BaseFragment;
import com.jiutong.meipengdai.views.CircleView;

/**
 * Created by suxi on 2017/5/24.
 */

public class LoanFragment extends BaseFragment {

    private CircleView circleview;

    @Override
    protected int getLayoutResouceId() {
        return R.layout.fragment_loan;
    }

    @Override
    protected void initView() {
        super.initView();
        circleview = findViewById(R.id.circleview);
        circleview.setCurrentDegree(0.25f);
        circleview.invalidate();
    }
}
