package com.oraclechain.pocketeos.modules.pairdetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;
import com.oraclechain.pocketeos.bean.PairBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.SparkLinesBean;
import com.oraclechain.pocketeos.bean.PairTransferHistoryBean;
import com.oraclechain.pocketeos.modules.otherloginorshare.BaseUIListener;
import com.oraclechain.pocketeos.modules.pairtransaction.PairTransactionActivity;
import com.oraclechain.pocketeos.utils.ChartUtil;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.dialog.sharecoindetailsdialog.ShareCoinDetailsDialog;
import com.tencent.connect.common.Constants;
import com.tencent.connect.share.QQShare;
import com.tencent.connect.share.QzonePublish;
import com.tencent.connect.share.QzoneShare;
import com.tencent.tauth.Tencent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

//币种详情页
public class PairDetailsActivity extends BaseAcitvity<PairDetailsView, PairDetailsPresenter> implements PairDetailsView {

    @BindView(R.id.lineChart)
    LineChart mChart;
    List<Entry> yDataList = new ArrayList<>();// 数据源
    @BindView(R.id.img_right)
    ImageView mImgRight;
    @BindView(R.id.tokenbase_price)
    TextView mTokenBasePrice;
    @BindView(R.id.basetoken_price)
    TextView mBaseTokenPrice;

    @BindView(R.id.tokenbase)
    TextView mTokenBase;
    @BindView(R.id.tokenquote)
    TextView mTokenQuote;

    @BindView(R.id.go_buy)
    TextView mGoBuy;
    @BindView(R.id.go_sell)
    TextView mGoSell;
    @BindView(R.id.recycle_coin_history)
    RecyclerView mRecycleCoinHistory;
    @BindView(R.id.spring)
    SpringView mSpring;
    @BindView(R.id.coin_uoanddown)
    TextView mCoinUoanddown;
    @BindView(R.id.coin_upanddown_img)
    ImageView mCoinUpanddownImg;
    ShareCoinDetailsDialog dialog = null;
    private PairBean mPair = new PairBean();
    private List<PairTransferHistoryBean.DataBeanX.ActionBean> mDataBeanList = new ArrayList<>();//交易历史
    private EmptyWrapper mHistoryAdapter;
    private int size = 10; //每页加载的数量
    private PostChainHistoryBean mPostChainHistoryBean = new PostChainHistoryBean();
    private int skip_seq = 0;
    private String account;
    private String pairName;
    private AccountWithCoinBean2  assetPair1 = new AccountWithCoinBean2();
    private AccountWithCoinBean2  assetPair2 = new AccountWithCoinBean2();
    private List<String> pairs = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pair_detail;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

        LinearLayoutManager layoutManager = new LinearLayoutManager(PairDetailsActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleCoinHistory.setLayoutManager(layoutManager);
        mRecycleCoinHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));

        mPair   = getIntent().getParcelableExtra("pair");
        account = getIntent().getStringExtra("account");
        pairs.add(mPair.getSupply().split(" ")[1]);

        pairName = mPair.getSupply().split(" ")[1];
        setCenterTitle(pairName);
        setRightImg(true);
        mImgRight.setImageDrawable(getResources().getDrawable(R.mipmap.makecollectionshare));

        String tokenPrice = mPair.getToken_price_pair();
        mCoinUoanddown.setText(mPair.getToken_ups_and_downs() + getString(R.string.today));
        mTokenBasePrice.setText(StringUtils.addComma(tokenPrice));

        String baseTokenPrice = 1.0 / Double.parseDouble(tokenPrice) + "";
        mBaseTokenPrice.setText(StringUtils.addComma(baseTokenPrice ));

        //系统刷新
        mSpring.setFooter(new AliFooter(this));
        mSpring.setGive(SpringView.Give.BOTTOM);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mSpring.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                mPostChainHistoryBean.setSkip_seq(skip_seq);
                presenter.getPairTransactionHistoryData(mPostChainHistoryBean);
            }
        });
    }

    @Override
    protected void initData() {
        //给上面的X、Y轴数据源做假数据测试
        for (int i = 0; i < 20; i++) {
            float mult = 30 / 2f;
            float val = (float) (Math.random() * mult) + 50;
            yDataList.add(new Entry(i, val));
        }
        //显示图表,参数（ 上下文，图表对象数据，图表标题，曲线图例名称，坐标点击弹出提示框中数字单位）
        ChartUtil.showChart(this, mChart, yDataList, "", "", "");

        presenter.getPairsDetailsData(pairs);
        presenter.getAccountDetailsData(account, mPair.getBase_balance().split(" ")[1], mPair.getQuote_balance().split(" ")[1]);

        showProgress();
        presenter.getSparklinesData("EOS");

        //mPostChainHistoryBean.setAccount_name(mCoinUserNumber.getText().toString());
        mPostChainHistoryBean.setAccount_name(account);
        mPostChainHistoryBean.setSkip_seq(-20);
        mPostChainHistoryBean.setNum_seq(-1);
        presenter.getPairTransactionHistoryData(mPostChainHistoryBean);

        //mHistoryAdapter = new EmptyWrapper(AdapterManger.getCoinDetailsHistoryAdapter(this, mDataBeanList, mCoinUserNumber.getText().toString()));
        mHistoryAdapter = new EmptyWrapper(AdapterManger.getPairDetailsHistoryAdapter(this, mDataBeanList, mPair));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);
        mRecycleCoinHistory.setAdapter(mHistoryAdapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public PairDetailsPresenter initPresenter() {
        return new PairDetailsPresenter(PairDetailsActivity.this);
    }


    @Override
    public void getPairTransactionHistoryDataHttp(PairTransferHistoryBean.DataBeanX transferHistoryBean) {
        hideProgress();
        mSpring.onFinishFreshAndLoad();
        //skip_seq += transferHistoryBean.getActions().size();
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
    public void getSparklinesData(SparkLinesBean.DataBean dataBean) {
        MyApplication.getInstance().showImage(dataBean.getSparkline_token_png(), mCoinUpanddownImg);
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mSpring.onFinishFreshAndLoad();
        toast(msg);
    }

    @Override
    public void getPairsDetailsDataHttp(List<PairBean> pairs){
        ShowDialog.dissmiss();
        if(pairs.size() > 0) {
            mPair = pairs.get(0);

            String tokenPrice = mPair.getToken_price_pair();
            mCoinUoanddown.setText(mPair.getToken_ups_and_downs() + getString(R.string.today));
            mTokenBasePrice.setText(StringUtils.addComma(tokenPrice));

            String baseTokenPrice = 1.0 / Double.parseDouble(tokenPrice) + "";
            mBaseTokenPrice.setText(StringUtils.addComma(baseTokenPrice ));
        }
    }

    @Override
    public void getAccountDetailsDataHttp(AccountWithCoinBean2 asset1, AccountWithCoinBean2  asset2) {
        ShowDialog.dissmiss();
        mSpring.onFinishFreshAndLoad();

        assetPair1 = asset1;
        mTokenBase.setText(StringUtils.addComma(assetPair1.getCoinNumber())+ " " + assetPair1.getCoinName() );
        assetPair2 = asset2;
        mTokenQuote.setText(StringUtils.addComma(assetPair2.getCoinNumber()) + " " + assetPair2.getCoinName() );

    }

    //@OnClick({R.id.img_right, R.id.go_transfer_accounts, R.id.go_make_collections, R.id.go_red_packet})
    @OnClick({R.id.img_right, R.id.go_buy, R.id.go_sell})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.img_right://分享操作
                String desc = null;
                final Bundle params = new Bundle();
                break;
            case R.id.go_buy:
                bundle.putString("account", account);
                bundle.putString("operate", "buy");
                bundle.putParcelable("pair",mPair);
                bundle.putString("pairname",pairName);
                ActivityUtils.next(PairDetailsActivity.this, PairTransactionActivity.class, bundle, 100);
                break;
            case R.id.go_sell:
                bundle.putString("account", account);
                bundle.putString("operate", "sell");
                bundle.putParcelable("pair",mPair);
                bundle.putString("pairname",pairName);
                ActivityUtils.next(PairDetailsActivity.this, PairTransactionActivity.class, bundle,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        hideProgress();
        if (requestCode == Constants.REQUEST_QQ_SHARE || requestCode == Constants.REQUEST_QZONE_SHARE || requestCode == Constants.REQUEST_OLD_SHARE) {
            Tencent.handleResultData(data, new BaseUIListener(PairDetailsActivity.this, true));
        }
        if (requestCode == 100 && resultCode == 300) {
            presenter.getPairsDetailsData(pairs);
            presenter.getAccountDetailsData(account, mPair.getBase_balance().split(" ")[1], mPair.getQuote_balance().split(" ")[1]);
            presenter.getPairTransactionHistoryData(mPostChainHistoryBean);
        }
    }

}
