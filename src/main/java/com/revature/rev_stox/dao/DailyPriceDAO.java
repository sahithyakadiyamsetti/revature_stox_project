package com.revature.rev_stox.dao;

import com.revature.rev_stox.model.DailyPrice;
import com.revature.rev_stox.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DailyPriceDAO {

    //insert a daily price record
    public void insertDailyPrice(DailyPrice price) {
        String query = """
                insert ignore into daily_prices
                (symbol,trade_date, open_price, high_price, low_price, close_price, volume, turnover)
                values (?,?,?,?,?,?,?,?)""";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, price.getSymbol());
            ps.setDate(2, Date.valueOf(price.getTradeDate()));
            ps.setDouble(3, price.getOpenPrice());
            ps.setDouble(4, price.getHighPrice());
            ps.setDouble(5, price.getLowPrice());
            ps.setDouble(6, price.getClosePrice());
            ps.setLong(7, price.getVolume());
            ps.setLong(8, price.getTurnover());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting daily price: " + e.getMessage());
        }
    }

    // fetch daily prices by stock symbol
    public List<DailyPrice> getPriceBySymbol(String symbol) throws SQLException {
        List<DailyPrice> prices = new ArrayList<>();
        String query = "Select * from daily_prices where symbol = ? order by trade_date";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, symbol);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DailyPrice price = new DailyPrice(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            rs.getDouble("open_price"),
                            rs.getDouble("high_price"),
                            rs.getDouble("low_price"),
                            rs.getDouble("close_price"),
                            rs.getLong("volume"),
                            rs.getLong("turnover")
                    );
                    prices.add(price);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        } catch (SQLException e) {
        System.err.println("Error fetching daily prices: " + e.getMessage());
    }
return prices;
}
}

