package com.oraclechain.pocketeos.modules.coindetails;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.SparkLinesBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean2;

/**
 * Created by pocketEos on 2017/12/26.
 */
public interface CoinDetailsView2 extends BaseView {


    void getTransferHistoryDataHttp(TransferHistoryBean2.DataBeanX transferHistoryBean);

    void getSparklinesData(SparkLinesBean.DataBean dataBean);


    void getDataHttpFail(String msg);
}
