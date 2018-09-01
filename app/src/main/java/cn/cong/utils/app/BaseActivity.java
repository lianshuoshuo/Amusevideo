package cn.cong.utils.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import cn.cong.utils.LogUtils;
import cn.cong.utils.http.HttpUtils;

public abstract class BaseActivity extends AppCompatActivity {

    protected Handler handler = new Handler();
    protected BaseActivity act; // act = this;? 上下文环境还没有搭建起来，导致this中某些东西为null
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        act = this;
        sp = getSharedPreferences(AppContent.SP_NAME, MODE_PRIVATE);
        setContentView(getLayoutId());
        initView();
        initData();
        initListener();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();


    protected void saveSP(String key, String value) {
        sp.edit().putString(key, value).apply();
    }

    protected String getSP(String key, String def) {
        return sp.getString(key, def);
    }


    // 跳转（携带数据）
    protected void jumpToActivity(Class<? extends BaseActivity> cls, String... ss) {
        Intent intent = new Intent(this, cls);
        for (int i = 0; i < ss.length; i++) {
            intent.putExtra(String.valueOf(i), ss[i]);
        }
        startActivity(intent);
        LogUtils.d("from:" + this.getClass().getSimpleName() + "|to:" + cls.getSimpleName());
    }

    protected String getDataFromIntent(int i) {
        Intent intent = getIntent();
        if (intent == null)
            return null;
        return intent.getStringExtra(String.valueOf(i));
    }


    // 进度提示弹窗的弹出
    protected void showDialog() {
        // TODO: 18/07/25 025
    }

    // 进度提示弹窗的隐藏
    protected void dismissDialog() {
        // TODO: 18/07/25 025
    }


    // 封装Toast（可兼容子线程）
    protected void showToast(final CharSequence str) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(act, str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        HttpUtils.cancel(this); // 取消当前Activity内的网络请求（要求是每次请求的tag为this）
        handler.removeCallbacksAndMessages(null); // 取消handler任务
        super.onDestroy();
    }
}
