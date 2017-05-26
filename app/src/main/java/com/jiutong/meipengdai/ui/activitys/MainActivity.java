package com.jiutong.meipengdai.ui.activitys;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.jiutong.meipengdai.R;
import com.jiutong.meipengdai.base.BaseActivity;
import com.jiutong.meipengdai.ui.fragments.HomeFragment;
import com.jiutong.meipengdai.ui.fragments.LoanFragment;
import com.jiutong.meipengdai.ui.fragments.PersonFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Fragment mContent;
    private LoanFragment loanFragment;
    private HomeFragment homeFragment;
    private View rb_home;
    private View rb_loan;
    private View rb_person;
    private PersonFragment personFragment;


    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initDate(Bundle savedInstanceState) {
        super.initDate(savedInstanceState);
    }

    @Override
    protected void initView() {
        rb_home = findViewById(R.id.rb_home);
        rb_loan = findViewById(R.id.rb_loan);
        rb_person = findViewById(R.id.rb_person);

        homeFragment = new HomeFragment();
        loanFragment = new LoanFragment();
        personFragment = new PersonFragment();

        commitFragment(R.id.main_frameLayout, homeFragment);
        mContent = homeFragment;
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        rb_home.setOnClickListener(this);
        rb_loan.setOnClickListener(this);
        rb_person.setOnClickListener(this);

    }



    public void commitFragment(int layoutId, Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(layoutId, fragment);
        transaction.commit();
    }

    /**
     * 切换tab页面
     *
     * @param fragment
     *            要显示的fragment
     */
    public void switchContent(Fragment fragment) {
        if (mContent != fragment) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            if (!fragment.isAdded()) {
                transaction.hide(mContent).add(R.id.main_frameLayout, fragment)
                        .commit();
            } else {
                transaction.hide(mContent).show(fragment)
                        .commitAllowingStateLoss();
            }
            mContent = fragment;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rb_home:
                switchContent(homeFragment);
                break;
            case R.id.rb_loan:
                switchContent(loanFragment);
                break;
            case R.id.rb_person:
                switchContent(personFragment);
                break;
        }
    }
}
