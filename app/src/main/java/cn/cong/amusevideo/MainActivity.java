package cn.cong.amusevideo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import cn.cong.amusevideo.fragment.Main_Gallery_Fragment;
import cn.cong.amusevideo.fragment.Main_Home_Fragment;
import cn.cong.utils.app.BaseActivity;
import cn.cong.utils.app.BaseFragment;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    private FrameLayout lf;
    private  RadioButton rg_home;
    private ImageView iv_shot;
    private RadioButton rg_gallery;
    private RadioGroup rg;
    // 存放已经加载了的frag（工作间）
    private BaseFragment[] frags;
    // 存放要加载的所有的fragment（仓库）
    private BaseFragment[] frags_backup;
    private int[] rb = {R.id.rg_home, R.id.rg_gallery};
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rg=findViewById(R.id.rg);
         rg_home=findViewById(R.id.rg_home);
         iv_shot=findViewById(R.id.iv_shot);
         rg_gallery=findViewById(R.id.rg_gallery);
         lf=findViewById(R.id.lf);
    }

    @Override
    protected void initData() {
        frags_backup=new BaseFragment[]{new Main_Home_Fragment(),new Main_Gallery_Fragment()};
        frags=new BaseFragment[2];
    }

    @Override
    protected void initListener() {
        rg.setOnCheckedChangeListener(this);
        iv_shot.setOnClickListener(this);
        rg.check(R.id.rg_home);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        for (int i = 0; i < rb.length; i++) {
            if (rb[i] != checkedId)
                continue;
            changeFragment(i);
    }
}
    private void changeFragment(int index) {
        FragmentTransaction tran =getSupportFragmentManager().beginTransaction();
        // 隐藏所有的fragment
        for (Fragment frag : frags) {
            if (frag != null)
                tran.hide(frag);
        }
        // 判断当前要加载的fragment是否已经加載
        if (frags[index] == null) {
            frags[index] = frags_backup[index];
            tran.add(R.id.lf, frags[index]);
            tran.show(frags[index]);
        } else {
            tran.show(frags[index]);
        }
        // 事务提交
        tran.commit();
    }


    @Override
    public void onClick(View v) {
            jumpToActivity(ShotActivity.class);
    }
}
