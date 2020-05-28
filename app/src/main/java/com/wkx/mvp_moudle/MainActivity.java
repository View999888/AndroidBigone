package com.wkx.mvp_moudle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.wkx.base.TestInfo;
import com.wkx.fragme.ApiConfig;
import com.wkx.fragme.CommonPresenter;
import com.wkx.fragme.ICommonView;
import com.wkx.fragme.LoadTypeConfig;
import com.wkx.fragme.ParamHashMap;
import com.wkx.fragme.TestModel;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ICommonView {

    private RecyclerView recycle_home;
    private SmartRefreshLayout smart;
    private TestModel testModel;
    private TestAdapter testAdapter;
    private int pageid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initPresenter();
    }

    private void initPresenter() {
        testModel = new TestModel();
        final CommonPresenter commonPresenter = new CommonPresenter(this, testModel);

        final Map<String, Object> params = new ParamHashMap().add("c", "api").add("a", "getList");

        commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL,params, pageid);

        smart.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH,params,pageid);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                commonPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE,params,pageid);
            }
        });
    }

    private void initView() {
        recycle_home = (RecyclerView) findViewById(R.id.recycle_home);
        smart = (SmartRefreshLayout) findViewById(R.id.smart);


        recycle_home.setLayoutManager(new LinearLayoutManager(this));
        testAdapter = new TestAdapter(this);
        recycle_home.setAdapter(testAdapter);


    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        List<TestInfo.DatasBean> datas = ((TestInfo) pD[0]).getDatas();
        testAdapter.setDataInfos(datas);

        switch (whichApi){
            case ApiConfig.TEST_GET:
                if (loadType==LoadTypeConfig.MORE){
                    smart.finishLoadmore();
                }else if (loadType==LoadTypeConfig.REFRESH){
                    smart.finishRefresh();
                    datas.clear();
                }
                break;
        }

    }

    @Override
    public void onFailed(int whichApi, Throwable pThroable) {
        Toast.makeText(MainActivity.this, pThroable.getMessage()!=null ? pThroable.getMessage():"网络请求发生错误", Toast.LENGTH_SHORT).show();
    }
}
