package com.oraclechain.pocketeos.bean;

// import java.util.ArrayList;
// import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pocketEos on 2018/1/22.
 */

public class PairBean implements Parcelable {
    private String supply;
    // private Connector base;
    // private Connector quote;
    private String base_balance;
    private String base_weight;

    private String quote_balance;
    private String quote_weight;

    private String fee_amount;
    private String token_price_pair;
    private String token_price_usd;
    private String token_price_cny;
    private String token_ups_and_downs;

    public String getSupply() {
        return supply == null ? "" : supply;
    }
    public void setSupply(String supply) {
        this.supply = supply;
    }

    // public Connector getBase() {
    //     return base;
    // }
    // public void setBase(Connector base) {
    //     this.base = base;
    // }

    // public Connector getQuote() {
    //     return quote;
    // }
    // public void setQuote(Connector quote) {
    //     this.quote = quote;
    // }

    public String getBase_balance() {
        return base_balance;
    }
    public void setBase_balance(String base_balance) {
        this.base_balance = base_balance;
    }
    public String getBase_weight() {
        return base_weight;
    }
    public void setBase_weight(String base_weight) {
        this.base_weight = base_weight;
    }

    public String getQuote_balance() {
        return quote_balance;
    }
    public void setQuote_balance(String quote_balance) {
        this.quote_balance = quote_balance;
    }
    public String getQuote_weight() {
        return quote_weight;
    }
    public void setQuote_weight(String quote_weight) {
        this.quote_weight = quote_weight;
    }


    public String getFee_amount() {
        return fee_amount == null ? "" : fee_amount;
    }
    public void setFee_amount(String fee_amount) {
        this.fee_amount = fee_amount;
    }

    public String getToken_price_pair() {
        return token_price_pair == null ? "" : token_price_pair;
    }
    public void setToken_price_pair(String token_price_pair) {
        this.token_price_pair = token_price_pair;
    }

    public String getToken_price_usd() {
        return token_price_usd == null ? "" : token_price_usd;
    }
    public void setToken_price_usd(String token_price_usd) {
        this.token_price_usd = token_price_usd;
    }

    public String getToken_price_cny() {
        return token_price_cny == null ? "" : token_price_cny;
    }
    public void setToken_price_cny(String token_price_cny) {
        this.token_price_cny = token_price_cny;
    }

    public String getToken_ups_and_downs() {
        return token_ups_and_downs == null ? "" : token_ups_and_downs;
    }
    public void setToken_ups_and_downs(String token_ups_and_downs) {
        this.token_ups_and_downs = token_ups_and_downs;
    }

    // public class Connector {
    //     private String balance;
    //     private String weight;

    //     public String getBalance() {
    //         return balance == null ? "" : balance;
    //     }
    //     public void setBalance(String balance) {
    //         this.balance = balance;
    //     }

    //     public String getWeight() {
    //         return weight == null ? "" : weight;
    //     }
    //     public void setWeight(String weight) {
    //         this.weight = weight;
    //     }

    // }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.supply);
        dest.writeString(this.base_balance);
        dest.writeString(this.base_weight);
        dest.writeString(this.quote_balance);
        dest.writeString(this.quote_weight);
        dest.writeString(this.fee_amount);
        dest.writeString(this.token_price_pair);
        dest.writeString(this.token_price_usd);
        dest.writeString(this.token_price_cny);
        dest.writeString(this.token_ups_and_downs);
        // dest.writeString(this.eos_market_cap_usd);
        // dest.writeString(this.eos_market_cap_cny);
        // dest.writeString(this.oct_market_cap_usd);
        // dest.writeString(this.oct_market_cap_cny);
        // dest.writeString(this.oct_price_cny);
        // dest.writeString(this.eos_price_cny);
    }

    public PairBean() {
    }

    protected PairBean(Parcel in) {
        this.supply           = in.readString();
        this.base_balance     = in.readString();
        this.base_weight      = in.readString();
        this.quote_balance    = in.readString();
        this.quote_weight     = in.readString();
        this.fee_amount       = in.readString();
        this.token_price_pair = in.readString();
        this.token_price_usd  = in.readString();
        this.token_price_cny  = in.readString();
        this.token_ups_and_downs = in.readString();
        // this.eos_market_cap_usd = in.readString();
        // this.eos_market_cap_cny = in.readString();
        // this.oct_market_cap_usd = in.readString();
        // this.oct_market_cap_cny = in.readString();
        // this.oct_price_cny = in.readString();
        // this.eos_price_cny = in.readString();
    }

    public static final Parcelable.Creator<PairBean> CREATOR = new Parcelable.Creator<PairBean>() {
        @Override
        public PairBean createFromParcel(Parcel source) {
            return new PairBean(source);
        }

        @Override
        public PairBean[] newArray(int size) {
            return new PairBean[size];
        }
    };


}

