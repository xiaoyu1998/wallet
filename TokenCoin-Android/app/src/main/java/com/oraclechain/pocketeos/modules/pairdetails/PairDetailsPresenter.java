package com.oraclechain.pocketeos.modules.pairdetails;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.AccountDetailsBean2;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;
import com.oraclechain.pocketeos.bean.PairBean;
import com.oraclechain.pocketeos.bean.PairsDetailsBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.SparkLinesBean;
import com.oraclechain.pocketeos.bean.PairTransferHistoryBean;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;
import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class PairDetailsPresenter extends BasePresent<PairDetailsView> {
    private Context mContext;

    public PairDetailsPresenter(Context context) {
        this.mContext = context;
    }

    public void getPairTransactionHistoryData(PostChainHistoryBean postChainHistoryBean) {
        HttpUtils.postRequest(BaseUrl.HTTP_get_pair_transaction_history, mContext, new Gson().toJson(postChainHistoryBean), new JsonCallback<ResponseBean<PairTransferHistoryBean.DataBeanX>>() {
            @Override
            public void onSuccess(Response<ResponseBean<PairTransferHistoryBean.DataBeanX>> response) {
                OkLogger.i("===========>"+response.body().code);
                if (response.body().code == 0) {
                    view.getPairTransactionHistoryDataHttp(response.body().data);
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

    public void getAccountDetailsData(final String name, final String pair1, final String pair2 ) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("account_name", name);
        String json = new JSONObject(hashMap).toString();
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_account, mContext, json, new JsonCallback<ResponseBean<AccountDetailsBean2>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AccountDetailsBean2>> response) {
                if (response.body().code == 0) {
                    if (response.body().data.getAccount_name().equals(name)) {
                        AccountWithCoinBean2 assetPair1 = new AccountWithCoinBean2();
                        AccountWithCoinBean2  assetPair2 = new AccountWithCoinBean2();

                        for (int i = 0; i < response.body().data.getAccount_assets().size(); i++) {
                            String coin[] = response.body().data.getAccount_assets().get(i).getAsset().split(" ");
                            if(coin[1].equals(pair1)){
                                assetPair1.setContract(response.body().data.getAccount_assets().get(i).getContract());
                                assetPair1.setCoinName(coin[1]);
                                assetPair1.setCoinNumber(coin[0]);
                                assetPair1.setCoinForCny(Float.parseFloat(coin[0])*50 + "");
                                assetPair1.setCoinImg("");
                                assetPair1.setCoinUpsAndDowns("+5.0%");
                                assetPair1.setCoinPriceCny("50");
                            }

                            if(coin[1].equals(pair2)){
                                assetPair2.setContract(response.body().data.getAccount_assets().get(i).getContract());
                                assetPair2.setCoinName(coin[1]);
                                assetPair2.setCoinNumber(coin[0]);
                                assetPair2.setCoinForCny(Float.parseFloat(coin[0])*50 + "");
                                assetPair2.setCoinImg("");
                                assetPair2.setCoinUpsAndDowns("+5.0%");
                                assetPair2.setCoinPriceCny("50");
                            }
                        }
                        view.getAccountDetailsDataHttp(assetPair1 , assetPair2);

                    }
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void getPairsDetailsData(final List<String> pairs ) {

        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        hashMap.put("pairs", pairs);
        //String json = new JSONObject(hashMap).toString();
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_pairs, mContext, new Gson().toJson(hashMap), new JsonCallback<ResponseBean<PairsDetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<PairsDetailsBean>> response) {
                if (response.body().code == 0) {
                       List<PairBean> pairDetailsBean = new ArrayList<>();
                       for (int i = 0; i < response.body().data.getPairs().size(); i++) {

                            PairBean pair = new PairBean();
                            PairsDetailsBean.Pair p = response.body().data.getPairs().get(i);
                            pair.setSupply(p.getSupply());
                            pair.setBase_balance(p.getBase().getBalance());
                            pair.setBase_weight(p.getBase().getWeight());
                            pair.setQuote_balance(p.getQuote().getBalance());
                            pair.setQuote_weight(p.getQuote().getWeight());
                            pair.setFee_amount(p.getFee_amount());
                            pair.setToken_price_pair(p.getToken_price_pair());
                            pair.setToken_price_usd(p.getToken_price_usd());
                            pair.setToken_price_cny(p.getToken_price_cny());
                            pair.setToken_ups_and_downs("+5.0%");
                            pairDetailsBean.add(pair);
                       }
                       view.getPairsDetailsDataHttp(pairDetailsBean);

                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}
