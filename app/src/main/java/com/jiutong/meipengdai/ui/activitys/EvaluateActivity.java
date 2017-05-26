package com.jiutong.meipengdai.ui.activitys;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.jiutong.meipengdai.R;
import com.jiutong.meipengdai.base.AppManager;
import com.jiutong.meipengdai.base.BaseActivity;

/**
 * 额度评估界面
 * Created by suxi on 2017/5/24.
 */

public class EvaluateActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_submit;

    @Override
    public int getLayoutResouceId() {
        return R.layout.activity_evaluate;
    }

    @Override
    protected void initView() {
        super.initView();
        btn_submit = (Button) findViewById(R.id.btn_submit);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        btn_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit:
                startActivity(new Intent(EvaluateActivity.this, EvaluateResultActivity.class));
                break;
        }
    }
}
