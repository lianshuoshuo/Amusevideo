package cn.cong.amusevideo.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import cn.cong.amusevideo.R;
import cn.cong.amusevideo.SearchActivity;
import cn.cong.utils.app.BaseFragment;

public class Main_Home_Fragment extends BaseFragment implements View.OnClickListener {
    private ImageView iv_searchss;
    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.main_homefrag, container, false);
        iv_searchss=view.findViewById(R.id.iv_searchss);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
   iv_searchss.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        jumpToActivity(SearchActivity.class);
    }
}
