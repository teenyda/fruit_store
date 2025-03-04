package me.teenyda.fruit.model.home.seconds_kill;

import android.content.Context;
import android.content.Intent;


import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.KillFruit;
import me.teenyda.fruit.common.mvp.MvpActivity;
import me.teenyda.fruit.common.utils.CalendarsUtils;
import me.teenyda.fruit.model.home.seconds_kill.adapter.SBAdapter;
import me.teenyda.fruit.model.home.seconds_kill.adapter.SecondKillAdapter;
import me.teenyda.fruit.model.home.seconds_kill.presenter.SecondKillPresenter;
import me.teenyda.fruit.model.home.seconds_kill.view.ISecondKillView;

/**
 * author: teenyda
 * date: 2020/9/20
 * description: 限时秒杀
 */
public class SecondKillActivity extends MvpActivity<ISecondKillView, SecondKillPresenter> implements ISecondKillView {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SecondKillActivity.class);
        context.startActivity(intent);
    }

    @BindView(R.id.seconds_kill_rv)
    RecyclerView seconds_kill_xrv;

    private SecondKillAdapter mAdapter;


    @Override
    protected SecondKillPresenter createPresenter() {
        return new SecondKillPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_seconds_kill;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getMContext());

        seconds_kill_xrv.setLayoutManager(linearLayoutManager);
        // seconds_kill_xrv.setAdapter(mAdapter = new SecondKillAdapter(getMContext(), initData()));
        seconds_kill_xrv.setAdapter(new SBAdapter(getMContext(), initFruit()));
    }

    private List<KillFruit> initFruit() {
        List<KillFruit> fruits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            KillFruit f = new KillFruit("水果", CalendarsUtils.nextHour(i));
            fruits.add(f);
        }
        return fruits;
    }

    @Override
    protected void requestData() {

    }

    @Override
    public Context getMContext() {
        return this;
    }
}
