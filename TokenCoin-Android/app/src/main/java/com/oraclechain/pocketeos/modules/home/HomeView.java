package com.oraclechain.pocketeos.modules.home;

import com.oraclechain.pocketeos.base.BaseView;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;

import java.util.List;

/**
 * Created by pocketEos on 2018/1/18.
 */

public interface HomeView extends BaseView {

    void getAccountDetailsDataHttp(List<AccountWithCoinBean2> mAccountWithCoinBeen);

    void getDataHttpFail(String msg);
}
