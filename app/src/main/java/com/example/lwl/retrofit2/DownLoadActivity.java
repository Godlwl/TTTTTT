package com.example.lwl.retrofit2;

import android.app.DownloadManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class DownLoadActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTvTitle;
    private ProgressBar mPb;
    private TextView mTvPro;
    private Button mBtnDown;
    private DownloadManager mDownloadManager;
    private DownloadManager.Request mRequest;
    private DownloadManager.Query mQuery;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private long mId;
    private Handler mHandler;
    public final static String DOWNLOADURL = "http://ucdl.25pp.com/fs08/2017/01/20/2/2_87a290b5f041a8b512f0bc51595f839a.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);
        initView();
        initData();
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String name=msg.getData().getString("name");
                int pro=msg.getData().getInt("pro");
                mTvTitle.setText(name);
                mPb.setProgress(pro);
                mTvPro.setText(String.valueOf(pro)+"%");


            }
        };
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {

                Cursor cursor = mDownloadManager.query(mQuery.setFilterById(mId));
                if (cursor != null && cursor.moveToFirst()) {
                    if (cursor.getInt(
                            cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
                        mPb.setProgress(100);
                        install(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/app-release.apk");
                        mTimerTask.cancel();
                    }
                    String title = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_TITLE));
                    String address = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                    int bytes_total = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                    int pro = (bytes_downloaded * 100) / bytes_total;
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pro", pro);
                    bundle.putString("name", title);
                    msg.setData(bundle);
                    mHandler.sendMessage(msg);

                }
                cursor.close();
            }
        };

    }

    private void initData() {
        mDownloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        mRequest = new DownloadManager.Request(Uri.parse(DOWNLOADURL));
        mQuery=new DownloadManager.Query();
        mRequest.setTitle("大象投教");
        mRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        mRequest.setAllowedOverRoaming(false);
        mRequest.setMimeType("application/vnd.android.package-archive");
        mRequest.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
        mRequest.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"app-release.apk");
        mPb.setMax(100);
    }

    private void initView() {
        mTvTitle= (TextView) findViewById(R.id.tv_app_title);
        mPb= (ProgressBar) findViewById(R.id.pb_down);
        mTvPro= (TextView) findViewById(R.id.tv_app_progress);
        mBtnDown= (Button) findViewById(R.id.btn_down);
        mBtnDown.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        /**
         * 开始下载
         */
        mId = mDownloadManager.enqueue(mRequest);
        /**
         * 查询进度
         */
        mTimer.schedule(mTimerTask, 0,1000);
//        mTimerTask.run();
        mBtnDown.setClickable(false);

    }

    private void install(String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path), "application/vnd.android.package-archive");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//4.0以上系统弹出安装成功打开界面
        startActivity(intent);
    }
}
