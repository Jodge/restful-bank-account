# Bank Account REST API 


### What is this project for? ###

* simple "Bank Account" web service using REST API design principles.
* Built and tested using Java8
* Version 0.0.1

### How do I run the project? ###
* Execute ** start.sh **
* N.B.: Internet Connection needed to donwload jars from Maven Repo

### Rest Endpoints
#### http://localhost:8585/balance/ 				[GET]
#### http://localhost:8585/deposit/				[POST]
#### http://localhost:8585/withdrawal/			[POST]

### Executing API calls using Curl

* curl http://localhost:8585/balance/ 
* curl -H "Content-Type: application/json" -X POST -d '{"amount":25000}' http://localhost:8585/deposit/
* curl -H "Content-Type: application/json" -X POST -d '{"amount":15000}' http://localhost:8585/withdrawal/

> N.B.: Added backslash to escape on Windows OS

* curl -H "Content-Type: application/json" -X POST http://localhost:8585/deposit/ -d "{\"amount\":400}"

### Running tests
* Execute ** run-tests.sh **
* Test Reports ** build/reports **
* Test Results ** build/test-results **
* [Code Coverage] Jacoco ** build/reports/jacoco/test/html/index.html **


### Who do I talk to? 

* George Otieno <georgeorti@gmail.com>