package com.oraclechain.pocketeos.modules.pairtransaction;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.utils.OkLogger;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.AccountDetailsBean2;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.PairTransferHistoryBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean2;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */

public class PairTransactionPresenter extends BasePresent<PairTransactionView> {
    private Context mContext;

    public PairTransactionPresenter(Context context) {
        this.mContext = context;
    }

    public void getCoinRateData(String coinmarket_id) {//获取token汇率
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("coinmarket_id", coinmarket_id);
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_coin_rate, mContext, hashMap, new JsonCallback<ResponseBean<CoinRateBean.DataBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<CoinRateBean.DataBean>> response) {
                if (response.body().code == 0) {
                    view.getCoinRateDataHttp(response.body().data);
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }

    public void getAccountDetailsData(final String name) {//动态获取账号资产信息
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("account_name", name);
        String json = new JSONObject(hashMap).toString();
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_account, mContext, json, new JsonCallback<ResponseBean<AccountDetailsBean2>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AccountDetailsBean2>> response) {
                if (response.body().code == 0) {
                    //view.getAccountDetailsDataHttp(response.body().data);
                    List<AccountWithCoinBean2> accountWithCoinBeens = new ArrayList<>();
                    for (int i = 0; i < response.body().data.getAccount_assets().size(); i++) {

                        String coin[] = response.body().data.getAccount_assets().get(i).getAsset().split(" ");
                        AccountWithCoinBean2  asset = new AccountWithCoinBean2();
                        asset.setCoinName(coin[1]);
                        asset.setCoinNumber(coin[0]);
                        asset.setCoinForCny(Float.parseFloat(coin[0])*50 + "");
                        asset.setCoinImg("");
                        asset.setCoinUpsAndDowns("+5.0%");
                        asset.setCoinPriceCny("50");

                        // asset.setEos_market_cap_usd("100");
                        // asset.setEos_market_cap_cny("100");
                        // asset.setEos_price_cny("50");
                        // asset.setOct_market_cap_usd("100");
                        // asset.setOct_market_cap_cny("100");
                        // asset.setOct_price_cny("300");

                        accountWithCoinBeens.add(asset);
                    }
                    view.getAccountDetailsDataHttp(accountWithCoinBeens);

                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
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

    public void pushAction(String contract, String action, String message, String scopes, String permissionAccount, String permissionName ){


        // can make
        String[] permissions = ( StringUtils.isEmpty(permissionAccount) || StringUtils.isEmpty( permissionName))
                ? null : new String[]{permissionAccount + "@" + permissionName };


  /*      addDisposable(
                mDataManager.pushAction(contract, action, message.replaceAll("\\r|\\n","")
                        , permissions)
                        .mergeWith( jsonObject -> mDataManager.addAccountHistory( getAccountListForHistory( contract, permissionAccount) ))
                        .subscribeOn( getSchedulerProvider().io())
                        .observeOn( getSchedulerProvider().ui())
                        .subscribeWith(new RxCallbackWrapper<PushTxnResponse>( this) {
                                           @Override
                                           public void onNext(PushTxnResponse result) {
                                               if (!isViewAttached()) return;

                                               getMvpView().showLoading(false);

                                               getMvpView().showResult( Utils.prettyPrintJson( result ), result.toString() );
                                           }
                                       }
                        )*/

    }


}
