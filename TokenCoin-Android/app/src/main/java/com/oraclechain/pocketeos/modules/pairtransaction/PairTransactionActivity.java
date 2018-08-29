package com.oraclechain.pocketeos.modules.pairtransaction;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;
import com.oraclechain.pocketeos.bean.CoinRateBean;
import com.oraclechain.pocketeos.bean.PairBean;
import com.oraclechain.pocketeos.bean.PairTransactionMessageBean;
import com.oraclechain.pocketeos.bean.PairTransferHistoryBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.TransferEosMessageBean;
import com.oraclechain.pocketeos.blockchain.EosDataManger;
import com.oraclechain.pocketeos.blockchain.PairDataManger;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;
import com.oraclechain.pocketeos.view.popupwindow.BasePopupWindow;
import com.oraclechain.pocketeos.view.textwatcher.TransferMoneyTextWatcher;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.ljp.permission.PermissionItem;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

//资产转账页面
public class PairTransactionActivity extends BaseAcitvity<PairTransactionView, PairTransactionPresenter> implements PairTransactionView {


    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.img_right)
    ImageView mImgRight;
     // @BindView(R.id.property_person)
     // ClearEditText mPropertyPerson;
    // @BindView(R.id.go_friends_list)
    // ImageView mGoFriendsList;
    @BindView(R.id.take_property_number)
    ClearEditText mTakePropertyNumber;
    @BindView(R.id.can_use_property)
    TextView mCanUseProperty;
    @BindView(R.id.rmb_property)
    TextView mRmbProperty;
    @BindView(R.id.take_rmb_property)
    TextView mTakeRmbProperty;
    @BindView(R.id.sure_go)
    Button mGo;
    @BindView(R.id.recycle_transferaccounts_history)
    RecyclerView mRecycleTransferaccountsHistory;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.spring)
    SpringView mSpring;
    // @BindView(R.id.leave_message)
    // ClearEditText mLeaveMessage;

    Boolean isSHow = false;
    BasePopupWindow basePopupWindow;
    Boolean isSHow1 = false;
    BasePopupWindow basePopupWindow1;
    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();
    private List<String> mCoinList = new ArrayList<>();

    private EmptyWrapper mHistoryAdapter;
    private int size = 10; //每页加载的数量
    private PostChainHistoryBean mPostChainHistoryBean = new PostChainHistoryBean();


    //private AccountWithCoinBean eos, oct;//选择账号之后重新获取最新数据信息
    private int skip_seq = 0;
    private BigDecimal coinRate;//资产汇率
    private String userPassword = null;
    private String account ;
    private String pairName = "lskeos";
    private String operate ;
    private PairBean mPair = new PairBean();
    private List<PairTransferHistoryBean.DataBeanX.ActionBean> mDataBeanList = new ArrayList<>();//交易历史


    @Override
    protected int getLayoutId() {
        return R.layout.activity_pair_transaction;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(activity);

        account  = getIntent().getStringExtra("account");
        pairName = getIntent().getStringExtra("pairname");
        operate  = getIntent().getStringExtra("operate");
        mPair    = getIntent().getParcelableExtra("pair");

        if(operate.equals("buy"))
           mGo.setText( getString(R.string.sure_go) + getString(R.string.go_buy) );
        else
           mGo.setText( getString(R.string.sure_go) + getString(R.string.go_sell) ); 

        // if (getIntent().getStringExtra("from").equals("qrcode")) {
        //     QrCodeMakeCollectionBean qrCodeMakeCollectionBean = getIntent().getParcelableExtra("info");
        //     mPropertyPerson.setText(qrCodeMakeCollectionBean.getAccount_name());
        //     mPropertyPerson.setSelection(mPropertyPerson.getText().length());
        //     mTakePropertyNumber.setText(qrCodeMakeCollectionBean.getMoney());
        //     //mSwitchProperty.setText(qrCodeMakeCollectionBean.getCoin());
        //     mGoTransferAccounts.setBackgroundColor(getResources().getColor(R.color.theme_color));
        // } else if (getIntent().getStringExtra("from").equals("frienddetails")) {
        //     mPropertyPerson.setText(getIntent().getStringExtra("getmoneyperson"));
        //     mPropertyPerson.setSelection(mPropertyPerson.getText().length());
        // }

        setCenterTitle( pairName );
        setRightImg(true);
        mImgRight.setImageDrawable(getResources().getDrawable(R.mipmap.scan));


        LinearLayoutManager layoutManager = new LinearLayoutManager(PairTransactionActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleTransferaccountsHistory.setLayoutManager(layoutManager);
        mRecycleTransferaccountsHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));

        //系统刷新
        mSpring.setFooter(new AliFooter(getContext()));
        mSpring.setGive(SpringView.Give.BOTTOM);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mSpring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                mPostChainHistoryBean.setSkip_seq(mDataBeanList.size());
                presenter.getPairTransactionHistoryData(mPostChainHistoryBean);
            }
        });

    }

    @Override
    protected void initData() {
        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);

        presenter.getAccountDetailsData(account);

        mPostChainHistoryBean.setAccount_name(account);
        mPostChainHistoryBean.setSkip_seq(-20);
        mPostChainHistoryBean.setNum_seq(-1);
        presenter.getPairTransactionHistoryData(mPostChainHistoryBean);


        mHistoryAdapter = new EmptyWrapper(AdapterManger.getPairDetailsHistoryAdapter(this, mDataBeanList, mPair));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);
        mRecycleTransferaccountsHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initEvent() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideProgress();
        if (basePopupWindow != null) {
            basePopupWindow.dismiss();
        }
        if (basePopupWindow1 != null) {
            basePopupWindow1.dismiss();
        }

    }

    @Override
    public PairTransactionPresenter initPresenter() {
        return new PairTransactionPresenter(PairTransactionActivity.this);
    }

    @Override
    public void getCoinRateDataHttp(CoinRateBean.DataBean coinRateBean) {//获取资产汇率
        coinRate = coinRateBean.getPrice_cny();
//        mTakePropertyNumber.addTextChangedListener(new TransferMoneyTextWatcher(mTakePropertyNumber, mTakeRmbProperty, coinRate, mPropertyPerson, mGoTransferAccounts));//限制金额最多为小数点后面四位
//        if (mTakePropertyNumber.getText().toString().trim().length() != 0) {
//            mTakeRmbProperty.setText("≈" + StringUtils.addComma(BigDecimalUtil.multiply(BigDecimal.valueOf(Double.parseDouble(mTakePropertyNumber.getText().toString())), coinRate, 4) + "") + "CNY");
//        }
    }

    @Override
    public void getAccountDetailsDataHttp(List<AccountWithCoinBean2> accountWithCoinBeens) {
//        if (accountWithCoinBeens.size() != 0) {
//            for (AccountWithCoinBean2 accountWithCoinBean : accountWithCoinBeens) {
//                if(accountWithCoinBean.getCoinName().equals(cointype.toUpperCase())){
//                    mCanUseProperty.setText(StringUtils.addComma(accountWithCoinBean.getCoinNumber()) + " " + accountWithCoinBean.getCoinName());
//                    mRmbProperty.setText("≈" + StringUtils.addComma(accountWithCoinBean.getCoinForCny()) + " CNY");
//                    break;
//                }
//            }
//        }
    }

    @Override
    public void getPairTransactionHistoryDataHttp(PairTransferHistoryBean.DataBeanX transferHistoryBean) {
        hideProgress();
        mSpring.onFinishFreshAndLoad();
        skip_seq += transferHistoryBean.getActions().size();
        for (int i = 0; i < transferHistoryBean.getActions().size(); i++) {
            if (transferHistoryBean.getActions().get(i).getData().getPair().equals(pairName)) {
                if (transferHistoryBean.getActions().get(i).getData().getFrom().contains(account)) {
                    PairTransferHistoryBean.DataBeanX.ActionBean itemdata = transferHistoryBean.getActions().get(i);
                    mDataBeanList.add(itemdata);
                }
            }
        }
        Collections.reverse(mDataBeanList);
        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        mSpring.onFinishFreshAndLoad();
        hideProgress();
        toast(msg);
    }

    //@OnClick({R.id.img_right, R.id.go_friends_list, R.id.go_transfer_accounts})
    @OnClick({R.id.sure_go})
    public void onViewClicked(View view) {
        if (KeyBoardUtil.isSoftInputShow(this)) {
            KeyBoardUtil.getInstance(this).hide();
        }
        switch (view.getId()) {
            // case R.id.img_right:
            //     List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
            //     permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.camer), R.drawable.permission_ic_camera));
            //     if (Utils.getPermissions(permissonItems, getString(R.string.open_camer_scan))) {
            //         Bundle bundle = new Bundle();
            //         bundle.putString("from", "import_account");
            //         ActivityUtils.next(TransferAccountsActivity.this, ScanCodeActivity.class, bundle, 100);
            //     }
            //     break;
            // case R.id.go_friends_list:
            //     Bundle bundle = new Bundle();
            //     bundle.putString("account", mPropertyPerson.getText().toString());
            //     ActivityUtils.next(TransferAccountsActivity.this, SwitchFriendActivity.class, bundle, 100);
            //     break;
            case R.id.sure_go:
                if (!TextUtils.isEmpty(mTakePropertyNumber.getText().toString().trim())) {
                    PasswordDialog mPasswordDialog = new PasswordDialog(PairTransactionActivity.this, new PasswordCallback() {
                        @Override
                        public void sure(final String password) {
                            if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                                userPassword = password;
                                showProgress();
                                //coinRate = BigDecimal.valueOf(Double.parseDouble(50.0 + ""));

                                String baseSymbol  = mPair.getBase_balance().split(" ")[1];
                                String quoteSymbol = mPair.getQuote_balance().split(" ")[1];

                                String from = account.trim();
                                String quant = StringUtils.addZero(mTakePropertyNumber.getText().toString().trim());
                                String symbol = StringUtils.addZero(0+"");
                                String feeto = "xiaoyu";
                                if(operate.equals("buy")){
                                   quant  = quant + " " + quoteSymbol;
                                   symbol = symbol + " " + baseSymbol;
                                }else{
                                   quant  = quant + " " + baseSymbol;
                                   symbol = symbol + " " + quoteSymbol;
                                }
                                new PairDataManger(PairTransactionActivity.this, userPassword).pushAction(
                                            new Gson().toJson(new PairTransactionMessageBean(from, quant, symbol, feeto)),
                                                              operate,
                                                              account.trim());
                            }

                        }

                        @Override
                        public void cancle() {
                        }
                    });
                    mPasswordDialog.setCancelable(true);
                    mPasswordDialog.show();
                } else  {
                    toast(getString(R.string.input_property_number_info));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            // mPropertyPerson.setText(data.getStringExtra("account"));
            // mPropertyPerson.setSelection(mPropertyPerson.getText().length());
        }
        if (requestCode == 100 && resultCode == 300) {
            // mPropertyPerson.setText(data.getStringExtra("account"));
            // mPropertyPerson.setSelection(mPropertyPerson.getText().length());
   //         mTakePropertyNumber.setText(data.getStringExtra("money"));
            //mSwitchProperty.setText(data.getStringExtra("coin"));

//            if (data.getStringExtra("coin").equals("EOS")) {
//                mCanUseProperty.setText(eos.getCoinNumber());
//                mRmbProperty.setText("≈" + eos.getCoinForCny());
//                presenter.getCoinRateData("eos");
//            } else {
//                mCanUseProperty.setText(oct.getCoinNumber());
//                mRmbProperty.setText("≈" + oct.getCoinForCny());
//                presenter.getCoinRateData("oraclechain");
//            }
        }
    }

}
