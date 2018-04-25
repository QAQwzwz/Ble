package com.srtianxia.bleattendance.http;

import com.srtianxia.bleattendance.http.api.Api;

/**
 * Created by zia on 2018/4/25.
 */
public enum Service {
    instance;

    public Api api;

    private Service() {
        api = ApiUtil.createApi(Api.class, ApiUtil.getBaseUrl());
    }


}
