package com.oraclechain.pocketeos.modules.home;

import android.content.Context;

import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.AccountDetailsBean2;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;
import com.oraclechain.pocketeos.net.HttpUtils;
import com.oraclechain.pocketeos.bean.ResponseBean;
import com.oraclechain.pocketeos.net.callbck.JsonCallback;
import com.oraclechain.pocketeos.utils.RegexUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

/**
 * Created by pocketEos on 2018/1/18.
 */

public class HomePresenter extends BasePresent<HomeView> {
    private Context mContext;

    public HomePresenter(Context context) {
        this.mContext = context;
    }
    public void getAccountDetailsData(final String name ) {

        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("account_name", name);
        String json = new JSONObject(hashMap).toString();
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_account, mContext, json, new JsonCallback<ResponseBean<AccountDetailsBean2>>() {
            @Override
            public void onSuccess(Response<ResponseBean<AccountDetailsBean2>> response) {
                if (response.body().code == 0) {
                    if (response.body().data.getAccount_name().equals(name)) {
                        // List<AccountWithCoinBean> accountWithCoinBeens = new ArrayList<>();
                        // AccountWithCoinBean  eos = new AccountWithCoinBean();
                        // eos.setCoinName("EOS");
                        // eos.setCoinForCny(RegexUtil.subZeroAndDot(response.body().data.getEos_balance_cny()));
                        // eos.setCoinNumber(RegexUtil.subZeroAndDot(response.body().data.getEos_balance()));
                        // eos.setCoinImg(response.body().data.getAccount_icon());
                        // eos.setEos_market_cap_usd(response.body().data.getEos_market_cap_usd());
                        // eos.setEos_market_cap_cny(response.body().data.getEos_market_cap_cny());
                        // eos.setEos_price_cny(response.body().data.getEos_price_cny());
                        // if (response.body().data.getEos_price_change_in_24h().contains("-")) {
                        //     eos.setCoinUpsAndDowns(response.body().data.getEos_price_change_in_24h() + "%");
                        // } else {
                        //     eos.setCoinUpsAndDowns("+" + response.body().data.getEos_price_change_in_24h() + "%");
                        // }
                        // accountWithCoinBeens.add(eos);
                        // AccountWithCoinBean oct = new AccountWithCoinBean();
                        // oct.setCoinName("OCT");
                        // oct.setCoinForCny(RegexUtil.subZeroAndDot(response.body().data.getOct_balance_cny()));
                        // oct.setCoinNumber(RegexUtil.subZeroAndDot(response.body().data.getOct_balance()));
                        // oct.setCoinImg(response.body().data.getAccount_icon());
                        // oct.setOct_market_cap_cny(response.body().data.getOct_market_cap_cny());
                        // oct.setOct_market_cap_usd(response.body().data.getOct_market_cap_usd());
                        // oct.setOct_price_cny(response.body().data.getOct_price_cny());
                        // if (response.body().data.getOct_price_change_in_24h().contains("-")) {
                        //     oct.setCoinUpsAndDowns(response.body().data.getOct_price_change_in_24h() + "%");
                        // } else {
                        //     oct.setCoinUpsAndDowns("+" +response.body().data.getOct_price_change_in_24h() + "%");
                        // }
                        // accountWithCoinBeens.add(oct);
                        // view.getAccountDetailsDataHttp(accountWithCoinBeens);

                        List<AccountWithCoinBean2> accountWithCoinBeens = new ArrayList<>();
                        for (int i = 0; i < response.body().data.getAccount_assets().size(); i++) {

                            String coin[] = response.body().data.getAccount_assets().get(i).getAsset().split(" ");
                            AccountWithCoinBean2  asset = new AccountWithCoinBean2();
                            asset.setContract(response.body().data.getAccount_assets().get(i).getContract());
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

                    }
                } else {
                    view.getDataHttpFail(response.body().message);
                }
            }
        });
    }
}

