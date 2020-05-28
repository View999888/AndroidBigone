package com.wkx.fragme;

import android.view.textservice.TextInfo;

import com.wkx.base.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestModel implements ICommonModel {
    @Override
    public void getData(ICommonPresenter presenter, int whichApi, Object[] params) {

        int loadType = (int) params[0];

        Map param = (Map) params[1];
        int pageId = (int) params[2];

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://static.owspace.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        Observable<TestInfo> testData = retrofit.create(Iservice.class).getTestData(param, pageId);

        testData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TestInfo>() {
                    @Override
                    public void accept(TestInfo textInfo) throws Exception {
                        presenter.onSuccess(whichApi, loadType, textInfo);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        presenter.onFailed(whichApi, throwable);
                    }
                });
    }
}
