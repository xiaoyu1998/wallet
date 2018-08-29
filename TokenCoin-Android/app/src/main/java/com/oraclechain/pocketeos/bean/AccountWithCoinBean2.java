package com.oraclechain.pocketeos.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2018/1/22.
 * 账号里面的代币bean
 */

public class AccountWithCoinBean2 implements Parcelable {
    private String coinName;
    private String coinImg;
    private String coinNumber;
    private String coinForCny;
    private String coinUpsAndDowns;
    private String coinPriceCny;
    private String coinPriceUsd;
    private String contract;
    // private String eos_market_cap_usd;
    // private String eos_market_cap_cny;
    // private String oct_market_cap_usd;
    // private String oct_market_cap_cny;
    // private String oct_price_cny;
    // private String eos_price_cny;

    public String getCoinName() {
        return coinName == null ? "" : coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getCoinImg() {
        return coinImg == null ? "" : coinImg;
    }

    public void setCoinImg(String coinImg) {
        this.coinImg = coinImg;
    }

    public String getCoinNumber() {
        return coinNumber == null ? "" : coinNumber;
    }

    public void setCoinNumber(String coinNumber) {
        this.coinNumber = coinNumber;
    }

    public String getCoinForCny() {
        return coinForCny == null ? "" : coinForCny;
    }

    public void setCoinForCny(String coinForCny) {
        this.coinForCny = coinForCny;
    }

    public String getCoinUpsAndDowns() {
        return coinUpsAndDowns == null ? "" : coinUpsAndDowns;
    }

    public void setCoinUpsAndDowns(String coinUpsAndDowns) {
        this.coinUpsAndDowns = coinUpsAndDowns;
    }

    public String getCoinPriceCny() {
        return coinPriceCny == null ? "" : coinPriceCny;
    }

    public void setCoinPriceCny(String coinPriceCny) {
        this.coinPriceCny = coinPriceCny;
    }

    public String getCoinPriceUsd() {
        return coinPriceUsd == null ? "" : coinPriceUsd;
    }

    public void setCoinPriceUsd(String coinPriceUsd) {
        this.coinPriceUsd = coinPriceUsd;
    }

    public String getContract() {
        return contract == null ? "" : contract;
    }

    public void setContract(String contract) {
        this. contract = contract;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.coinName);
        dest.writeString(this.coinImg);
        dest.writeString(this.coinNumber);
        dest.writeString(this.coinForCny);
        dest.writeString(this.coinUpsAndDowns);
        dest.writeString(this.coinPriceCny);
        dest.writeString(this.coinPriceUsd);
        dest.writeString(this.contract);
        // dest.writeString(this.eos_market_cap_usd);
        // dest.writeString(this.eos_market_cap_cny);
        // dest.writeString(this.oct_market_cap_usd);
        // dest.writeString(this.oct_market_cap_cny);
        // dest.writeString(this.oct_price_cny);
        // dest.writeString(this.eos_price_cny);
    }

    public AccountWithCoinBean2() {
    }

    protected AccountWithCoinBean2(Parcel in) {
        this.coinName = in.readString();
        this.coinImg = in.readString();
        this.coinNumber = in.readString();
        this.coinForCny = in.readString();
        this.coinUpsAndDowns = in.readString();
        this.coinPriceCny = in.readString();
        this.coinPriceUsd = in.readString();
        this.contract = in.readString();
        // this.eos_market_cap_usd = in.readString();
        // this.eos_market_cap_cny = in.readString();
        // this.oct_market_cap_usd = in.readString();
        // this.oct_market_cap_cny = in.readString();
        // this.oct_price_cny = in.readString();
        // this.eos_price_cny = in.readString();
    }

    public static final Parcelable.Creator<AccountWithCoinBean2> CREATOR = new Parcelable.Creator<AccountWithCoinBean2>() {
        @Override
        public AccountWithCoinBean2 createFromParcel(Parcel source) {
            return new AccountWithCoinBean2(source);
        }

        @Override
        public AccountWithCoinBean2[] newArray(int size) {
            return new AccountWithCoinBean2[size];
        }
    };
}
