# stocks-api

An API to load stock info and retrive stock information . The app is inspired from a collection of records from the [Dow Jones Index from 2011](http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#). By default the CSV file is loaded at the start of the application. Users of the service could as well enter new stock information individually or via CSV upload. Duplicate stock information is not allowed in the app. Also stock info for a future date is prevented from creating.


## Technologies used 

 - [JAVA 11](https://www.oracle.com/java/technologies/downloads/#java11)
 - Fremaework - Spring Boot
 - Database - H2
 - Build Tool- Maven
 - Api Documentation - [Swagger](https://swagger.io/)
 - CSV parse Library - [OpenCSV](http://opencsv.sourceforge.net/)
 - Version Control - Git


## Application Setup

- git clone , import the app and run locally

- using Docker 
  ```
  mvn clean install
  docker build . -t harisree06/stocksapi
  docker run -p 8080:8080 harisree06/stocksapi
  ```
- From Docker hub (might be broken) 
  ```
  docker pull harisree06/stocksapi
  docker run -p 8080:8080 harisree06/stocksapi
  
  ```
  
  
  
Once application is checked out and running access the app URL using swagger UI or RESTClient
- Swagger Url - http://localhost:8080/swagger-ui/index.html

![swagger](https://github.com/webdev-june-2021/notes_images/blob/main/swagger.png?raw=true)
- To check the DB - http://localhost:8080/h2-console/login.do

Credentials are as follows 
url=jdbc:h2:mem:stockInfodb
driverClassName=org.h2.Driver
username=rbc_user
password=rbc_password


## Current Features

- To Access stock Info using stock ticker
 GET http://localhost:8080/rbc-test/api/v1/stocks?stockTicker=AA
 
 
 -To post info about the stock 
 POST http://localhost:8080/rbc-test/api/v1/stocks
 
 
 -To upload stock info as a batch 
 POST http://localhost:8080/rbc-test/api/v1/uploadCSV

