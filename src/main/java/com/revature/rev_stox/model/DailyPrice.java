package com.revature.rev_stox.model;

import java.time.LocalDate;

public class DailyPrice {
    private String symbol;
    private LocalDate tradeDate;
    private double openPrice, highPrice, lowPrice, closePrice;
    private long volume;
    private long turnover;

    // constructor

    public DailyPrice(String symbol, LocalDate tradeDate, double openPrice, double highPrice, double lowPrice, double closePrice, long volume, long turnover) {
        this.symbol = symbol;
        this.tradeDate = tradeDate;
        this.openPrice = openPrice;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
        this.turnover = turnover;
    }

    //getters

    public String getSymbol() {
        return symbol;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public long getVolume() {
        return volume;
    }

    public long getTurnover() {
        return turnover;
    }
}
