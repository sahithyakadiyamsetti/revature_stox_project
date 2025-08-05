package com.revature.rev_stox.util.importer;

import com.revature.rev_stox.dao.DailyPriceDAO;
import com.revature.rev_stox.dao.StockDAO;
import com.revature.rev_stox.model.DailyPrice;
import com.revature.rev_stox.model.Stock;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class DataImporter {

    public static void main(String[] args) {
        StockDAO stockDAO = new StockDAO();
        DailyPriceDAO priceDAO = new DailyPriceDAO();

        HashSet<String> insertedStocks = new HashSet<>();

        try {
            InputStream inputStream = DataImporter.class.getClassLoader().getResourceAsStream("Nifty50.csv");

            if (inputStream == null) {
                throw new RuntimeException("CSV file not found in resources folder.");
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                if (tokens.length < 11) {
                    System.err.println("skipping line due to insufficient columns : " + line);
                    continue;
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

                String date = tokens[0].trim();
                if (date.startsWith("\uFEFF")) {
                    date = date.substring(1);
                }

                LocalDate tradeDate;
                try {
                    tradeDate = LocalDate.parse(date, formatter);
                }  catch (Exception e) {
                    System.out.println("Date parse error for value '" + date + "': " + e.getMessage());
                    continue;
                }

                String symbol = tokens[1].trim();
                String company_name = tokens[2].trim();
                String sector = tokens[3].trim();
                double open = Double.parseDouble(tokens[4].trim());
                double high = Double.parseDouble(tokens[5].trim());
                double low = Double.parseDouble(tokens[6].trim());
                double close = Double.parseDouble(tokens[7].trim());
                long volume = Long.parseLong(tokens[8].trim());
                double turnover = Double.parseDouble(tokens[9].trim());
                double marketCap = Double.parseDouble(tokens[10].trim());

                // insert only once per symbol

                if (!insertedStocks.contains(symbol)) {
                    Stock stock = new Stock(symbol, company_name, sector, (long) marketCap);
                    stockDAO.insertStock(stock);
                    insertedStocks.add(symbol);
                }

                // use insert ignore logic to avoid duplicates

                DailyPrice dp = new DailyPrice(symbol, tradeDate, open, high, low, close, volume, (long) turnover);
                priceDAO.insertDailyPrice(dp);
            }

            System.out.println("CSV file imported successfully.");

        } catch (Exception e) {
            System.err.println("Error Importing date : " + e.getMessage());
        }
    }
}


