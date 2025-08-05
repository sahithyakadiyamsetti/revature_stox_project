package com.revature.rev_stox.model;


public class Stock {
    private String symbol;
    private String companyName;
    private String sector;
    private long marketCap;


    // getter and setter

    public String getSymbol() {

        return symbol;
    }

    public void setSymbol(String symbol) {

        this.symbol = symbol;
    }

    public String getCompanyName() {

        return companyName;
    }

    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

    public String getSector() {

        return sector;
    }

    public void setSector(String sector) {

        this.sector = sector;
    }

    public long getMarketCap() {

        return marketCap;
    }

    public void setMarketCap(long marketCap) {

        this.marketCap = marketCap;
    }

    // constructor

    public Stock(String symbol, String companyName, String sector, long marketCap) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.sector = sector;
        this.marketCap = marketCap;

    }
}
