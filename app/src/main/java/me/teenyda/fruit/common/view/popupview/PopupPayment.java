package me.teenyda.fruit.common.view.popupview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import me.teenyda.fruit.R;

/**
 * author: teenyda
 * date: 2019/8/22
 * description: 支付方式
 */
public class PopupPayment implements View.OnClickListener {

    private Context mContext;
    private PopupWindow mPopupWindow;
    private View mView;

    private LinearLayout ll_weixin;
    private LinearLayout ll_zfv;
    private LinearLayout ll_balance;

    private PaymentTypeClick mPaymentTypeClick;

    private TextView wallet_balance;

    //微信
    public static final int PAYMENT_TYPE_WX = 1;
    public static final String PAYMENT_TYPE_WX_STR = "微信支付";
    //支付宝
    public static final int PAYMENT_TYPE_ZFB = 2;
    public static final String PAYMENT_TYPE_ZFB_STR = "支付宝支付";
    //余额
    public static final int PAYMENT_TYPE_YE = 3;
    public static final String PAYMENT_TYPE_YE_STR = "余额支付";

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_weixin:
                if (mPaymentTypeClick != null)
                    mPaymentTypeClick.onPaymentTypeClickClick(PAYMENT_TYPE_WX);
                break;
            case R.id.ll_zfv:
                if (mPaymentTypeClick != null)
                    mPaymentTypeClick.onPaymentTypeClickClick(PAYMENT_TYPE_ZFB);
                break;
            case R.id.ll_balance:
                if (mPaymentTypeClick != null)
                    mPaymentTypeClick.onPaymentTypeClickClick(PAYMENT_TYPE_YE);
                break;
        }
    }

    public interface PaymentTypeClick{
        void onPaymentTypeClickClick(int type);
    }

    public void setPaymentTypeClick(PaymentTypeClick paymentTypeClick) {
        this.mPaymentTypeClick = paymentTypeClick;
    }

    public void setBalance(double balance) {
        if (mContext != null) {
            wallet_balance.setText(String.format(mContext.getString(R.string.balance), balance));
        }
    }

    public PopupPayment(Context context) {
        mContext = context;
        initPopup();
    }


    private void initPopup() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pop_payment, null);
        mPopupWindow = new PopupWindow(mView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setAnimationStyle(R.style.AnimationBottomInAndOut);

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setAlpha(1f);
            }
        });

        ll_weixin = mView.findViewById(R.id.ll_weixin);
        ll_zfv = mView.findViewById(R.id.ll_zfv);
        ll_balance = mView.findViewById(R.id.ll_balance);
        wallet_balance = mView.findViewById(R.id.wallet_balance);

        ll_weixin.setOnClickListener(this);
        ll_zfv.setOnClickListener(this);
        ll_balance.setOnClickListener(this);

    }

    public void show(View view) {

        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        setAlpha(0.5f);
    }

    public void dismiss() {
        if (mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    private void setAlpha(float alpha) {
        WindowManager.LayoutParams layoutParams = ((Activity) mContext).getWindow().getAttributes();
        layoutParams.alpha = alpha;
        ((Activity) mContext).getWindow().setAttributes(layoutParams);
    }



}
