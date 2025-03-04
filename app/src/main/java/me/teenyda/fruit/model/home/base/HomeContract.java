package me.teenyda.fruit.model.home.base;

import java.io.File;

import me.teenyda.fruit.common.mvp.BaseView;

public class HomeContract {

    public interface IHomeView extends BaseView{
        void compressImageSuccess(File file);
    }

    public interface IHomePresenter{
        public void compressImage(File file);

        public void uploadFile(File file);

        public void getBook();

        public void getBooks();
    }
}
