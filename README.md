# sample-web-scraper [![Build Status](https://travis-ci.com/abhinavtrip87/sample-web-scraper.svg?branch=master)](https://travis-ci.com/abhinavtrip87/sample-web-scraper) 

Sample web scraper is a Spring Boot web application, aimed at parsing dynamic web pages from Amazon App Store. It derives the following information regarding a particular app from Amazon App Store :

* App name
* App version
* Changelog for the current version (the "Latest updates" section)
* Release date

## Getting Started

### Prerequisites
- Java(TM) SE Runtime Environment (build 1.8.XX)
- Apache Maven 3.X or greater

1. Clone git repository 
```
git clone https://github.com/abhinavtrip87/sample-web-scraper
```
2. Build the project 
```
mvn clean install
```
3. Importing project in Eclipse (optional)
```
Import => Maven => Exisiting maven projects
```
## Running the application
1. Running directly from command line. 
```
mvn spring-boot:run
```
2. Running in eclipse. (Optional)
- Search for *SpringBootScraperApplication.java* --> Right Click --> Run as Java Application

3. Open up any web browser (Chrome, Safari etc.) and access the below URL for user interface
```
http://localhost:8080/amazon/appstore/details
```
4. Enter valid Amazon App Store's app URL eg: http://www.amazon.com/Instagram/dp/B00KZP2DTQ/

5. Details will be listed in the subsequent response. If there was a problem, an error page will be displayed instead.
![NSGIF](http://recordit.co/2bjJwhMGcU)

OR

Running the curl directly from terminal. Eg.
```
curl -d "url=https://www.amazon.com/Facebook-Messenger/dp/B00KZ6WRAA" -X POST http://localhost:8080/amazon/appstore/details -v
```

## Running tests
Even though mvn clean install will run all the tests, if tests need to be run explicitly, below command will run all tests for the application
```
mvn test
```

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot) - The web framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [JBrowserDriver](https://github.com/MachinePublishers/jBrowserDriver) - A programmable, embeddable web browser driver 
* [Jsoup](https://jsoup.org/) - Java HTML Parser
* [Thymeleaf](https://www.thymeleaf.org/) - Server-side Java template engine for both web and standalone environments
* [Bootstrap](https://getbootstrap.com/) - Front-end component library

