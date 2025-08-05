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

    // Full analytics

    public List<StockAnalytics> getFullanalytics(String symbol) {
        logger.info("Fetching full analytics for symbol: {}", symbol);
        List<StockAnalytics> list = new ArrayList<>();

        String query = """
            SELECT * FROM (
                SELECT
                    symbol, 
                    trade_date,
                    COALESCE(ROUND((high_price - low_price) / open_price * 100, 2), 0) AS volatility,
                    COALESCE(ROUND(AVG(close_price) OVER (
                        PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 29 PRECEDING AND CURRENT ROW
                    ), 2), 0) AS moving_average,
                    COALESCE(ROUND(SUM(close_price * volume) OVER (
                        PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
                    ) / NULLIF(SUM(volume) OVER (
                        PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
                    ), 0), 2), 0) AS vwap,
                    COALESCE(ROUND(close_price * volume, 2), 0) AS turnover
                FROM daily_prices
                WHERE symbol = ?
            ) AS analytics
            ORDER BY trade_date
            LIMIT 50
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
                            rs.getDouble("moving_average"),
                            rs.getDouble("vwap"),
                            rs.getDouble("turnover")
                    );
                    list.add(a);
                }
            }

            logger.info("Full analytics fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching full analytics for symbol {}: {}", symbol, e.getMessage(), e);
        }

        return list;
    }

    // Moving average only

    public List<StockAnalytics> getMovingAverage(String symbol) {
        logger.info("Fetching 30-day moving average for symbol: {}", symbol);
        List<StockAnalytics> list = new ArrayList<>();

        String query = """
            SELECT symbol, trade_date,
                   ROUND(AVG(close_price) OVER (
                       PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 29 PRECEDING AND CURRENT ROW
                   ), 2) AS moving_average
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
            LIMIT 50
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, symbol);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StockAnalytics a = new StockAnalytics(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            0.0,
                            rs.getDouble("moving_average"),
                            0.0,
                            0.0
                    );
                    list.add(a);
                }
            }

            logger.info("Moving average fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching moving average for {}: {}", symbol, e.getMessage(), e);
        }

        return list;
    }

    // Volatility only

    public List<StockAnalytics> getVolatility(String symbol) {
        logger.info("Fetching volatility for symbol: {}", symbol);
        List<StockAnalytics> list = new ArrayList<>();

        String query = """
            SELECT symbol, trade_date,
                   ROUND((high_price - low_price) / open_price * 100, 2) AS volatility
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
            LIMIT 20
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
                            0.0, 0.0, 0.0
                    );
                    list.add(a);
                }
            }

            logger.info("Volatility data fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching volatility for {}: {}", symbol, e.getMessage(), e);
        }

        return list;
    }

    // VWAP only

    public List<StockAnalytics> vwap(String symbol) {
        logger.info("Fetching VWAP for symbol: {}", symbol);
        List<StockAnalytics> list = new ArrayList<>();

        String query = """
            SELECT symbol, trade_date,
                   ROUND(SUM(close_price * volume) OVER (
                       PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
                   ) / NULLIF(SUM(volume) OVER (
                       PARTITION BY symbol ORDER BY trade_date ROWS BETWEEN 6 PRECEDING AND CURRENT ROW
                   ), 0), 2) AS vwap
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
            LIMIT 20
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StockAnalytics a = new StockAnalytics(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            0.0, 0.0,
                            rs.getDouble("vwap"),
                            0.0
                    );
                    list.add(a);
                }
            }

            logger.info("VWAP data fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching VWAP for {}: {}", symbol, e.getMessage(), e);
        }

        return list;
    }

    // Turnover only

    public List<StockAnalytics> getTurnover(String symbol) {
        logger.info("Fetching turnover for symbol: {}", symbol);
        List<StockAnalytics> list = new ArrayList<>();

        String query = """
            SELECT symbol, trade_date,
                   ROUND(close_price * volume, 2) AS turnover
            FROM daily_prices
            WHERE symbol = ?
            ORDER BY trade_date
            LIMIT 20
        """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, symbol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    StockAnalytics a = new StockAnalytics(
                            rs.getString("symbol"),
                            rs.getDate("trade_date").toLocalDate(),
                            0.0, 0.0, 0.0,
                            rs.getDouble("turnover")
                    );
                    list.add(a);
                }
            }

            logger.info("Turnover data fetched: {} records", list.size());
        } catch (SQLException e) {
            logger.error("Error fetching turnover for {}: {}", symbol, e.getMessage(), e);
        }

        return list;
    }
}