package me.teenyda.fruit.model.cart.base;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.teenyda.fruit.R;
import me.teenyda.fruit.common.entity.Order;
import me.teenyda.fruit.common.entity.OrderInfo;
import me.teenyda.fruit.common.entity.OrderItemDto;
import me.teenyda.fruit.common.mvp.MvpRxFragment;
import me.teenyda.fruit.common.utils.FileCacheUtil;
import me.teenyda.fruit.model.cart.base.adapter.ShoppingCartAdapter;
import me.teenyda.fruit.model.cart.base.presenter.ShoppingCartPresenter;
import me.teenyda.fruit.model.cart.base.view.IShoppingCartView;
import me.teenyda.fruit.model.cart.settlement.CartSettlementActicity;
import me.teenyda.fruit.model.classify.settlement.SettlementActicity;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class ShoppingCartFragment extends MvpRxFragment<IShoppingCartView, ShoppingCartPresenter> implements IShoppingCartView {

    @BindView(R.id.shopping_cart_rv)
    XRecyclerView shopping_cart_rv;

    @BindView(R.id.start_bar)
    RelativeLayout start_bar;

    @BindView(R.id.iv_cart_select_all)
    ImageView iv_cart_select_all;

    @BindView(R.id.tv_cart_total_price)
    TextView tv_cart_total_price;

    @BindView(R.id.settlement)
    TextView settlement;

    private List<Order> mSelectProduct;

    private ShoppingCartAdapter mShoppingCartAdapter;


    @Override
    protected ShoppingCartPresenter createPresenter() {
        return new ShoppingCartPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int setR_layout() {
        return R.layout.act_shopping_cart;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, mView);

        setTitleShow(false, "购物车", false);

        // StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), start_bar);
        /*<View
        android:id="@+id/fake_status_bar"
        android:layout_width="match_parent"
        android:layout_height="@dimen/statusbar_view_height"
        android:background="@color/picture_color_transparent"/>*/

        LinearLayoutManager manager = new LinearLayoutManager(getMContext());
        shopping_cart_rv.setLayoutManager(manager);
        mShoppingCartAdapter = new ShoppingCartAdapter(getMContext());
        shopping_cart_rv.setAdapter(mShoppingCartAdapter);

        shopping_cart_rv.setNestedScrollingEnabled(false);

        /*List<Cart> carts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Cart cart = new Cart();
            cart.setName("蜜柚9斤现摘宜昌青皮蜜橘新鲜水果当季孕妇橘子薄皮");
            cart.setCount(ToolUtils.randomInt(5) + 1);
            cart.setSpecification("规格: 水果一大箱");
            cart.setPrice((float) ToolUtils.randomPrice(50));

            carts.add(cart);
        }*/

        mSelectProduct = new ArrayList<>();

        mShoppingCartAdapter.setProductSelect(new ShoppingCartAdapter.IProductSelect() {
            @Override
            public void onProductedSelect(double price, boolean isSelectedAll, List<Order> selectedOrder) {
                tv_cart_total_price.setText(String.format(getString(R.string.cart_total_price), String.valueOf(price)));
                mSelectProduct = selectedOrder;
                if (isSelectedAll != iv_cart_select_all.isSelected()) {
                    iv_cart_select_all.setSelected(isSelectedAll);
                }
            }
        });

        iv_cart_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_cart_select_all.setSelected(!iv_cart_select_all.isSelected());
                mShoppingCartAdapter.selectedAll(iv_cart_select_all.isSelected());
            }
        });

        settlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mSelectProduct.size() > 0) {
                    List<OrderItemDto> orderItems = new ArrayList<>();
                    for (Order order : mSelectProduct) {
                        OrderItemDto orderItemDto = order.getOrderItems().get(0);
                        orderItems.add(orderItemDto);
                    }

                    mPresenter.settlementCart(orderItems);
                }
            }
        });

        shopping_cart_rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                Integer userId = FileCacheUtil.getUser(getMContext()).getUserId();
                mPresenter.getCart(userId);
            }

            @Override
            public void onLoadMore() {

            }
        });

    }



    @Override
    protected void requestData() {
        shopping_cart_rv.refresh();
    }

    @Override
    public Context getMContext() {
        return getContext();
    }

    @Override
    public void setCart(List<Order> orders) {
        mShoppingCartAdapter.setCarts(orders);
        shopping_cart_rv.refreshComplete();
    }

    @Override
    public void cartOrderSuccess(OrderInfo orderInfo) {
        CartSettlementActicity.startActivity(getMContext(), orderInfo.getOrderNum());
    }
}
