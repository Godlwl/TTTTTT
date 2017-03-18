package com.example.lwl.retrofit2;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiRequest request;
    private TextView tv;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityManager manager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        int heapSize = manager.getMemoryClass();
        Log.d("msg","堆内存大小："+heapSize);

        Runtime rt=Runtime.getRuntime();
        long maxMemory=rt.maxMemory();
        Log.d("maxMemory:",Long.toString(maxMemory/(1024*1024)));
        request=new ApiRequest();
        tv= (TextView) findViewById(R.id.tv_text);
        btn= (Button) findViewById(R.id.btn_get);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request.getWeizhang().getWeiZhang("d41e418205bb19598f387e6ef026463c").enqueue(new Callback<ResultDataResponse>() {
                    @Override
                    public void onResponse(Call<ResultDataResponse> call, Response<ResultDataResponse> response) {
                        tv.setText(response.body().getResult().get(0).getProvince());
                        SharedPreferences sp=getSharedPreferences("msg",0);
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("msg","hhhh");
                    }

                    @Override
                    public void onFailure(Call<ResultDataResponse> call, Throwable t) {
                        /**
                         * 用户1提交代码
                         */
                        /**
                         * 用户2提交代码
                         */

                        /**
                         * 用户2使用了新分支TEST
                         */

                    }
                });
            }
        });

    }
}
