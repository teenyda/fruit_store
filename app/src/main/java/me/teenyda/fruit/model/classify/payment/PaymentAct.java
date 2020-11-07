package me.teenyda.fruit.model.classify.payment;

import android.content.Context;
import android.content.Intent;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.iwgang.countdownview.CountdownView;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.model.classify.payment.presenter.PaymentPresenter;
import me.teenyda.fruit.model.classify.payment.view.IPaymentView;

/**
 * author: teenyda
 * date: 2020/11/7
 * description:
 */
public class PaymentAct extends MvpActivity<IPaymentView, PaymentPresenter> implements IPaymentView {

    @BindView(R.id.cv_countdown)
    CountdownView cv_countdown;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, PaymentAct.class);
        context.startActivity(intent);
    }
    @Override
    protected PaymentPresenter createPresenter() {
        return new PaymentPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_payment;
    }

    @Override
    protected void initView() {
        setTitleShow(true, "支付", false);
        ButterKnife.bind(this);
        Calendar c = Calendar.getInstance();
        long start = c.getTimeInMillis();
        c.add(Calendar.MINUTE, 10);
        long end = c.getTimeInMillis();
        cv_countdown.start(end - start);
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
