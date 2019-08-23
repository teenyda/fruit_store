package me.teenyda.mvp_template.model.home.base.presenter;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.teenyda.mvp_template.common.api.BaseObserver;
import me.teenyda.mvp_template.common.mvp.BasePresenter;
import me.teenyda.mvp_template.common.utils.BitmapUtil;
import me.teenyda.mvp_template.model.home.base.view.IHomeV;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: teenyda
 * date: 2019/8/22
 * description:
 */
public class HomeP extends BasePresenter<IHomeV> {

    public void compressImage(File file) {
        Observable.create(new ObservableOnSubscribe<File>() {
            @Override
            public void subscribe(ObservableEmitter<File> emitter) throws Exception {
                if (file != null) {
                    emitter.onNext(file);
                }
                emitter.onComplete();

            }
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .map(new Function<File, File>() {
                    @Override
                    public File apply(File file) throws Exception {
                        File resizedFile = BitmapUtil.compressImage(file.getPath());
                        return resizedFile;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<File>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(File file) {
                        mBaserView.compressImageSuccess(file);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void uploadFile(File file) {
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileRequestBody);
        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "imaeg-type");

        addDisposable(mApiServer.uploadImage(part, description), new BaseObserver(mBaserView) {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }

    public void getBook() {
        addDisposable(mApiServer.bookList(), new BaseObserver(mBaserView) {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(String errorMsg) {

            }
        });
    }
}
