package me.teenyda.fruit.model.cart.settlement.presenter;

import java.util.List;

import me.teenyda.fruit.common.entity.Contact;
import me.teenyda.fruit.common.entity.Discounts;
import me.teenyda.fruit.common.entity.OrderPayment;
import me.teenyda.fruit.common.entity.SettlementOrder;
import me.teenyda.fruit.common.entity.User;
import me.teenyda.fruit.common.entity.Wallet;
import me.teenyda.fruit.common.mvp.BaseRxPresenter;
import me.teenyda.fruit.common.mvp.MyObserver;
import me.teenyda.fruit.common.net.request.OrderPaymentReq;
import me.teenyda.fruit.model.cart.settlement.view.ISettlementView;

/**
 * author: teenyda
 * date: 2020/9/16
 * description:
 */
public class SettlementPresenter extends BaseRxPresenter<ISettlementView> {

    public void getOrder(String orderNum) {
        addDisposable(mApi.getCartOrder(orderNum), new MyObserver<List<SettlementOrder>>(mContext) {
            @Override
            public void onSuccess(List<SettlementOrder> settlementOrder) {
                mView.setOrderItem(settlementOrder);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getUserInfo(int userId) {
        addDisposable(mApi.getUser(userId), new MyObserver<User>(mContext) {
            @Override
            public void onSuccess(User user) {
                mView.setUserInfo(user);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getDiscount(boolean isMember) {

        addDisposable(isMember ? mApi.getMemberDiscounts() : mApi.getUserDiscounts(),
            new MyObserver<List<Discounts>>(mContext) {
                @Override
                public void onSuccess(List<Discounts> discountsList) {
                    mView.setDiscount(discountsList);
                }

                @Override
                public void onFailure(Throwable e, String errorMsg) {

                }
            });
    }

    public void getWallet(Integer userId) {
        addDisposable(mApi.getWallet(userId), new MyObserver<Wallet>(mContext) {
            @Override
            public void onSuccess(Wallet wallet) {
                mView.setWallet(wallet);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void getContacts(int userId) {
        addDisposable(mApi.getContacts(userId), new MyObserver<List<Contact>>(mContext) {
            @Override
            public void onSuccess(List<Contact> contacts) {
                mView.setContacts(contacts);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }

    public void submit(OrderPaymentReq req) {
        addDisposable(mApi.toPayment(req), new MyObserver<OrderPayment>(mContext) {
            @Override
            public void onSuccess(OrderPayment orderPayment) {
                mView.toPayment(orderPayment);
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {

            }
        });
    }
}
