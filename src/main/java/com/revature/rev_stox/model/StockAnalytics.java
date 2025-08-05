package com.revature.rev_stox.model;

import java.time.LocalDate;

public class StockAnalytics {
    private String symbol;
    private LocalDate tradeDate;
    private double volatility;
    private double movingAvg7;
    private double movingAvg30;
    private double vwap;
    private double turnover;

    // constructor

    public StockAnalytics(String symbol, LocalDate tradeDate, double volatility, double movingAvg7, double getMovingAvg30, double vwap, double turnover) {
        this.symbol = symbol;
        this.tradeDate = tradeDate;
        this.volatility = volatility;
        this.movingAvg7 = movingAvg7;
        this.movingAvg30 = movingAvg30;
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

    public double getMovingAvg7() {
        return movingAvg7;
    }

    public void setMovingAvg7(double movingAvg7) {
        this.movingAvg7 = movingAvg7;
    }

    public double getMovingAvg30() {
        return movingAvg30;
    }

    public void setMovingAvg30(double movingAvg30) {
        this.movingAvg30 = movingAvg30;
    }

    public double getVwap() {
        return vwap;
    }

    public void setVwap(double vwap) {
        vwap = vwap;
    }

    public double getTurnover() {
        return turnover;
    }

    public void setTurnover(double turnover) {
        this.turnover = turnover;
    }

    @Override
    public String toString() {
        return "StockAnalytics{" +
                "symbol='" + symbol + '\'' +
                ", tradeDate=" + tradeDate +
                ", volatility=" + volatility +
                ", movingAvg7=" + movingAvg7 +
                ", movingAvg30=" + movingAvg30 +
                ", vwap=" + vwap +
                ", turnover=" + turnover +
                '}';
    }
}
