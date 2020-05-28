package com.wkx.fragme;

public class CommonPresenter implements ICommonPresenter {


   private   ICommonView view;
    private  ICommonModel iCommonModel;

    public CommonPresenter(ICommonView view, ICommonModel iCommonModel) {
        this.view = view;
        this.iCommonModel = iCommonModel;
    }

    @Override
    public void getData(int whichApi, Object...objects) {
        iCommonModel.getData(this, whichApi, objects);
    }

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] pD) {
        view.onSuccess(whichApi, loadType,pD);
    }

    @Override
    public void onFailed(int whichApi, Throwable pThroable) {
        view.onFailed(whichApi, pThroable);
    }
}
