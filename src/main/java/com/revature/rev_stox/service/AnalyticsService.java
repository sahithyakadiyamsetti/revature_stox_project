package com.revature.rev_stox.service;

import com.revature.rev_stox.dao.AnalyticsDAO;
import com.revature.rev_stox.model.StockAnalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class AnalyticsService {
    private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);
    private final AnalyticsDAO AnalyticsDAO;

    public AnalyticsService() {
        this.AnalyticsDAO = new AnalyticsDAO();
        logger.info("AnalyticsService initialized successfully");
    }

    // full analytics
    public List<StockAnalytics> getFullAnalytics(String symbol) {
        logger.info("Getting getFullAnalytics data for symbol: {}", symbol);
        return AnalyticsDAO.getVolatility(symbol);
    }

    // vwap
    public List<StockAnalytics>getvwapOnly(String symbol) {
        logger.info("Getting getvwaponly data for symbol: {}", symbol);
        return AnalyticsDAO.getVolatility(symbol);
    }

    // turnover
    public List<StockAnalytics>getTurnOverOnly(String symbol) {
       logger.info("Getting TurnOverOnly data for symbol: {}", symbol);
        return AnalyticsDAO.getVolatility(symbol);
    }

    //to get only volatility
    public List<StockAnalytics> getVolatilityOnly(String symbol) {
        logger.info("Getting Volatility for symbol: {}", symbol);
        return AnalyticsDAO.getVolatility(symbol);
    }


    //to get only moving averages
    public List<StockAnalytics> getMovingAveragesOnly(String symbol) {
        logger.info("Getting getMovingAveragesOnly for symbol: {}", symbol);
        return AnalyticsDAO.getVolatility(symbol);
    }
}
