package com.example.lwl.retrofit2.fragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.lwl.retrofit2.MyApplication;
import com.example.lwl.retrofit2.R;

public class FragmentActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup mRg;
    private FrameLayout mFl;
    private FragmentManager manager;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        mRg= (RadioGroup) findViewById(R.id.rg_chose);
        mFl= (FrameLayout) findViewById(R.id.fl_fragment);
        rb1= (RadioButton) findViewById(R.id.rb1);
        rb2= (RadioButton) findViewById(R.id.rb2);
        rb3= (RadioButton) findViewById(R.id.rb3);
        fragment1=Test1Fragment.newInstance("张三","");
        fragment2=Test1Fragment.newInstance("李四","");
        fragment3=Test1Fragment.newInstance("王五","");
        manager=getFragmentManager();
        manager.beginTransaction().add(R.id.fl_fragment,fragment1).add(R.id.fl_fragment,fragment2).add(R.id.fl_fragment,fragment3).commit();
//        manager.beginTransaction().hide(fragment1).hide(fragment2).hide(fragment3).commit();
        mRg.setOnCheckedChangeListener(this);
        rb1.setChecked(true);
        mHandler=new MyHandler();
        Message msg=mHandler.obtainMessage();
        mHandler.sendEmptyMessage(1);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()){
            case R.id.rb1:
                Toast.makeText(this,"页面一",Toast.LENGTH_LONG).show();
                manager.beginTransaction().show(fragment1).hide(fragment2).hide(fragment3).commit();
                break;
            case R.id.rb2:
                Toast.makeText(this,"页面二",Toast.LENGTH_LONG).show();
                manager.beginTransaction().show(fragment2).hide(fragment1).hide(fragment3).commit();
                break;
            case R.id.rb3:
                Toast.makeText(this,"页面三",Toast.LENGTH_LONG).show();
                manager.beginTransaction().show(fragment3).hide(fragment1).hide(fragment2).commit();
                break;
        }

    }

    static class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(MyApplication.context,"dddddd",Toast.LENGTH_LONG).show();
        }
    }
    abstract class A{
        void a(){

        }
        abstract void c();
    }
    interface B{
        void b();
    }
}
