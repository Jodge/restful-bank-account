# README #

JAVA Based REST API ** Built and Tested on Java8 **

### What is this project for? ###

* simple "Bank Account" web service using REST API design principles.
* Version 0.0.1

### How do I run the project? ###
Execute ** start.sh **
N.B.: Internet Connection needed to donwload jars from Maven Repo

* REST ENDPOINTS
http://localhost:8585/balance/ 				[GET]
http://localhost:8585/deposit/				[POST]
http://localhost:8585/withdrawal/			[POST]

### Executing APIs using Curl ###

curl http://localhost:8585/balance/ 
curl -H "Content-Type: application/json" -X POST -d '{"amount":45000}' http://localhost:8585/deposit/      *** $45k deposit ***
curl -H "Content-Type: application/json" -X POST -d '{"amount":15000}' http://localhost:8585/withdrawal/   *** $15k withdrawal ***

N.B.: Added backslash to escape windows
curl -H "Content-Type: application/json" -X POST http://localhost:8585/deposit/ -d "{\"amount\":400}"

### Running tests? ###
Execute ** run-tests.sh **
Test Reports ** build/reports **
Test Results ** build/test-results **

[Code Coverage] Jacoco ** build/reports/jacoco/test/html/index.html **


### Who do I talk to? ###

* George Otieno <georgeorti@gmail.com>
