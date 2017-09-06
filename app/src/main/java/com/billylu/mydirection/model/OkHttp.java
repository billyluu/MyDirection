package com.billylu.mydirection.model;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by billylu on 2017/9/2.
 */

public class OkHttp {

    private OkHttpClient mOkHttpClient;
    private String URL = "";

    public OkHttp(String URL) {
        mOkHttpClient = new OkHttpClient();
        this.URL = URL;
    }



    public String get() throws IOException {


        Request request = new Request.Builder()
                .url(URL)
                .build();


        Response response = mOkHttpClient.newCall(request).execute();
        return response.body().string();
    }


}
