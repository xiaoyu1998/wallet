package com.oraclechain.pocketeos.modules.market;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.liaoinstan.springview.widget.SpringView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.MultiItemTypeAdapter;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.modules.account.accountdetails.AccountDetailsActivity;
import com.oraclechain.pocketeos.modules.coindetails.CoinDetailsActivity;
import com.oraclechain.pocketeos.modules.leftdrawer.usercenter.UserCenterActivity;
import com.oraclechain.pocketeos.modules.pairdetails.PairDetailsActivity;
import com.oraclechain.pocketeos.modules.scancode.ScanCodeActivity;
import com.oraclechain.pocketeos.modules.switchusernumber.SwitchUserNumberActivity;
import com.oraclechain.pocketeos.modules.transaction.makecollections.MakeCollectionsActivity;
import com.oraclechain.pocketeos.modules.transaction.redpacket.makeredpacket.RedPacketActivity;
import com.oraclechain.pocketeos.modules.transaction.transferaccounts.TransferAccountsActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.DensityUtil;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.utils.Utils;
import com.oraclechain.pocketeos.view.AppDefeatHeadView;
import com.oraclechain.pocketeos.view.CircleImageView;
import com.oraclechain.pocketeos.view.MyScrollview;
import com.oraclechain.pocketeos.bean.PairsDetailsBean;
import com.oraclechain.pocketeos.bean.PairBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.ljp.permission.PermissionItem;

//import static com.oraclechain.pocketeos.R.id.spring2;

/**
 * 首页
 */
public class MarketFragment extends BaseFragment<MarketView, MarketPresenter> implements MarketView, MyScrollview.ScrollViewListener {

    // @BindView(R.id.iv_scan)
    // ImageView mIvScan;

    @BindView(R.id.scrollView)
    MyScrollview mScrollView;
    @BindView(R.id.title)
    RelativeLayout mTitle;
    @BindView(R.id.recycle_icon)
    RecyclerView mRecycleIcon;
    // @BindView(R.id.user_img)
    // CircleImageView mUserImg;
    @BindView(R.id.spring)
    SpringView mSpring;

    private Openleft mOpenleft = null;
    private TranslateAnimation mShowAction, mHiddenAction;
    private int topToCardView;//到CardView的距离
    private int showNumber;
    private int hintNumber;
    //private List<AccountWithCoinBean2> mAccountWithCoinBeen = new ArrayList<>();
    private List<PairBean> mPairsDetailsBean = new ArrayList<>();
    private CommonAdapter mPairsAdapter;
    private Boolean isfromWithData = false;
    private String account;
    private List<String> pairs = new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();
        //mUserName.setText(MyApplication.getInstance().getUserBean().getWallet_name() + getString(R.string.wallet));
        //MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);
        if (!isfromWithData) {
            //presenter.getAccountDetailsData(userAccountNumber);
            pairs.clear();
            pairs.add("LSKEOS");
            pairs.add("ASTEOS");
            pairs.add("IOTAEOS");
            pairs.add("DASHEOS");
            pairs.add("USDTEOS");
            pairs.add("FILEOS");
            presenter.getPairsDetailsData(pairs);
        }
    }

    @Override
    public MarketPresenter initPresenter() {
        return new MarketPresenter(getActivity());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        //系统刷新
        mSpring.setHeader(new AppDefeatHeadView(getContext()));
        mSpring.setGive(SpringView.Give.TOP);
        mSpring.setType(SpringView.Type.FOLLOW);
        mSpring.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                presenter.getPairsDetailsData(pairs);
            }

            @Override
            public void onLoadmore() {
                mSpring.onFinishFreshAndLoad();
            }
        });


        // mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
        //         Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
        //         -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        // mShowAction.setDuration(300);

        // mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
        //         0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
        //         Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
        //         -1.0f);
        // mHiddenAction.setDuration(300);
        // topToCardView = DensityUtil.dip2px(getActivity(), 260);

        account = MyApplication.getInstance().getUserBean().getWallet_main_account();
        // MyApplication.getInstance().showCirImage(MyApplication.getInstance().getUserBean().getWallet_img(), mUserImg);

    }

    @Override
    protected void initData() {
        ShowDialog.showDialog(getActivity(), "", true, null);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleIcon.setLayoutManager(layoutManager);
        mPairsAdapter = AdapterManger.getPairsAdapter(getActivity(), mPairsDetailsBean);
        mPairsAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("pair", mPairsDetailsBean.get(position));
                bundle.putString("account", account);
                ActivityUtils.next(getActivity(), PairDetailsActivity.class, bundle);
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        mRecycleIcon.setAdapter(mPairsAdapter);
    }

    @Override
    public void initEvent() {
        // mIvSeting.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View v) {
        //         mOpenleft.open(1);
        //     }
        // });
        //mScrollView.setScrollViewListener(this);

    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(false, 0f).init();
        ImmersionBar.setTitleBar(getActivity(), mTitle);
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_market;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.statusBarDarkFont(false, 0f).init();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            isfromWithData = true;
            presenter.getPairsDetailsData(pairs);
        } else if (requestCode == 100 && resultCode == 300) {
            isfromWithData = true;
            presenter.getPairsDetailsData(pairs);
        } else {
            isfromWithData = false;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        //mOpenleft = (Openleft) activity;
        super.onAttach(activity);
    }

    @Override
    public void getPairsDetailsDataHttp(List<PairBean> pairs){
        ShowDialog.dissmiss();
        mSpring.onFinishFreshAndLoad();
        if (pairs.size() != 0){
            mPairsDetailsBean.clear();
            for (PairBean pair : pairs){
                mPairsDetailsBean.add(pair);
            }           
            mPairsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mSpring.onFinishFreshAndLoad();
        toast(msg);
    }

    @Override
    public void onScrollChanged(MyScrollview scrollView, int x, int y, int oldx, int oldy) {
        // if (y < topToCardView) {
        //     showNumber = 0;
        //     if (hintNumber != 1 && mHomeTitle.getVisibility() == View.VISIBLE) {
        //         mHomeTitle.startAnimation(mHiddenAction);
        //         mHiddenAction.setAnimationListener(new Animation.AnimationListener() {
        //             @Override
        //             public void onAnimationStart(Animation animation) {

        //             }
        //             @Override
        //             public void onAnimationEnd(Animation animation) {
        //                 hintNumber = 1;
        //             }

        //             @Override
        //             public void onAnimationRepeat(Animation animation) {

        //             }
        //         });
        //     }
        // } else {
        //     hintNumber = 0;
        //     if (showNumber != 1 && mHomeTitle.getVisibility() == View.GONE) {
        //         mHomeTitle.startAnimation(mShowAction);

        //         mShowAction.setAnimationListener(new Animation.AnimationListener() {
        //             @Override
        //             public void onAnimationStart(Animation animation) {

        //             }

        //             @Override
        //             public void onAnimationEnd(Animation animation) {
        //                 showNumber = 1;
        //             }

        //             @Override
        //             public void onAnimationRepeat(Animation animation) {

        //             }
        //         });
        //     }
        // }
    }

     //@OnClick({R.id.iv_message, R.id.iv_scan, R.id.transfer_accounts, R.id.make_collections, R.id.red_packet})
     @OnClick({ R.id.iv_scan})
    public void onViewClicked(View view) {
        final Bundle bundle = new Bundle();
        switch (view.getId()) {
            // case R.id.iv_message:
            //     break;
            case R.id.iv_scan:
                List<PermissionItem> permissonItems = new ArrayList<PermissionItem>();
                permissonItems.add(new PermissionItem(Manifest.permission.CAMERA, getString(R.string.camer), R.drawable.permission_ic_camera));
                if (Utils.getPermissions(permissonItems, getString(R.string.open_camer_scan))) {
                    bundle.putString("from", "home");
                    ActivityUtils.next(getActivity(), ScanCodeActivity.class, bundle);
                }
                break;
        }
    }

    public interface Openleft {
        void open(int tag);
    }
}
