package com.oraclechain.pocketeos.modules.coindetails;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.SparkLinesBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean2;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.HashMap;
import org.json.JSONObject;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class CoinDetailsPresenter2 extends BasePresent<CoinDetailsView2> {
    private Context mContext;

    public CoinDetailsPresenter2(Context context) {
        this.mContext = context;
    }

    public void getTransferHistoryData(PostChainHistoryBean postChainHistoryBean) {
        HttpUtils.postRequest(BaseUrl.HTTP_get_transaction_history, mContext, new Gson().toJson(postChainHistoryBean), new JsonCallback<ResponseBean<TransferHistoryBean2.DataBeanX>>() {
            @Override
            public void onSuccess(Response<ResponseBean<TransferHistoryBean2.DataBeanX>> response) {
                OkLogger.i("===========>"+response.body().code);
                if (response.body().code == 0) {
                    view.getTransferHistoryDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void getSparklinesData(String token_symbol) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("token_symbol", token_symbol);
        String json = new JSONObject(hashMap).toString();
        HttpUtils.postRequest(BaseUrl.HTTP_get_sparklines, mContext, json, new JsonCallback<ResponseBean<SparkLinesBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<SparkLinesBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getSparklinesData(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}
