package com.oraclechain.pocketeos.modules.transaction.transferaccounts;

import com.oraclechain.pocketeos.base.BaseView;
//import com.oraclechain.pocketeos.bean.AccountDetailsBean2;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean2;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;

import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface TransferAccountsView extends BaseView {
    void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean);

    //void getAccountDetailsDataHttp(AccountDetailsBean accountDetailsBean);
    void getAccountDetailsDataHttp(List<AccountWithCoinBean2> accountWithCoinBeens);

    void getTransferHistoryDataHttp(TransferHistoryBean2.DataBeanX transferHistoryBean);

    void getDataHttpFail(String msg);
}
