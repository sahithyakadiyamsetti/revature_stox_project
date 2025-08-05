package com.revature.rev_stox.dao;

import com.revature.rev_stox.model.StockAnalytics;
import com.revature.rev_stox.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnalyticsDAO {

private static final Logger logger = LoggerFactory.getLogger(AnalyticsDAO.class);

    public List<StockAnalytics> getVolatility(String symbol) {
        List<StockAnalytics> list = new ArrayList<>();
        String query = """
            SELECT symbol, trade_date,
                   ROUND((high_price - low_price) / open_price * 100, 2) AS volatility
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StockAnalytics a = new StockAnalytics(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            rs.getDouble("volatility"),
                            0, 0, 0, 0
                    );
                    list.add(a);
                }
            }
            logger.info("Volatility data fetched : {} records", list.size());
        } catch (SQLException e) {
            logger.error("error fetching volatility for symbol {}: {} ", symbol, e.getMessage());
        }
        return list;
    }

    public List<StockAnalytics> MovingAverages(String symbol) {
        logger.info("fetching moving averages for symbol: {} ", symbol);
        List<StockAnalytics> list = new ArrayList<>();
        String query = """
            SELECT symbol, trade_date,
                   ROUND(AVG(close_price) OVER (PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW), 2) AS moving_avg_7,
                   ROUND(AVG(close_price) OVER (PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 29 PRECEDING AND CURRENT ROW), 2) AS moving_avg_30
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StockAnalytics a = new StockAnalytics(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            0,
                            rs.getDouble("moving_avg_7"),
                            rs.getDouble("moving_avg_30"),
                            0, 0
                    );
                    list.add(a);
                }
            }
            logger.info("moving averages fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching moving averages for symbol {}: {}", symbol, e.getMessage(), e);
        }
        return list;
    }

    public List<StockAnalytics> vwap(String symbol) {
        logger.info("Fetching vwap for symbol: {}", symbol);
        List<StockAnalytics> list = new ArrayList<>();
        String query = """
            SELECT symbol, trade_date,
                   ROUND(SUM(close_price * volume) OVER (PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW)
                   / NULLIF(SUM(volume) OVER (PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW), 0), 2) AS vwap
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StockAnalytics a = new StockAnalytics(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            0, 0, 0,
                            rs.getDouble("vwap"),
                            0
                    );
                    list.add(a);
                }
            }
            logger.info("vwap fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching VWAP for symbol {}: {}",symbol, e.getMessage(),e);
        }
        return list;
    }

    public List<StockAnalytics> getTurnover(String symbol) {
        logger.info("fetching turnover for symbol: {} ", symbol);
        List<StockAnalytics> list = new ArrayList<>();
        String query = """
            SELECT symbol, trade_date,
                   ROUND(close_price * volume, 2) AS turnover
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StockAnalytics a = new StockAnalytics(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            0, 0, 0, 0,
                            rs.getDouble("turnover")
                    );
                    list.add(a);
                }
            }
            logger.info("TurnOver fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching turnover for symbol {}: {}",symbol, e.getMessage(), e);
        }
        return list;
    }
}