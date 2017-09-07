package com.billylu.mydirection.Utils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by billylu on 2017/9/2.
 */

public class MyOkHttp {

    private OkHttpClient mOkHttpClient;
    private String URL = "";

    public MyOkHttp(String URL) {
        mOkHttpClient = new OkHttpClient();

        this.URL = URL;
    }

    public String get() throws IOException {


        Request request = new Request.Builder()
                .url(URL)
                .addHeader("Accept-Language", "zh-TW")
                .build();

        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }
}