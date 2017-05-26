package com.jiutong.meipengdai.views;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jiutong.meipengdai.R;
import com.jiutong.meipengdai.utils.DensityUtil;
import com.orhanobut.logger.Logger;

public class CircleView extends View{

    private final Context mContext;
    Handler handler = new Handler();
    private Paint paint;//画笔
    private RectF oval; // RectF对象（圆环）
    private int currentDegree = 0;//当前度数（除360可求百分比）
    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            if (currentDegree >= 270)
                return;
//				currentDegree = 0;
            invalidate();
            handler.postDelayed(runnable, 6);//6毫秒绘制一次（可以根据需要更改）
            currentDegree++;
        }
    };
    @SuppressLint("NewApi")
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();//颜色渐变插值器
    private int height;//控件高
    private int width;//控件宽
    private int circleWidth;//圆环宽

    //渐变数组
    private int[] arcColors = new int[]{
            Color.parseColor("#DE4C30"),
            Color.parseColor("#f09738"),
            Color.parseColor("#DE4C30")};


    private int strokeWidth = 20;//画笔大小
    private boolean isGradual = true;//是否显示渐变色
    private Paint paintCircle;
    private Paint paintPart;
    private float radius = 15;
    private float dx = 0;
    private float dy = 7;

    public CircleView(Context context) {
        this(context, null, 0);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        this.mContext = context;
        //初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);
        oval = new RectF();

        strokeWidth = DensityUtil.dip2px(context, 6);
        paint.setStrokeWidth(strokeWidth); // 线宽
        paint.setStyle(Paint.Style.STROKE);

        //设置颜色渐变
        SweepGradient sweepGradient =
                new SweepGradient(width / 2, height / 2, arcColors, null);
        paint.setShader(sweepGradient);


        paintCircle = new Paint();
        paintCircle.setAntiAlias(true);
        paintCircle.setColor(Color.WHITE);

        //关闭硬件加速，要不然无法显示阴影
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        paintCircle.setShadowLayer(radius, dx, dy, 0xFF333333);

        paintPart = new Paint();
        paintPart.setAntiAlias(true);
        paintPart.setStrokeWidth(strokeWidth); // 线宽
        paintPart.setStyle(Paint.Style.STROKE);
        paintPart.setColor(Color.parseColor("#e6e6e6"));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        //计算最小宽度
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);

        if (width >= height) {
            circleWidth = height;
        } else {
            circleWidth = width;
        }

        setMeasuredDimension(circleWidth, circleWidth);
        oval.left = strokeWidth / 2; // 左边
        oval.top = strokeWidth / 2; // 上边
        oval.right = circleWidth - strokeWidth / 2; // 右边
        oval.bottom = circleWidth - strokeWidth / 2; // 下边
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        //draw外环
        canvas.drawArc(oval, 180, 360, false, paint);

        if(currentDegree != 0){
            //draw灰色部分
            canvas.drawArc(oval, 270, currentDegree, false, paintPart);
        }

        //draw中间圆以及阴影
        canvas.drawCircle(circleWidth / 2, circleWidth / 2, circleWidth / 2 - strokeWidth * 3, paintCircle);

    }

    /**
     * 根据百分比设置颜色范围
     *
     * @param pDegree  灰色区域所占百分比
     */
    public void setCurrentDegree(float pDegree) {
        this.currentDegree = (int) (360f * pDegree);
    }

    /**
     * 颜色是否渐变
     *
     * @param gradual
     */
    public void setGradual(boolean gradual) {
        this.isGradual = gradual;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                paintCircle.setShadowLayer(radius, dx, dy, 0xFFF09738);
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                paintCircle.setShadowLayer(radius, dx, dy, 0xFF333333);
                break;
        }

        invalidate();
        return true;
    }
}
