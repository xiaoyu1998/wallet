package com.oraclechain.pocketeos.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.base.ViewHolder;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean2;
import com.oraclechain.pocketeos.bean.AccountWithCoinBean;
import com.oraclechain.pocketeos.bean.CandyUserTaskBean;
import com.oraclechain.pocketeos.bean.CoinBean;
import com.oraclechain.pocketeos.bean.DappBean;
import com.oraclechain.pocketeos.bean.DappCommpanyBean;
import com.oraclechain.pocketeos.bean.FriendsListInfoBean;
import com.oraclechain.pocketeos.bean.HotEquitiesBean;
import com.oraclechain.pocketeos.bean.MessageCenterBean;
import com.oraclechain.pocketeos.bean.NewsBean;
import com.oraclechain.pocketeos.bean.PairTransferHistoryBean;
import com.oraclechain.pocketeos.bean.PelistBean;
import com.oraclechain.pocketeos.bean.QuestionListBean;
import com.oraclechain.pocketeos.bean.RedPacketDetailsBean;
import com.oraclechain.pocketeos.bean.RedPacketHistoryBean;
import com.oraclechain.pocketeos.bean.SuggestionBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean2;
import com.oraclechain.pocketeos.bean.UserBean;
import com.oraclechain.pocketeos.modules.dapp.dappcommpany.DappCommpanyDetailsActivity;
import com.oraclechain.pocketeos.modules.dapp.dappdetails.DappDetailsActivity;
import com.oraclechain.pocketeos.modules.dapp.paidanswer.questiondetails.QuestionDetailsActivity;
import com.oraclechain.pocketeos.modules.friendslist.friendsdetails.FriendsDetailsActivity;
import com.oraclechain.pocketeos.modules.news.newsdetails.WebNewsDetailsActivity;
import com.oraclechain.pocketeos.modules.transaction.redpacket.continueredpacket.ContinueRdPacketActivity;
import com.oraclechain.pocketeos.modules.transaction.redpacket.getredpacketdetails.GetRedPacketDetailsActivity;
import com.oraclechain.pocketeos.utils.DateUtils;
import com.oraclechain.pocketeos.utils.RegexUtil;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.view.CircleImageView;
import com.oraclechain.pocketeos.bean.PairsDetailsBean;
import com.oraclechain.pocketeos.bean.PairBean;

import java.text.ParseException;
import java.util.List;


/**
 * Created by pocketEos on 2018/3/14.
 */
public class AdapterManger {

    /**
     * The M common adapter.
     */
    static CommonAdapter mCommonAdapter;
    /**
     * The M selected pos.
     */
    static int mSelectedPos = 0;//保存当前选中的position 重点！默认选中第一个item

    /**
     * Gets coin adapter.
     *
     * @param context              the context
     * @param mPairsDetailsBean the m account with coin been
     * @return the coin adapter
     */
    public static CommonAdapter getPairsAdapter(final Context context, List<PairBean> mPairsDetailsBean) {
        mCommonAdapter = new CommonAdapter<PairBean>(context, R.layout.item_pair, mPairsDetailsBean) {
            @Override
            protected void convert(ViewHolder holder, PairBean pair, int position) {
                TextView textView = holder.getView(R.id.pair_token_ups_and_downs);
                textView.setText(pair.getToken_ups_and_downs());
                if (pair.getToken_ups_and_downs().contains("-")) {
                    textView.setBackgroundColor(context.getResources().getColor(R.color.down_color));
                } else {
                    textView.setBackgroundColor(context.getResources().getColor(R.color.up_color));
                }
                String supply[] = pair.getSupply().split(" ");
                holder.setText(R.id.pair_name, supply[1]);

                ImageView imageView = (ImageView) holder.getView(R.id.coin_img);
                if (supply[1].equals("IOTAEOS")) {
                    imageView.setImageResource(R.mipmap.iotaeos);
                } else if(supply[1].equals("LSKEOS")) {
                    imageView.setImageResource(R.mipmap.lskeos);
                } else if(supply[1].equals("DASHEOS")) {
                    imageView.setImageResource(R.mipmap.dasheos);
                } else if(supply[1].equals("USDTEOS")) {
                    imageView.setImageResource(R.mipmap.usdteos);
                } else if(supply[1].equals("FILEOS")) {
                    imageView.setImageResource(R.mipmap.fileos);
                } else {
                    imageView.setImageResource(R.mipmap.oct);
                }

                String tokenPrice = pair.getToken_price_pair();
                holder.setText(R.id.pair_token_price_pair, StringUtils.addComma(tokenPrice));

                holder.setText(R.id.pair_token_price_f, StringUtils.addComma(1.0 / Double.parseDouble(tokenPrice) + ""));
                //holder.setText(R.id.coin_number_for_cny, "≈" + StringUtils.addComma(accountWithCoinBean.getCoinForCny()) + " CNY");
                //holder.setText(R.id.coin_number, StringUtils.addComma(accountWithCoinBean.getCoinNumber()) + " " + accountWithCoinBean.getCoinName());
                holder.setText(R.id.pair_token_price_cny, "￥" + StringUtils.addComma(pair.getToken_price_cny()));
            }
        };
        return mCommonAdapter;
    }




    /**
     * Gets coin adapter.
     *
     * @param context              the context
     * @param mAccountWithCoinBeen the m account with coin been
     * @return the coin adapter
     */
    public static CommonAdapter getCoinAdapter2(final Context context, List<AccountWithCoinBean2> mAccountWithCoinBeen) {
        mCommonAdapter = new CommonAdapter<AccountWithCoinBean2>(context, R.layout.item_account_with_coin, mAccountWithCoinBeen) {
            @Override
            protected void convert(ViewHolder holder, AccountWithCoinBean2 accountWithCoinBean, int position) {
                TextView textView = holder.getView(R.id.coin_ups_and_downs);
                textView.setText(accountWithCoinBean.getCoinUpsAndDowns());
                if (accountWithCoinBean.getCoinUpsAndDowns().contains("-")) {
                    textView.setTextColor(context.getResources().getColor(R.color.down_color));
                } else {
                    textView.setTextColor(context.getResources().getColor(R.color.up_color));
                }
                holder.setText(R.id.coin_number_for_cny, "≈" + StringUtils.addComma(accountWithCoinBean.getCoinForCny()) + " CNY");

                ImageView imageView = (ImageView) holder.getView(R.id.coin_img);
                if (accountWithCoinBean.getCoinName().equals("EOS")) {
                    imageView.setImageResource(R.mipmap.eos);
                } else if(accountWithCoinBean.getCoinName().equals("BTC")) {
                    imageView.setImageResource(R.mipmap.btc);
                } else if(accountWithCoinBean.getCoinName().equals("ETH")) {
                    imageView.setImageResource(R.mipmap.eth);
                } else if(accountWithCoinBean.getCoinName().equals("FIL")) {
                    imageView.setImageResource(R.mipmap.fil);
                } else if(accountWithCoinBean.getCoinName().equals("USDT")) {
                    imageView.setImageResource(R.mipmap.usdt);
                } else if(accountWithCoinBean.getCoinName().equals("DASH")) {
                    imageView.setImageResource(R.mipmap.dash);
                } else if(accountWithCoinBean.getCoinName().equals("LSK")) {
                    imageView.setImageResource(R.mipmap.lsk);
                } else if(accountWithCoinBean.getCoinName().equals("IOTA")) {
                    imageView.setImageResource(R.mipmap.iota);
                } else {
                    imageView.setImageResource(R.mipmap.oct);
                }

                holder.setText(R.id.coin_number, StringUtils.addComma(accountWithCoinBean.getCoinNumber()) + " " + accountWithCoinBean.getCoinName());
                holder.setText(R.id.coin_one_for_cny, "￥" + StringUtils.addComma(accountWithCoinBean.getCoinPriceCny()));

                RelativeLayout ll = holder.getView(R.id.ll);
                if(accountWithCoinBean.getContract().equals("cactus.token")){
                    ll.setBackgroundColor(context.getResources().getColor(R.color.ivory));
                } else {
                    ll.setBackgroundColor(context.getResources().getColor(R.color.white));
                }
            }
        };
        return mCommonAdapter;
    }

     /**
     * Gets coin adapter.
     *
     * @param context              the context
     * @param mAccountWithCoinBeen the m account with coin been
     * @return the coin adapter
     */
    public static CommonAdapter getCoinAdapter(final Context context, List<AccountWithCoinBean> mAccountWithCoinBeen) {
        mCommonAdapter = new CommonAdapter<AccountWithCoinBean>(context, R.layout.item_account_with_coin, mAccountWithCoinBeen) {
            @Override
            protected void convert(ViewHolder holder, AccountWithCoinBean accountWithCoinBean, int position) {
                TextView textView = holder.getView(R.id.coin_ups_and_downs);
                textView.setText(accountWithCoinBean.getCoinUpsAndDowns());
                if (accountWithCoinBean.getCoinUpsAndDowns().contains("-")) {
                    textView.setTextColor(context.getResources().getColor(R.color.down_color));
                } else {
                    textView.setTextColor(context.getResources().getColor(R.color.up_color));
                }
                holder.setText(R.id.coin_number_for_cny, "≈" + StringUtils.addComma(accountWithCoinBean.getCoinForCny()) + " CNY");

                ImageView imageView = (ImageView) holder.getView(R.id.coin_img);
                if (accountWithCoinBean.getCoinName().equals("EOS")) {
                    imageView.setImageResource(R.mipmap.eos);
                    holder.setText(R.id.coin_number, StringUtils.addComma(accountWithCoinBean.getCoinNumber()) + " EOS");
                    holder.setText(R.id.coin_one_for_cny, "￥" + StringUtils.addComma(accountWithCoinBean.getEos_price_cny()));
                } else {
                    imageView.setImageResource(R.mipmap.oct);
                    holder.setText(R.id.coin_number, StringUtils.addComma(accountWithCoinBean.getCoinNumber()) + " OCT");
                    holder.setText(R.id.coin_one_for_cny, "￥" + StringUtils.addComma(accountWithCoinBean.getOct_price_cny()));
                }

            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets news adapter.
     *
     * @param context      the context
     * @param mNewInfoBeen the m new info been
     * @return the news adapter
     */
    public static CommonAdapter getNewsAdapter(final Context context, List<NewsBean.DataBean> mNewInfoBeen) {
        mCommonAdapter = new CommonAdapter<NewsBean.DataBean>(context, R.layout.item_news_info, mNewInfoBeen) {
            @Override
            protected void convert(ViewHolder holder, final NewsBean.DataBean newInfoBean, final int position) {
                holder.setText(R.id.news_title, newInfoBean.getTitle());
                holder.setText(R.id.news_desc, newInfoBean.getSummary());
                try {
                    holder.setText(R.id.news_time, DateUtils.getTime1(DateUtils.string2Data(newInfoBean.getCreateTime())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                MyApplication.getInstance().showImage(newInfoBean.getImageUrl(), (ImageView) holder.getView(R.id.news_img));
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("details", newInfoBean.getNewsUrl());
                        ActivityUtils.next((Activity) context, WebNewsDetailsActivity.class, bundle);
                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets coin type adapter.
     *
     * @param context   the context
     * @param mCoinBeen the m coin been
     * @param coinid    the coinid
     * @return the coin type adapter
     */
    public static CommonAdapter getNewsCoinTypeAdapter(final Context context, List<CoinBean.DataBean> mCoinBeen, final String coinid) {
        mCommonAdapter = new CommonAdapter<CoinBean.DataBean>(context, R.layout.type_item, mCoinBeen) {
            @Override
            protected void convert(ViewHolder holder, final CoinBean.DataBean coinbean, final int position) {
                TextView type = holder.getView(R.id.type_name);
                type.setText(coinbean.getAssetName());
                ImageView imageView = holder.getView(R.id.choose_yes);
                if (coinbean.getId().equals(coinid)) {
                    type.setTextColor(context.getResources().getColor(R.color.theme_color));
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    type.setTextColor(context.getResources().getColor(R.color.txt_color));
                    imageView.setVisibility(View.GONE);
                }
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets account adapter.
     *
     * @param context              the context
     * @param mAccountInfoBeanList the m account info bean list
     * @param account              the account
     * @return the account adapter
     */
    public static CommonAdapter getAccountAdapter(final Context context, List<AccountInfoBean> mAccountInfoBeanList, final String account) {
        mCommonAdapter = new CommonAdapter<AccountInfoBean>(context, R.layout.type_item, mAccountInfoBeanList) {
            @Override
            protected void convert(ViewHolder holder, final AccountInfoBean item, final int position) {
                TextView type = holder.getView(R.id.type_name);
                type.setText(item.getAccount_name());
                ImageView imageView = holder.getView(R.id.choose_yes);
                if (item.getAccount_name().equals(account)) {
                    type.setTextColor(context.getResources().getColor(R.color.theme_color));
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    type.setTextColor(context.getResources().getColor(R.color.txt_color));
                    imageView.setVisibility(View.GONE);
                }
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets coin type adapter.
     *
     * @param context   the context
     * @param mCoinList the m coin list
     * @param coin      the coin
     * @return the coin type adapter
     */
    public static CommonAdapter getCoinTypeAdapter(final Context context, List<String> mCoinList, final String coin) {
        mCommonAdapter = new CommonAdapter<String>(context, R.layout.type_item, mCoinList) {
            @Override
            protected void convert(ViewHolder holder, final String item, final int position) {
                TextView type = holder.getView(R.id.type_name);
                type.setText(item);
                ImageView imageView = holder.getView(R.id.choose_yes);
                if (item.equals(coin)) {
                    type.setTextColor(context.getResources().getColor(R.color.theme_color));
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    type.setTextColor(context.getResources().getColor(R.color.txt_color));
                    imageView.setVisibility(View.GONE);
                }
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets dapp head adapter.
     *
     * @param context     the context
     * @param mHeaderList the m header list
     * @return the dapp head adapter
     */
    public static CommonAdapter getDappHeadAdapter(final Context context, List<DappCommpanyBean.DataBean> mHeaderList) {
        mCommonAdapter = new CommonAdapter<DappCommpanyBean.DataBean>(context, R.layout.item_application, mHeaderList) {
            @Override
            protected void convert(ViewHolder holder, final DappCommpanyBean.DataBean dataBean, final int position) {
                holder.setText(R.id.application_name, dataBean.getEnterpriseName());
                MyApplication.getInstance().showCirImage(dataBean.getEnterpriseIcon(), (CircleImageView) holder.getView(R.id.application_img));
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("bean", dataBean);
                        ActivityUtils.next((Activity) context, DappCommpanyDetailsActivity.class, bundle);
                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets dapp bussness adapter.
     *
     * @param context            the context
     * @param mBussinessDappList the m bussiness dapp list
     * @return the dapp bussness adapter
     */
    public static CommonAdapter getDappBussnessAdapter(final Context context, List<DappBean.DataBean> mBussinessDappList) {
        mCommonAdapter = new CommonAdapter<DappBean.DataBean>(context, R.layout.item_bussiness_application, mBussinessDappList) {
            @Override
            protected void convert(ViewHolder holder, final DappBean.DataBean dataBean, final int position) {
                holder.setText(R.id.bussiness_application_name, dataBean.getApplyName());
                holder.setText(R.id.bussiness_application_desc, dataBean.getApplyDetails());
                MyApplication.getInstance().showImage(dataBean.getApplyIcon(), (ImageView) holder.getView(R.id.bussiness_application_img));
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("title", dataBean.getApplyName());
                        bundle.putString("url", dataBean.getUrl());
                        ActivityUtils.next((Activity) context, DappDetailsActivity.class, bundle);
                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets switch user number adapter.
     *
     * @param context              the context
     * @param mAccountInfoBeanList the m account info bean list
     * @param account              the account
     * @return the switch user number adapter
     */
    public static CommonAdapter getSwitchUserNumberAdapter(final Context context, List<AccountInfoBean> mAccountInfoBeanList, final String account) {
        mCommonAdapter = new CommonAdapter<AccountInfoBean>(context, R.layout.item_account, mAccountInfoBeanList) {
            @Override
            protected void convert(ViewHolder holder, final AccountInfoBean accountInfoBean, final int position) {
                final TextView account_number = holder.getView(R.id.account_number);
                final ImageView account_img = holder.getView(R.id.account_img);
                account_number.setText(accountInfoBean.getAccount_name());
                MyApplication.getInstance().showImage(accountInfoBean.getAccount_img(), account_img);
                if (account.equals(account_number.getText().toString())) {
                    account_number.setTextColor(context.getResources().getColor(R.color.theme_color));
                }
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("account", account_number.getText().toString().trim());
                        ActivityUtils.goBackWithResult((Activity) context, 200, bundle);

                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets transfer history adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @return the transfer history adapter
     */
    public static CommonAdapter getTransferHistoryAdapter(final Context context, List<TransferHistoryBean.DataBeanX.TransactionsBean> mDataBeanList) {
        mCommonAdapter = new CommonAdapter<TransferHistoryBean.DataBeanX.TransactionsBean>(context, R.layout.item_transfer_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, TransferHistoryBean.DataBeanX.TransactionsBean item, int position) {
                holder.setText(R.id.transfer_details, "发送给 " + item.getTransaction().getTransaction().getActions().get(0).getData().getTo());

                holder.setText(R.id.transfer_amount, "-" + item.getTransaction().getTransaction().getActions().get(0).getData().getQuantity());

                holder.setText(R.id.time, "所在区块:" + item.getTransaction().getTransaction().getRef_block_num());
            }
        };
        return mCommonAdapter;
    }

        public static CommonAdapter getTransferHistoryAdapter2(final Context context, List<TransferHistoryBean2.DataBeanX.ActionBean> mDataBeanList) {
        mCommonAdapter = new CommonAdapter<TransferHistoryBean2.DataBeanX.ActionBean>(context, R.layout.item_transfer_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, TransferHistoryBean2.DataBeanX.ActionBean item, int position) {
                holder.setText(R.id.transfer_details, "发送给 " + item.getData().getTo());

                holder.setText(R.id.transfer_amount, "-" + item.getData().getQuantity());

                holder.setText(R.id.time, "区块:" + item.getBlock_num() + " " + item.getBlock_time());
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets transfer history adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @return the transfer history adapter
     */
    public static CommonAdapter getMakeCollectionHistoryAdapter(final Context context, List<TransferHistoryBean.DataBeanX.TransactionsBean> mDataBeanList) {
        mCommonAdapter = new CommonAdapter<TransferHistoryBean.DataBeanX.TransactionsBean>(context, R.layout.item_transfer_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, TransferHistoryBean.DataBeanX.TransactionsBean item, int position) {
                holder.setText(R.id.transfer_details, "接收自 " + item.getTransaction().getTransaction().getActions().get(0).getData().getFrom());
                TextView tv = holder.getView(R.id.transfer_amount);
                tv.setTextColor(context.getResources().getColor(R.color.up_color));
                tv.setText("+" + item.getTransaction().getTransaction().getActions().get(0).getData().getQuantity());
                holder.setText(R.id.time, "所在区块:" + item.getTransaction().getTransaction().getRef_block_num());
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets red packet history adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @param account       the account
     * @return the red packet history adapter
     */
    public static CommonAdapter getRedPacketHistoryAdapter(final Context context, List<RedPacketHistoryBean.DataBean> mDataBeanList, final String account) {
        mCommonAdapter = new CommonAdapter<RedPacketHistoryBean.DataBean>(context, R.layout.item_redpacket_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, final RedPacketHistoryBean.DataBean item, int position) {
                if (item.isSend()) {
                    int isRod = item.getPacketCount() - item.getResidueCount();
                    if (item.getResidueCount() == 0) {
                        holder.setText(R.id.transfer_details, "发送" + RegexUtil.subZeroAndDot(item.getAmount()) + "个" + item.getType() + "给" + item.getPacketCount() + "个人,全部被领取");
                    } else {
                        holder.setText(R.id.transfer_details, "发送" + RegexUtil.subZeroAndDot(item.getAmount()) + "个" + item.getType() + "给" + item.getPacketCount() + "个人,已被" + isRod + "人领取");
                    }
                } else {
                    holder.setText(R.id.transfer_details, "领取" + RegexUtil.subZeroAndDot(item.getAmount()) + "个" + item.getType());
                }
                holder.setText(R.id.time, item.getCreateTime());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        if (item.isSend()) {
                            bundle.putParcelable("data", item);
                            bundle.putString("account", account);
                            ActivityUtils.next((Activity) context, ContinueRdPacketActivity.class, bundle);
                        } else {
                            bundle.putParcelable("data", item);
                            bundle.putString("account", account);
                            ActivityUtils.next((Activity) context, GetRedPacketDetailsActivity.class, bundle);
                        }
                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets red packet details adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @return the red packet details adapter
     */
    public static CommonAdapter getRedPacketDetailsAdapter(final Context context, List<RedPacketDetailsBean.DataBean.RedPacketOrderRedisDtosBean> mDataBeanList) {
        mCommonAdapter = new CommonAdapter<RedPacketDetailsBean.DataBean.RedPacketOrderRedisDtosBean>(context, R.layout.item_redpacket_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, final RedPacketDetailsBean.DataBean.RedPacketOrderRedisDtosBean item, int position) {
                holder.setText(R.id.transfer_details, item.getAccount() + " 领取" + RegexUtil.subZeroAndDot(item.getAmount()) + " " + item.getType());
                holder.setText(R.id.time, item.getCreateTime());
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets transfer history adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @param account       the account
     * @return the transfer history adapter
     */
    public static CommonAdapter getCoinDetailsHistoryAdapter(final Context context, List<TransferHistoryBean.DataBeanX.TransactionsBean> mDataBeanList, final String account) {
        mCommonAdapter = new CommonAdapter<TransferHistoryBean.DataBeanX.TransactionsBean>(context, R.layout.item_transfer_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, TransferHistoryBean.DataBeanX.TransactionsBean item, int position) {
                TextView tv = holder.getView(R.id.transfer_amount);
                if (item.getTransaction().getTransaction().getActions().get(0).getData().getFrom().equals(account)) {
                    tv.setTextColor(context.getResources().getColor(R.color.title_color));
                    if (item.getTransaction().getTransaction().getActions().get(0).getData().getTo().equals("oc.redpacket")) {
                        holder.setText(R.id.transfer_details, "发出红包");
                    } else {
                        holder.setText(R.id.transfer_details, "发送给 " + item.getTransaction().getTransaction().getActions().get(0).getData().getTo());
                    }

                    tv.setText("-" + item.getTransaction().getTransaction().getActions().get(0).getData().getQuantity());

                } else if (item.getTransaction().getTransaction().getActions().get(0).getData().getTo().equals(account)) {
                    tv.setTextColor(context.getResources().getColor(R.color.up_color));
                    if (item.getTransaction().getTransaction().getActions().get(0).getData().equals("oc.redpacket")) {
                        holder.setText(R.id.transfer_details, "红包入账");
                    } else {
                        holder.setText(R.id.transfer_details, "接受自 " + item.getTransaction().getTransaction().getActions().get(0).getData().getFrom());
                    }

                    tv.setText("+" + item.getTransaction().getTransaction().getActions().get(0).getData().getQuantity());

                }
                holder.setText(R.id.time, "区块:" + item.getTransaction().getTransaction().getRef_block_num());
            }
        };
        return mCommonAdapter;
    }

    public static CommonAdapter getCoinDetailsHistoryAdapter2(final Context context, List<TransferHistoryBean2.DataBeanX.ActionBean> mDataBeanList, final String account) {
        mCommonAdapter = new CommonAdapter<TransferHistoryBean2.DataBeanX.ActionBean>(context, R.layout.item_transfer_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, TransferHistoryBean2.DataBeanX.ActionBean item, int position) {
                TextView tv = holder.getView(R.id.transfer_amount);
                if (item.getData().getFrom().equals(account)) {
                    tv.setTextColor(context.getResources().getColor(R.color.title_color));
                    holder.setText(R.id.transfer_details, "发送给 " + item.getData().getTo());

                    tv.setText("-" + item.getData().getQuantity());

                } else if (item.getData().getTo().equals(account)) {
                    tv.setTextColor(context.getResources().getColor(R.color.up_color));
                    holder.setText(R.id.transfer_details, "接受自 " + item.getData().getFrom());

                    tv.setText("+" + item.getData().getQuantity());

                }
                holder.setText(R.id.time, "区块:" + item.getBlock_num() + " " + item.getBlock_time());
            }
        };
        return mCommonAdapter;
    }

    public static CommonAdapter getPairDetailsHistoryAdapter(final Context context, List<PairTransferHistoryBean.DataBeanX.ActionBean> mDataBeanList, final PairBean pair) {
        mCommonAdapter = new CommonAdapter<PairTransferHistoryBean.DataBeanX.ActionBean>(context, R.layout.item_transfer_history, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, PairTransferHistoryBean.DataBeanX.ActionBean item, int position) {
                TextView tv = holder.getView(R.id.transfer_amount);
                String base[] = pair.getBase_balance().split(" ");
                String token[] = item.getData().getQuant().split(" ");
                if(token[1].equals(base[1])){
                    tv.setTextColor(context.getResources().getColor(R.color.title_color));
                    holder.setText(R.id.transfer_details, "卖出");
                    tv.setText(item.getData().getQuant());
                } else {
                    tv.setTextColor(context.getResources().getColor(R.color.up_color));
                    holder.setText(R.id.transfer_details, "买入");
                    tv.setText(item.getData().getQuant());
                }
                holder.setText(R.id.time, "区块:" + item.getBlock_num() + " " + item.getBlock_time());
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets friend list adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @param account       the account
     * @return the friend list adapter
     */
    public static CommonAdapter getFriendListAdapter(final Context context, List<FriendsListInfoBean> mDataBeanList, final String account) {
        mCommonAdapter = new CommonAdapter<FriendsListInfoBean>(context, R.layout.item_account, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, final FriendsListInfoBean item, final int position) {
                final TextView account_number = holder.getView(R.id.account_number);
                final ImageView account_img = holder.getView(R.id.account_img);
                account_number.setText(item.getDisplayName());
                MyApplication.getInstance().showImage(item.getAvatar(), account_img);
                if (account.equals(account_number.getText().toString())) {
                    account_number.setTextColor(context.getResources().getColor(R.color.theme_color));
                }
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("account", item.getDisplayName());
                        ActivityUtils.goBackWithResult((Activity) context, 200, bundle);

                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets pe list adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @param type          the type
     * @return the pe list adapter
     */
    public static CommonAdapter getPeListAdapter(final Context context, List<PelistBean.DataBean> mDataBeanList, final String type) {
        mCommonAdapter = new CommonAdapter<PelistBean.DataBean>(context, R.layout.item_friend_list, mDataBeanList) {
            @Override
            protected void convert(ViewHolder holder, final PelistBean.DataBean dataBean, final int position) {
                if (type.equals("0")) {
                    holder.getView(R.id.title).setVisibility(View.VISIBLE);
                    holder.setText(R.id.title, "Top" + (position + 1));
                } else {
                    holder.getView(R.id.title).setVisibility(View.GONE);
                }
                holder.setText(R.id.name, dataBean.getDisplayName());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("name", dataBean.getDisplayName());
                        bundle.putString("friend_type", "1");//属于钱包级别
                        bundle.putString("avatar", dataBean.getAvatar());
                        bundle.putString("uid", dataBean.getUid());
                        bundle.putString("from", "pelist");
                        ActivityUtils.next((Activity) context, FriendsDetailsActivity.class, bundle);
                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets paid answer list adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @param account       the account
     * @return the paid answer list adapter
     */
    public static CommonAdapter getPaidAnswerListAdapter(final Context context, List<QuestionListBean> mDataBeanList, final String account) {
        mCommonAdapter = new CommonAdapter<QuestionListBean>(context, R.layout.item_paid_answer, mDataBeanList) {
            @Override
            protected void convert(final ViewHolder holder, final QuestionListBean item, final int position) {
                holder.setText(R.id.name, item.getName());
                holder.setText(R.id.time, DateUtils.getDateFromSeconds(String.valueOf(item.getTime())));
                holder.setText(R.id.question_details, item.getTitle());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("question", item);
                        bundle.putString("account", account);
                        ActivityUtils.next((Activity) context, QuestionDetailsActivity.class, bundle);
                    }
                });
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets message list adapter.
     *
     * @param context       the context
     * @param mDataBeanList the m data bean list
     * @return the message list adapter
     */
    public static CommonAdapter getMessageListAdapter(final Context context, List<MessageCenterBean.DataBean> mDataBeanList) {
        mCommonAdapter = new CommonAdapter<MessageCenterBean.DataBean>(context, R.layout.item_message_center, mDataBeanList) {
            @Override
            protected void convert(final ViewHolder holder, final MessageCenterBean.DataBean item, final int position) {
                holder.setText(R.id.message_title, item.getTitle());
                holder.setText(R.id.message_desc, item.getSummary());
                holder.setText(R.id.message_time, item.getUpdateTime());
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets suggestion list adapter.
     *
     * @param context    the context
     * @param mDataBeans the m data beans
     * @return the suggestion list adapter
     */
    public static CommonAdapter getSuggestionListAdapter(final Context context, List<SuggestionBean.DataBean> mDataBeans) {
        mCommonAdapter = new CommonAdapter<SuggestionBean.DataBean>(context, R.layout.item_suggestion_history, mDataBeans) {
            @Override
            protected void convert(final ViewHolder holder, SuggestionBean.DataBean item, int position) {
                holder.setText(R.id.suggestion_title, item.getContent());
                if (item.getComment() != null) {
                    holder.setText(R.id.suggestion_answer, item.getComment());
                } else {
                    holder.setText(R.id.suggestion_answer, "暂无回复~");
                }
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets hot equities adapter.
     *
     * @param context    the context
     * @param mDataBeans the m data beans
     * @return hot equities adapter
     */
    public static CommonAdapter getHotEquitiesAdapter(final Context context, List<HotEquitiesBean.DataBean> mDataBeans) {
        mCommonAdapter = new CommonAdapter<HotEquitiesBean.DataBean>(context, R.layout.item_hot_equities, mDataBeans) {
            @Override
            protected void convert(final ViewHolder holder, HotEquitiesBean.DataBean item, int position) {
                holder.setText(R.id.equities_name, item.getTitle());
                MyApplication.getInstance().showImage(item.getAvatar(), holder.getConvertView().findViewById(R.id.img));
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets candy task adapter.
     *
     * @param context    the context
     * @param mDataBeans the m data beans
     * @return candy task adapter
     */
    public static CommonAdapter getCandyTaskAdapter(final Context context, List<CandyUserTaskBean.DataBean> mDataBeans) {
        mCommonAdapter = new CommonAdapter<CandyUserTaskBean.DataBean>(context, R.layout.item_candy_task_list, mDataBeans) {
            @Override
            protected void convert(final ViewHolder holder, CandyUserTaskBean.DataBean item, int position) {
                holder.setText(R.id.name, item.getCandyTask().getTitle());
                holder.setText(R.id.desc, item.getCandyTask().getDescription());
                TextView textView = holder.getView(R.id.do_status);
               if (item.isCompleted()){
                   textView.setText(R.string.done);
               }else {
                   textView.setText(R.string.no_done);
               }
                MyApplication.getInstance().showImage(item.getCandyTask().getAvatar(), holder.getConvertView().findViewById(R.id.task_img));
            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets wallet list adapter.
     *
     * @param context    the context
     * @param mDataBeans the m data beans
     * @return the wallet list adapter
     */
    public static CommonAdapter getWalletListAdapter(final Context context, List<UserBean> mDataBeans) {

        mCommonAdapter = new CommonAdapter<UserBean>(context, R.layout.item_choose_wallet, mDataBeans) {
            @Override
            protected void convert(final ViewHolder holder, UserBean item, int position) {
                holder.setText(R.id.wallet_name, item.getWallet_name());
                CheckBox checkBox = holder.getView(R.id.choose_wallet);
                checkBox.setChecked(mSelectedPos == position);
                checkBox.setClickable(false);
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {//判断item是否被点击
                    @Override
                    public void onClick(View v) {
                        if (mSelectedPos != position) {//当前选中的position和上次选中不是同一个position 执行
                            checkBox.setChecked(true);
                            if (mSelectedPos != -1) {//判断是否有效 -1是初始值 即无效 第二个参数是Object 随便传个int 这里只是起个标志位
                                notifyItemChanged(mSelectedPos, 0);
                            }
                            mSelectedPos = position;
                        }
                    }
                });

            }
        };
        return mCommonAdapter;
    }

    /**
     * Gets selected pos.
     *
     * @return the selected pos
     */
//提供给外部Activity来获取当前checkBox选中的item，这样就不用去遍历了 重点！
    public static int getSelectedPos() {
        return mSelectedPos;
    }
}
