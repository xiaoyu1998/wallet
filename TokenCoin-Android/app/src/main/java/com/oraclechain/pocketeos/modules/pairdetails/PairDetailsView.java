package com.oraclechain.pocketeos.modules.pairdetails;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.SparkLinesBean;
import com.oraclechain.pocketeos.bean.PairTransferHistoryBean;
import com.oraclechain.pocketeos.bean.PairBean;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;
import java.util.List;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface PairDetailsView extends BaseView {
    void getPairTransactionHistoryDataHttp(PairTransferHistoryBean.DataBeanX pairTransferHistoryBean);
    void getSparklinesData(SparkLinesBean.DataBean dataBean);
    void getDataHttpFail(String msg);
    void getAccountDetailsDataHttp(AccountWithCoinBean2  asset1, AccountWithCoinBean2  asset2);
    void getPairsDetailsDataHttp(List<PairBean> pairs);
}
