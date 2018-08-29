package com.oraclechain.pocketeos.modules.pairtransaction;

import com.oraclechain.pocketeos.base.BaseView;
//import com.oraclechain.pocketeos.bean.AccountDetailsBean2;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.PairTransferHistoryBean;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface PairTransactionView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);
    //void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);
    void getAccountDetailsDataHttp(List<AccountWithCoinBean2> accountWithCoinBeens);
    void getPairTransactionHistoryDataHttp(PairTransferHistoryBean.DataBeanX pairTransferHistoryBean);
    void getDataHttpFail(String msg);
}
