README.md — RevStox: Stock Market Analytics Project

RevStox - Stock Market Analytics Platform (Java + SQL)
------------------------------------------------------------------------
 What is RevStox?
RevStox is a Java-based backend application designed to perform *advanced analytics on historical stock market data using SQL and Java. It enables investors, analysts, and learners to explore key metrics like volatility, VWAP, moving averages, and turnover with ease.

 ------------------------------------------------------------------------
Stock markets generate huge volumes of data. Making sense of it requires:
- Deep data analysis
- Custom metrics (e.g., price volatility, VWAP)
- SQL queries for efficiency
- Dynamic, user-friendly access
  
------------------------------------------------------------------------
RevStox helps users:
- Make informed decisions using data
- Understand price patterns and volume trends
- Build technical knowledge in Java + SQL

------------------------------------------------------------------------

Technologies Used:
| Layer         | Technology |
|---------------|------------|
| Backend       | Java 17    |
| Database      | MySQL      |
| Build Tool    | Maven      |
| Logging       | SLF4J + Logback |
| DAO Layer     | JDBC (PreparedStatement) |
| Source Format | CSV        |
------------------------------------------------------------------------

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
------------------------------------------------------------------------

Project Structure
com.revature.rev_stox
├── model/ → Stock, DailyPrice, StockAnalytics (POJOs)
├── dao/ → AnalyticsDAO.java (JDBC queries)
├── service/ → AnalyticsService.java (business logic)
├── util/ → DBConnection.java (DB utility)
└── AnalyticsMenu.java → CLI menu (main class)

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
  -->Create database: revstox
  -->Create tables:
    - stocks
    - daily_prices
    - stock_analytics
  -->Import data from your cleaned .csv files using Workbench or SQL script.
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


📂 File Structure and Purpose

File	Purpose
pom.xml	Manages dependencies and build using Maven
logback.xml	Configures logging via SLF4J/Logback
db.properties	Stores MySQL DB connection credentials
AnalyticsMenu.java	CLI-based user interface for the project
AnalyticsDAO.java	Handles all SQL queries and JDBC logic
StockAnalytics.java	POJO model for stock analytics data
README.md	Project overview, instructions, and info

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

------------------------------------------------------------------------

Completion Checklist
--Stock POJO & DAO	
--MySQL Table Creation	
--CSV Import	
--Volatility Query	
--VWAP Query
--Moving Averages	
--Turnover Query	
--CLI Menu	
--Logging (SLF4J + Logback)
--Git Integration
--Clean Java Architecture	
--Exception Handling	


