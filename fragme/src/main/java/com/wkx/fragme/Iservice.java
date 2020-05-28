package com.wkx.fragme;

import com.wkx.base.TestInfo;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Iservice {

    @GET(".")
    Observable<TestInfo>  getTestData(@QueryMap Map<String,Object> params, @Query("page_id") int pageId);
}
