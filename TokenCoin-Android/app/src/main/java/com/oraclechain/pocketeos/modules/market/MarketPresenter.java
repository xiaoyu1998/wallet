package com.oraclechain.pocketeos.modules.market;

import android.content.Context;

import com.google.gson.Gson;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketeos.base.BasePresent;
import com.oraclechain.pocketeos.base.BaseUrl;
import com.oraclechain.pocketeos.bean.PairsDetailsBean;
import com.oraclechain.pocketeos.bean.PairBean;
import com.oraclechain.pocketeos.bean.PairBean;
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

public class MarketPresenter extends BasePresent<MarketView> {
    private Context mContext;

    public MarketPresenter(Context context) {
        this.mContext = context;
    }
    public void getPairsDetailsData(final List<String> pairs ) {

        HashMap<String, List<String>> hashMap = new HashMap<String, List<String>>();
        hashMap.put("pairs", pairs);
        //String json = new JSONObject(hashMap).toString();
        HttpUtils.postRequest(BaseUrl.HTTP_eos_get_pairs, mContext, new Gson().toJson(hashMap), new JsonCallback<ResponseBean<PairsDetailsBean>>() {
            @Override
            public void onSuccess(Response<ResponseBean<PairsDetailsBean>> response) {
                if (response.body().code == 0) {
                    // if (response.body().data.getAccount_name().equals(name)) {
                    //     List<AccountWithCoinBean2> accountWithCoinBeens = new ArrayList<>();
                    //     for (int i = 0; i < response.body().data.getAccount_assets().size(); i++) {

                    //         String coin[] = response.body().data.getAccount_assets().get(i).split(" ");
                    //         AccountWithCoinBean2  asset = new AccountWithCoinBean2();
                    //         asset.setCoinName(coin[1]);
                    //         asset.setCoinNumber(coin[0]);
                    //         asset.setCoinForCny(Float.parseFloat(coin[0])*50 + "");
                    //         asset.setCoinImg("");
                    //         asset.setCoinUpsAndDowns("+5.0%");
                    //         asset.setCoinPriceCny("50");
                    //         accountWithCoinBeens.add(asset);
                    //     }
                    //     view.getAccountDetailsDataHttp(accountWithCoinBeens);
                    // }
                       List<PairBean> pairDetailsBean = new ArrayList<>();
                       for (int i = 0; i < response.body().data.getPairs().size(); i++) {

                            PairBean pair = new PairBean();
                            PairsDetailsBean.Pair p = response.body().data.getPairs().get(i);

                            pair.setSupply(p.getSupply());
                            // pair.setBase(p.getBase());
                            // pair.setQuote(p.getQuote());

//                            PairBean.Connector base = pair.new Connector();
//                            base.setBalance(p.getBase().getBalance());
//                            base.setWeight(p.getBase().getWeight());
//
//                            PairBean.Connector quote = pair.new Connector();
//                            quote.setBalance(p.getQuote().getBalance());
//                            quote.setWeight(p.getQuote().getWeight());

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

