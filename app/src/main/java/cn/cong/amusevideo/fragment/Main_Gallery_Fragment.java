package cn.cong.amusevideo.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.cong.utils.app.BaseFragment;

public class Main_Gallery_Fragment extends BaseFragment{
    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        TextView tv=new TextView(container.getContext());
        tv.setText("图库");
        return tv;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
