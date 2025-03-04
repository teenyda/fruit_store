package me.teenyda.fruit.model.home.base;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youth.banner.Banner;
import com.youth.banner.indicator.RoundLinesIndicator;
import com.youth.banner.util.BannerUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Book;
import me.teenyda.fruit.common.entity.DataBean;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.common.view.popupview.CustomProgressDialog;
import me.teenyda.fruit.model.classify.info.ProductInfoActivity;
import me.teenyda.fruit.model.home.base.adapter.ImageNetAdapter;
import me.teenyda.fruit.model.home.base.adapter.RecommendAdapter;
import me.teenyda.fruit.model.home.base.presenter.HomePRx;
import me.teenyda.fruit.model.home.base.view.IHomeV;
import me.teenyda.fruit.model.home.new_fruit.NewFruit2Activity;
import me.teenyda.fruit.model.home.seconds_kill.SecondKillActivity;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeFxFrag extends MvpRxFragment<IHomeV, HomePRx> implements IHomeV,
                                EasyPermissions.PermissionCallbacks{


    @BindView(R.id.banner)
    Banner banner;

    // @BindView(R.id.news_banner)
    // Banner newsBanner;

    private Unbinder mBind;

    @BindView(R.id.tv_vip)
    TextView tvVip;

    @BindView(R.id.rv)
    RecyclerView rv;

    @BindView(R.id.home_new_fruit)
    RelativeLayout home_new_fruit;

    @BindView(R.id.hot_fruit_rl)
    RelativeLayout hot_fruit_rl;
    private CustomProgressDialog mDialog;
    private ImageNetAdapter mImageNetAdapter;


    @Override
    protected HomePRx createPresenter() {
        return new HomePRx();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.frag_home;
    }

    @Override
    protected void initView() {
        mBind = ButterKnife.bind(this, mView);
        setStatusBarTran(false, true);

        banner.setBannerRound(BannerUtils.dp2px(5));
        banner.setIndicator(new RoundLinesIndicator(getMContext()));
        banner.setIndicatorSelectedWidth((int) BannerUtils.dp2px(15));

        //实现1号店和淘宝头条类似的效果
        // newsBanner.setAdapter(new TopLineAdapter(DataBean.getTestData2()))
        //         .setOrientation(Banner.VERTICAL)
        //         .setPageTransformer(new ZoomOutPageTransformer())
        //         .setOnBannerListener((data, position) -> {
        //             Snackbar.make(newsBanner, ((DataBean) data).title, Snackbar.LENGTH_SHORT).show();
        //             LogUtils.d("position：" + position);
        //         });


        tvVip.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD_ITALIC);
        LinearLayoutManager ll = new LinearLayoutManager(getMContext());
        rv.setLayoutManager(ll);
        rv.setAdapter(new RecommendAdapter(getMContext()));

        banner.addBannerLifecycleObserver(this);
        mDialog = CustomProgressDialog.getInstance(getMContext(), R.drawable.anmi_loading);

    }

    @OnClick({R.id.home_new_fruit,R.id.hot_fruit_rl, R.id.seconds_kill_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_new_fruit:
                // NewFruitActivity.startActivity(getMContext());
                NewFruit2Activity.startActivity(getMContext());
                break;
            case R.id.hot_fruit_rl:
                // mDialog.show();
                // Book book = new Book();
                // book.setBookName("gg");
                // mPresenter.addBoos(book);
                break;
            case R.id.seconds_kill_rl:
                SecondKillActivity.startActivity(getMContext());
                break;
        }
    }



    @Override
    protected void requestData() {
        mPresenter.getBanners();
    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.stop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        showToast();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        showToast("请同意相关权限，否则功能无法使用");
    }

    @Override
    public void setBanners(List<me.teenyda.fruit.common.entity.Banner> banners) {
        mImageNetAdapter = new ImageNetAdapter(banners);
        banner.setAdapter(mImageNetAdapter);
        mImageNetAdapter.notifyDataSetChanged();
        mImageNetAdapter.setBannerClickListener(new ImageNetAdapter.BannerClickListener() {
            @Override
            public void onBannerClick(me.teenyda.fruit.common.entity.Banner banner) {
                Integer productId = banner.getProductId();
                if (productId != null) {
                    ProductInfoActivity.startActivity(getMContext(), productId);
                }
            }
        });
    }
}
