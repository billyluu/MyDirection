package com.billylu.mydirection.Utils;

import com.billylu.mydirection.bean.PositionBean;

import java.io.IOException;

/**
 * Created by billylu on 2017/9/6.
 */

public class GetJSONThread extends Thread {

    private String url;
    private CallBack callBack;


    public GetJSONThread(String url, GetJSONThread.CallBack callBack) {
        this.url = url;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        super.run();
        try {
            BaseActivity.OkHttp okHttp = new BaseActivity.OkHttp(url);
            String xmlPath = okHttp.get();
            PositionBean positionBean = new ParserJSON().parseJson(xmlPath);
            callBack.getResult(positionBean);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public interface CallBack{

        void getResult(PositionBean positionBean);

    }
}
