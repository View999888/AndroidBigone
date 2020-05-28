package com.wkx.fragme;

public interface ICommonPresenter<P> extends ICommonView {
    void getData(int whichApi,P...ps);
}
