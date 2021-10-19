#Start with a base image containing Java runtime
FROM openjdk:11-slim as build

MAINTAINER harisree06
COPY target/stocksapi-0.0.1-SNAPSHOT.jar stocksapi-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stocksapi-0.0.1-SNAPSHOT.jar"]

EXPOSE 8080
