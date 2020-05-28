package com.wkx.fragme;

public interface ICommonView<D> {
    void onSuccess(int whichApi, int loadType, D... pD);

    void onFailed(int whichApi, Throwable pThroable);
}
