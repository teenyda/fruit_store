package me.teenyda.fruit_store.common.view.popupview;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import me.teenyda.fruit_store.R;

/**
 * author: teenyda
 * date: 2020/9/19
 * description: 通用加载弹窗
 */
public class CustomProgressDialog extends ProgressDialog {

    private AnimationDrawable mAnimation;
    private Context mContext;
    private ImageView mImageView;
    private int count = 0;
    private String oldLoadingTip;
    private int mResid;

    public CustomProgressDialog(Context context, int id) {
        super(context);
        this.mContext = context;
        this.mResid = id;
        setCanceledOnTouchOutside(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {

        mImageView.setBackgroundResource(mResid);
        // 通过ImageView对象拿到背景显示的AnimationDrawable
        mAnimation = (AnimationDrawable) mImageView.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
        mImageView.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();

            }
        });

    }



    private void initView() {
        setContentView(R.layout.custom_loading);
        mImageView = (ImageView) findViewById(R.id.loading_iv);
    }

}
