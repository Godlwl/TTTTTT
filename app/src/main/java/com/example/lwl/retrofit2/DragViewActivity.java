package com.example.lwl.retrofit2;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DragViewActivity extends AppCompatActivity implements View.OnTouchListener {
    private Button mBtnDrag;
    private int xDelta;
    private int yDelta;

    private int lastX;
    private int lastY;

    int screenWidth;
    int screenHeight;

    int titleHeight;
    int statusBarHeight1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
        mBtnDrag= (Button) findViewById(R.id.btn_drag);
        mBtnDrag.setOnTouchListener(this);
        /**
         * 方法一设置margin
         */
//        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        layoutParams.leftMargin=50;
//        layoutParams.topMargin=50;
//        mBtnDrag.setLayoutParams(layoutParams);
        DisplayMetrics   dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;

        /**
         * 获取状态栏
         */

        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
        }
//        Log.d("barheight","statusBarHeight1"+statusBarHeight+" title:"+titleHeight);
        screenHeight = dm.heightPixels - 162;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        titleHeight=getTitleHeight(this);
        Log.d("barheight","statusBarHeight1:"+statusBarHeight1+"title:"+titleHeight);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//        final int x = (int) event.getRawX();
//        final int y = (int) event.getRawY();
//        Log.d("TAG", "onTouch: x= " + x + "y=" + y);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                /**
                 * 方法一
                 */
//                Log.d("ACTION_DOWN","x:"+event.getRawX()+"  y:"+event.getRawY());
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) v
//                        .getLayoutParams();
//                xDelta = x - params.leftMargin;
//                yDelta = y - params.topMargin;
//                Log.d("TAG", "ACTION_DOWN: xDelta= " + xDelta + "yDelta=" + yDelta);

                /**
                 * 方法二
                 */
                lastX = (int) event.getRawX();// 获取触摸事件触摸位置的原始X坐标
                lastY = (int) event.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:
                /**
                 * 方法一
                 */
//                Log.d("ACTION_MOVE","x:"+event.getRawX()+"  y:"+event.getRawY());
//                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) v
//                        .getLayoutParams();
//                int xDistance = x - xDelta;
//                int yDistance = y - yDelta;
//                Log.d("TAG", "ACTION_MOVE: xDistance= " + xDistance + "yDistance=" + yDistance);
//                layoutParams.leftMargin = xDistance;
//                layoutParams.topMargin = yDistance;
//                v.setLayoutParams(layoutParams);

                /**
                 * 方法二
                 */
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;
                int l = v.getLeft() + dx;
                int b = v.getBottom() + dy;
                int r = v.getRight() + dx;
                int t = v.getTop() + dy;
// 下面判断移动是否超出屏幕
                if (l < 0) {
                    l = 0;
                    r = l + v.getWidth();
                }
                if (t < 0) {
                    t = 0;
                    b = t + v.getHeight();
                }
                if (r > screenWidth) {
                    r = screenWidth;
                    l = r - v.getWidth();
                }
                if (b > screenHeight) {
                    b = screenHeight;
                    t = b - v.getHeight();
                }
                v.layout(l, t, r, b);
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                v.postInvalidate();



                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;
    }


    public int getTitleHeight(Activity activity) {
        int contentTop = getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        return contentTop;
    }
}
