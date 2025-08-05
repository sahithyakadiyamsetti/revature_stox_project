package com.revature.rev_stox.dao;

import com.revature.rev_stox.model.Stock;
import com.revature.rev_stox.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    // insert a new stock
    public void insertStock(Stock stock) {
        String query = "Insert ignore into stocks (symbol, company_name, sector, market_cap) values (?,?,?,?)";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, stock.getSymbol());
            stmt.setString(2, stock.getCompanyName());
            stmt.setString(3, stock.getSector());
            stmt.setLong(4,stock.getMarketCap());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting stock: " + e.getMessage());
        }
    }

    // fetch all stocks
    public List<Stock> getAllStocks() {
        List<Stock> stocks = new ArrayList<>();
        String query = "Select * from stocks";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Stock stock = new Stock(
                        rs.getString("symbol"),
                        rs.getString("company_name"),
                        rs.getString("sector"),
                        rs.getLong("market_cap")
                );
                stocks.add(stock);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching stocks : " + e.getMessage());
        }
    return stocks;
    }
}
