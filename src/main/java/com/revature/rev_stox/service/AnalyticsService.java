package com.revature.rev_stox.service;

import com.revature.rev_stox.dao.AnalyticsDAO;
import com.revature.rev_stox.model.StockAnalytics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class AnalyticsService {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);
    private final AnalyticsDAO analyticsDAO;

    public AnalyticsService() {
        this.analyticsDAO = new AnalyticsDAO();
        logger.info("AnalyticsService initialized successfully");
    }

    // Full analytics
    public List<StockAnalytics> getFullAnalytics(String symbol) {
        logger.info("Getting full analytics for symbol: {}", symbol);
        return analyticsDAO.getFullanalytics(symbol);
    }

    // VWAP only
    public List<StockAnalytics> getVwapOnly(String symbol) {
        logger.info("Getting VWAP data for symbol: {}", symbol);
        return analyticsDAO.vwap(symbol);
    }

    // Turnover only
    public List<StockAnalytics> getTurnOverOnly(String symbol) {
        logger.info("Getting turnover data for symbol: {}", symbol);
        return analyticsDAO.getTurnover(symbol);
    }

    // Volatility only
    public List<StockAnalytics> getVolatilityOnly(String symbol) {
        logger.info("Getting volatility data for symbol: {}", symbol);
        return analyticsDAO.getVolatility(symbol);
    }

    // Moving averages only
    public List<StockAnalytics> getMovingAveragesOnly(String symbol) {
        logger.info("Getting moving averages for symbol: {}", symbol);
        return analyticsDAO.getMovingAverage(symbol);
    }

}