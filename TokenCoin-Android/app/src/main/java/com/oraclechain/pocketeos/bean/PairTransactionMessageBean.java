package com.oraclechain.pocketeos.bean;

/**
 * Created by pocketEos on 2018/5/3.
 */

public class PairTransactionMessageBean {

    private String from;
    private String quant;
    private String symbol;
    private String feeto;

    public PairTransactionMessageBean(String from, String quant, String symbol, String feeto) {
        this.from   = from;
        this.quant  = quant;
        this.symbol = symbol;
        this.feeto  = feeto;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getQuant() {
        return quant;
    }

    public void setQuantity(String quant) {
        this.quant = quant;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getFeeto() {
        return feeto;
    }

    public void setFeeto(String feeto) {
        this.feeto = feeto;
    }

}
