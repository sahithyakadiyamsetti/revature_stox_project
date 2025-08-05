package com.revature.rev_stox;

import com.revature.rev_stox.dao.StockDAO;
import com.revature.rev_stox.model.Stock;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StockDAO dao = new StockDAO();

        //fetch and print all stocks
        List<Stock> stocks = dao.getAllStocks();
        if (stocks.isEmpty()) {
            System.out.println("no stocks found in the database");
        } else {
            System.out.println("Stocks From The Database");
        }
        for (Stock s : stocks) {
            System.out.println(s.getSymbol() + " ------ " +s.getCompanyName());
        }
    }
}
