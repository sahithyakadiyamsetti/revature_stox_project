README.md — RevStox: Stock Market Analytics Project

RevStox - Stock Market Analytics Platform (Java + SQL)

 What is RevStox?

*RevStox* is a Java-based backend application designed to perform *advanced analytics on historical stock market data* using SQL and Java. It enables investors, analysts, and learners to explore key metrics like *volatility, **VWAP, **moving averages, and **turnover* with ease.

 Why RevStox?

Stock markets generate huge volumes of data. Making sense of it requires:
- Deep data analysis
- Custom metrics (e.g., price volatility, VWAP)
- SQL queries for efficiency
- Dynamic, user-friendly access

*RevStox* helps users:
- Make informed decisions using data
- Understand price patterns and volume trends
- Build technical knowledge in Java + SQL

How does RevStox Work?

Technologies Used:
| Layer         | Technology |
|---------------|------------|
| Backend       | Java 17    |
| Database      | MySQL      |
| Build Tool    | Maven      |
| Logging       | SLF4J + Logback |
| DAO Layer     | JDBC (PreparedStatement) |
| Source Format | CSV        |

Core Features:

| Feature                | Description |
|------------------------|-------------|
| Volatility          | (High - Low) / Open * 100 |
| VWAP               | Σ(Price × Volume) / Σ(Volume) |
| Moving Averages     | 7-day & 30-day using SQL Window Functions |
| Turnover           | Close Price × Volume |
| Clean Code          | Layered Architecture (Model, DAO, Service, CLI) |
| Validation & Error Handling | With proper try-catch blocks |
| SQL Injection Safe | Uses PreparedStatement |

Project Structure

rev_stox/ ├── src/ │ 
├── main/ │   
│   ├── java/com/revature/rev_stox/ 
│   │   │   ├── model/           
# POJOs (Stock, StockAnalytics)
│   │   │   ├── dao/               
# DAO for JDBC logic 
│   │   │  ├── service/           
# Service layer 
│   │   │   ├── util/
# DBConnection utility 
│   │   │   └── AnalyticsMenu.java  
# CLI Menu 
│   │   └── resources/ 
│   │       ├── db.properties       
# DB Config file │
│       └── logback.xml         
# Logging config 
├── pom.xml                         
# Maven config 
└── README.md

How to Run
------------------------------------------------------------------------
1. Prerequisites
- Java 17 installed
- MySQL running locally (or on cloud)
- Maven installed
- MySQL Workbench (optional)
- IntelliJ or VSCode
------------------------------------------------------------------------
 2. Setup Database

1. Create database: revstox
2. Create tables:
    - stocks
    - daily_prices
    - stock_analytics
3. Import data from your cleaned .csv files using Workbench or SQL script.

------------------------------------------------------------------------

3. Configure db.properties

Located at src/main/resources/db.properties:

db.url=jdbc:mysql://localhost:3306/revstox db.username=root db.password=your_password

------------------------------------------------------------------------
4. Configure logback.xml

Located at src/main/resources/logback.xml:

------------------------------------------------------------------------

5. Run the App

From terminal:

mvn clean install
java -cp target/classes com.revature.rev_stox.AnalyticsMenu

From IntelliJ:

Right-click AnalyticsMenu.java → Run

------------------------------------------------------------------------

File	Purpose

pom.xml	Dependencies and build
logback.xml	Log file config
db.properties	Database credentials
AnalyticsMenu.java	CLI user interface
AnalyticsDAO.java	All SQL logic
StockAnalytics.java	Model class for analytics
README.md	This file 
------------------------------------------------------------------------

Security Considerations

---- SQL Injection prevented using PreparedStatement

-----No hardcoded credentials in Java code

-----Uses parameterized inputs

-----Logs errors with stack traces (in logs/application.log)


------------------------------------------------------------------------

Logging

Logs are stored in:

logs/application.log

Example:

2025-08-05 12:00:01 [main] INFO  AnalyticsService - getVolatilityOnly() called
2025-08-05 12:00:01 [main] ERROR AnalyticsDAO - SQLException: No data found

------------------------------------------------------------------------

Completion Checklist

Feature	Status

Stock POJO & DAO	
MySQL Table Creation	
CSV Import	
Volatility Query	
VWAP Query
Moving Averages	
Turnover Query	
CLI Menu	
Logging (SLF4J + Logback)
Git Integration
Clean Java Architecture	
Exception Handling	
README 

