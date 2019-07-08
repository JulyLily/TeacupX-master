package com.lily.teacupx.ui.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.lily.teacup.annotation.autoknife.FindView;
import com.lily.teacup.basicclass.BaseBean;
import com.lily.teacup.basicclass.BaseFragment;
import com.lily.teacupx.R;

/**
 * @author Lily
 * @date 2019/7/6 0006.
 * GitHub：https://github.com/JulyLily
 * email：228821309@qq.com
 * description：
 */
public class OnFragment extends BaseFragment {

    @FindView(R.id.starCover)
    RecyclerView starCover;

    @Override
    public int getLayoutResId() {
        return R.layout.frament_on;
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public <T extends BaseBean> void onDataChangeListener(T t) {

    }
}
