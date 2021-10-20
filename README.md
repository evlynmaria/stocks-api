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
- Swagger Url - http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config

![swagger](https://github.com/webdev-june-2021/notes_images/blob/main/swagger.png?raw=true)
- To check the DB - http://localhost:8080/h2-console/login.do

Credentials are as follows 
url=jdbc:h2:mem:stockInfodb
driverClassName=org.h2.Driver
username=rbc_user
password=rbc_password


## Current Features

- To Access stock Info using stock ticker
 GET http://localhost:8080/rbc-test/api/v1/stocks/{stockTicker}

Example request respose 
```
For request below with stock ticker value as 'AA'
GET http://localhost:8080/rbc-test/api/v1/stocks/AA

If there is matching record response will be HTTP status code 201

[
    {
        "quarter": 1,
        "stock": "AA",
        "date": "1/7/2011",
        "open": "$15.82",
        "high": "$16.72",
        "low": "$15.78",
        "close": "$16.42",
        "volume": "239655616",
        "percentChangePrice": 3.79267,
        "percentChangeVolumeOverLastWk": "",
        "previousWeeksVolume": "",
        "nextWeeksOpen": "$16.71",
        "nextWeeksClose": "$15.97",
        "percentChangeNextWeeksPrice": -4.42849,
        "daysToNextDividend": 26,
        "percentReturnNextDividend": 0.182704
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "1/14/2011",
        "open": "$16.71",
        "high": "$16.71",
        "low": "$15.64",
        "close": "$15.97",
        "volume": "242963398",
        "percentChangePrice": -4.42849,
        "percentChangeVolumeOverLastWk": "1.380223028",
        "previousWeeksVolume": "239655616",
        "nextWeeksOpen": "$16.19",
        "nextWeeksClose": "$15.79",
        "percentChangeNextWeeksPrice": -2.47066,
        "daysToNextDividend": 19,
        "percentReturnNextDividend": 0.187852
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "1/21/2011",
        "open": "$16.19",
        "high": "$16.38",
        "low": "$15.60",
        "close": "$15.79",
        "volume": "138428495",
        "percentChangePrice": -2.47066,
        "percentChangeVolumeOverLastWk": "-43.02495926",
        "previousWeeksVolume": "242963398",
        "nextWeeksOpen": "$15.87",
        "nextWeeksClose": "$16.13",
        "percentChangeNextWeeksPrice": 1.63831,
        "daysToNextDividend": 12,
        "percentReturnNextDividend": 0.189994
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "1/28/2011",
        "open": "$15.87",
        "high": "$16.63",
        "low": "$15.82",
        "close": "$16.13",
        "volume": "151379173",
        "percentChangePrice": 1.63831,
        "percentChangeVolumeOverLastWk": "9.355500109",
        "previousWeeksVolume": "138428495",
        "nextWeeksOpen": "$16.18",
        "nextWeeksClose": "$17.14",
        "percentChangeNextWeeksPrice": 5.93325,
        "daysToNextDividend": 5,
        "percentReturnNextDividend": 0.185989
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "2/4/2011",
        "open": "$16.18",
        "high": "$17.39",
        "low": "$16.18",
        "close": "$17.14",
        "volume": "154387761",
        "percentChangePrice": 5.93325,
        "percentChangeVolumeOverLastWk": "1.987451735",
        "previousWeeksVolume": "151379173",
        "nextWeeksOpen": "$17.33",
        "nextWeeksClose": "$17.37",
        "percentChangeNextWeeksPrice": 0.230814,
        "daysToNextDividend": 97,
        "percentReturnNextDividend": 0.175029
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "2/11/2011",
        "open": "$17.33",
        "high": "$17.48",
        "low": "$16.97",
        "close": "$17.37",
        "volume": "114691279",
        "percentChangePrice": 0.230814,
        "percentChangeVolumeOverLastWk": "-25.71219489",
        "previousWeeksVolume": "154387761",
        "nextWeeksOpen": "$17.39",
        "nextWeeksClose": "$17.28",
        "percentChangeNextWeeksPrice": -0.632547,
        "daysToNextDividend": 90,
        "percentReturnNextDividend": 0.172712
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "2/18/2011",
        "open": "$17.39",
        "high": "$17.68",
        "low": "$17.28",
        "close": "$17.28",
        "volume": "80023895",
        "percentChangePrice": -0.632547,
        "percentChangeVolumeOverLastWk": "-30.22669579",
        "previousWeeksVolume": "114691279",
        "nextWeeksOpen": "$16.98",
        "nextWeeksClose": "$16.68",
        "percentChangeNextWeeksPrice": -1.76678,
        "daysToNextDividend": 83,
        "percentReturnNextDividend": 0.173611
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "2/25/2011",
        "open": "$16.98",
        "high": "$17.15",
        "low": "$15.96",
        "close": "$16.68",
        "volume": "132981863",
        "percentChangePrice": -1.76678,
        "percentChangeVolumeOverLastWk": "66.17769355",
        "previousWeeksVolume": "80023895",
        "nextWeeksOpen": "$16.81",
        "nextWeeksClose": "$16.58",
        "percentChangeNextWeeksPrice": -1.36823,
        "daysToNextDividend": 76,
        "percentReturnNextDividend": 0.179856
    },
    {
        "quarter": 1,
        "stock": "AA",
        "date": "3/4/2011",
        "open": "$16.81",
        "high": "$16.94",
        "low": "$16.13",
        "close": "$16.58",
        "volume": "109493077",
        "percentChangePrice": -1.36823,
        "percentChangeVolumeOverLastWk": "-17.66315005",
        "previousWeeksVolume": "132981863",
        "nextWeeksOpen": "$16.58",
        "nextWeeksClose": "$16.03",
        "percentChangeNextWeeksPrice": -3.31725,
        "daysToNextDividend": 69,
        "percentReturnNextDividend": 0.180941
    }
]

```
 
 
 -To post info about the stock 
 POST http://localhost:8080/rbc-test/api/v1/stocks

Example 

```

```
 
 
 -To upload stock info as a batch 
 POST http://localhost:8080/rbc-test/api/v1/uploadCSV

