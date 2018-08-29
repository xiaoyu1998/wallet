package com.oraclechain.pocketeos.modules.market;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.PairBean;

import java.util.List;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface MarketView extends BaseView {
    void getPairsDetailsDataHttp(List<PairBean> pairs);
    void getDataHttpFail(String msg);
}
