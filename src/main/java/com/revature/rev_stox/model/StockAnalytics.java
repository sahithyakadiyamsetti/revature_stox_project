package com.revature.rev_stox.model;

import java.time.LocalDate;

public class StockAnalytics {
    private String symbol;
    private LocalDate tradeDate;
    private double volatility;
    private double movingAvg;
    private double vwap;
    private double turnover;

    // constructor

    public StockAnalytics(String symbol, LocalDate tradeDate, double volatility, double movingAvg, double vwap, double turnover) {
        this.symbol = symbol;
        this.tradeDate = tradeDate;
        this.volatility = volatility;
        this.movingAvg = movingAvg;
        this.vwap = vwap;
        this.turnover = turnover;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public double getMovingAvg() {
        return movingAvg;
    }

    public void setMovingAvg(double movingAvg) {
        this.movingAvg = movingAvg;
    }

    public double getVwap() {
        return vwap;
    }

    public void setVwap(double vwap) {
        this.vwap = vwap;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }
}
