package com.wkx.fragme;

public interface ICommonModel <T>{
    void getData(ICommonPresenter presenter,int whichApi,T...params);
}
