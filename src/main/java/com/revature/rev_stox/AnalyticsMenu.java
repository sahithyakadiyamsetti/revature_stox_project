package com.revature.rev_stox;

import com.revature.rev_stox.model.StockAnalytics;
import com.revature.rev_stox.service.AnalyticsService;

import java.lang.invoke.VolatileCallSite;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;

public class AnalyticsMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnalyticsService service = new AnalyticsService();
        String continueChoice;

        do {
            System.out.println("=======Stock Analytics Menu========");
            System.out.println("1. View Volatility");
            System.out.println("2. View VWAP");
            System.out.println("3. View Turnover");
            System.out.println("4. View Moving Averages");
            System.out.println("5. view full analytics: ");
            System.out.println("6. Exit");
            System.out.println("Enter your choice(1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    System.out.println("Enter stock symbol(e.g: ZEEL, BAJFINANCE, ,POWERGRID, TATAMOTORS, MARUTI): ");
                    String symbol = scanner.nextLine().toUpperCase();
                    List<StockAnalytics> analyticsList = service.getVolatilityOnly(symbol);
                    displayAnalytics(analyticsList, symbol, "Volatility only");
                }
                case "2" -> {
                    System.out.println("Enter stock symbol (e.g: ZEEL, BAJFINANCE, TATAMOTORS, MARUTI, POWERGRID): ");
                    String symbol = scanner.nextLine().toUpperCase();
                    List<StockAnalytics> analyticsList = service.getVwapOnly(symbol);
                    displayAnalytics(analyticsList, symbol, "VWAP only");
                }
                case "3" -> {
                    System.out.println("Enter stock symbol: ");
                    String symbol = scanner.nextLine().toUpperCase();
                    List<StockAnalytics> analyticsList = service.getTurnOverOnly(symbol);
                    displayAnalytics(analyticsList, symbol, "TurnOver only");
                }

                case "4" -> {
                    System.out.println("Enter stock symbol: ");
                    String symbol = scanner.nextLine().toUpperCase();
                    List<StockAnalytics> analyticsList = service.getMovingAveragesOnly(symbol);
                    displayAnalytics(analyticsList, symbol, "Moving Averages only");
                }
                case "5" -> {
                    System.out.println("Enter stock symbol: ");
                    String symbol = scanner.nextLine().toUpperCase();
                    List<StockAnalytics> analyticsList = service.getFullAnalytics(symbol);
                    displayAnalytics(analyticsList, symbol, "Full Analytics");
                }
                case "6" -> {
                    System.out.println("Exiting Rev_Stox analytics. Thank You!!!!!!!!!!!");
                    return;
                }
                default -> System.out.println("Invalid choice.please enter a number in between 1 and 5.");
            }
            System.out.println("\n Do you want to perform another operation? (Y/N): ");
            continueChoice = scanner.nextLine().trim().toUpperCase();
        } while (continueChoice.equals("Y"));
        System.out.println("Rev_stox ended");
    }

    private static void displayAnalytics(List<StockAnalytics> list, String symbol, String title) {
        if (list.isEmpty()) {
            System.out.println("No Analytics found for : " + symbol);
            return;
        }
        System.out.printf("\n%s for %s: \n", title, symbol);
        printHeader();

        for (StockAnalytics a : list) {
            System.out.printf("%-10s | %-12s | %-10.2f | %-15.2f | %-12.2f | %-12.2f%n",
                    a.getSymbol(),
                    a.getTradeDate(),
                    a.getVolatility(),
                    a.getMovingAvg(),
                    a.getVwap(),
                    a.getTurnover());
        }
    }
    private static void printHeader() {

        System.out.println("-------------------------------------------------------------------------");
        System.out.printf("%-10s | %-12s | %-10s | %-15s | %-10s | %-10s%n",
                "Symbol", "Trade date", "Volatility", "MovingAvg", "VWAP", "Turnover");
        System.out.println("---------------------------------------------------------------------------");
    }
}

