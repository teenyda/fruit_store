package me.teenyda.fruit.model.main;

import androidx.annotation.Nullable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle3.components.support.RxFragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.teenyda.fruit.R;
import me.teenyda.fruit.model.cart.base.ShoppingCartFragment;
import me.teenyda.fruit.model.classify.base.ClassifyFragment;
import me.teenyda.fruit.model.home.base.HomeFxFrag;
import me.teenyda.fruit.model.myself.base.MyselfFrag;

public class MainActivity extends RxAppCompatActivity {

    private HomeFxFrag mHomeFrag;
    // private StoreFrag mStoreFrag;
    private ClassifyFragment mClassifyFrag;
    private ShoppingCartFragment mCartFragment;
    private MyselfFrag mMyselfFrag;
    private RxFragment mCurrentFragment;

    @BindView(R.id.home_rl)
    RelativeLayout home_rl;

    @BindView(R.id.classify_rl)
    RelativeLayout classify_rl;

    @BindView(R.id.myself_rl)
    RelativeLayout myself_rl;

    @BindView(R.id.shopping_cart_rl)
    RelativeLayout shopping_cart_rl;

    @BindView(R.id.home_iv)
    ImageView home_iv;
    @BindView(R.id.classify_iv)
    ImageView classify_iv;
    @BindView(R.id.shopping_cart_iv)
    ImageView shopping_cart_iv;
    @BindView(R.id.myself_iv)
    ImageView myself_iv;

    @BindView(R.id.home_tv)
    TextView home_tv;
    @BindView(R.id.classify_tv)
    TextView classify_tv;
    @BindView(R.id.shopping_cart_tv)
    TextView shopping_cart_tv;
    @BindView(R.id.myself_tv)
    TextView myself_tv;


    private FragmentManager manager;
    private Unbinder mBind;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        manager = getSupportFragmentManager();
        home_rl.performClick();

//        StatusBarUtil.setColor(this, getColor(R.color.c_00000000));
    }

    @OnClick({R.id.home_rl, R.id.classify_rl,R.id.shopping_cart_rl, R.id.myself_rl})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_rl:
                switchNavigation(0);
                switchIcon(0);
                break;
            case R.id.classify_rl:
                switchNavigation(1);
                switchIcon(1);
                break;
            case R.id.shopping_cart_rl:
                switchNavigation(2);
                switchIcon(2);
                break;
            case R.id.myself_rl:
                switchNavigation(3);
                switchIcon(3);
                break;
        }
    }
    private void switchNavigation(int index) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (index) {
            case 0:

                if (mHomeFrag == null)
                    mHomeFrag = new HomeFxFrag();

                if (!mHomeFrag.isAdded())
                    transaction.add(R.id.main_frame, mHomeFrag);

                if (mCurrentFragment != null && mCurrentFragment != mHomeFrag) {
                    transaction.show(mHomeFrag).hide(mCurrentFragment).commit();
                } else {
                    transaction.show(mHomeFrag).commit();
                }
                // StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);


                mCurrentFragment = mHomeFrag;
                break;
            case 1:

                if (mClassifyFrag == null)
                    mClassifyFrag = new ClassifyFragment();

                if (!mClassifyFrag.isAdded())
                    transaction.add(R.id.main_frame, mClassifyFrag);

                if (mCurrentFragment != null && mCurrentFragment != mClassifyFrag) {
                    transaction.show(mClassifyFrag).hide(mCurrentFragment).commit();
                }
                // StatusBarUtil.setColor(MainActivity.this, getColor(R.color.colorPrimary), 0);
                // StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, null);
                mCurrentFragment = mClassifyFrag;
                break;
           case 2:
               if (mCartFragment == null) {
                   mCartFragment = new ShoppingCartFragment();
               }

               if (!mCartFragment.isAdded())
                   transaction.add(R.id.main_frame, mCartFragment);

               if (mCurrentFragment != null && mCurrentFragment != mCartFragment) {
                   transaction.show(mCartFragment).hide(mCurrentFragment).commit();
               }
               // StatusBarUtil.setColor(MainActivity.this, getColor(R.color.colorPrimary), 0);
               mCurrentFragment = mCartFragment;
               break;

        case 3:
            if (mMyselfFrag == null) {
                mMyselfFrag = new MyselfFrag();
            }

            if (!mMyselfFrag.isAdded())
                transaction.add(R.id.main_frame, mMyselfFrag);

            if (mCurrentFragment != null && mCurrentFragment != mMyselfFrag) {
                transaction.show(mMyselfFrag).hide(mCurrentFragment).commit();
            }
            // StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0,null);
            mCurrentFragment = mMyselfFrag;
            break;
        }
    }

    private void switchIcon(int index) {
        switch (index) {
            case 0:
                home_iv.setSelected(true);
                classify_iv.setSelected(false);
                shopping_cart_iv.setSelected(false);
                myself_iv.setSelected(false);

                home_tv.setTextColor(getColor(R.color.c_d4237a));
                classify_tv.setTextColor(getColor(R.color.c_ffffff));
                shopping_cart_tv.setTextColor(getColor(R.color.c_ffffff));
                myself_tv.setTextColor(getColor(R.color.c_ffffff));
                break;
            case 1:
                home_iv.setSelected(false);
                classify_iv.setSelected(true);
                shopping_cart_iv.setSelected(false);
                myself_iv.setSelected(false);

                home_tv.setTextColor(getColor(R.color.c_ffffff));
                classify_tv.setTextColor(getColor(R.color.c_d4237a));
                shopping_cart_tv.setTextColor(getColor(R.color.c_ffffff));
                myself_tv.setTextColor(getColor(R.color.c_ffffff));
                break;
            case 2:
                home_iv.setSelected(false);
                classify_iv.setSelected(false);
                shopping_cart_iv.setSelected(true);
                myself_iv.setSelected(false);

                home_tv.setTextColor(getColor(R.color.c_ffffff));
                classify_tv.setTextColor(getColor(R.color.c_ffffff));
                shopping_cart_tv.setTextColor(getColor(R.color.c_d4237a));
                myself_tv.setTextColor(getColor(R.color.c_ffffff));
                break;

            case 3:
                home_iv.setSelected(false);
                classify_iv.setSelected(false);
                shopping_cart_iv.setSelected(false);
                myself_iv.setSelected(true);

                home_tv.setTextColor(getColor(R.color.c_ffffff));
                classify_tv.setTextColor(getColor(R.color.c_ffffff));
                shopping_cart_tv.setTextColor(getColor(R.color.c_ffffff));
                myself_tv.setTextColor(getColor(R.color.c_d4237a));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mCurrentFragment != null) {
            mCurrentFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
