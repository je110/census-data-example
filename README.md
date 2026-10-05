Census Data - Example
-------------------------


*Uses Java 8*

Instructions:

-update `application.properties` with mySQL user info
-create a database called `census` 



------------
Endpoints:
---------------

  All endpoints begin with `localhost:8080/api/v1/census`
  

Return all zipcodes which have a total population within range provided by the client.
  `/population`, params: `min`, `max` (inclusive range)
  ex: `localhost:8080/api/v1/census/population/min=1&max=1000`

Return all zipcodes which have a median age within a range provided by the client.
  `/medianAge`, params: `min`, `max` (inclusive range)
  ex: `localhost:8080/api/v1/census/medianAge/min=10&max=19`

Return top X number of most populated zipcodes.
  `/mostPopulous`, params: `amount`
  ex: `localhost:8080/api/v1/census/mostPopulous/amount=10`
  
Return all zipcodes with more females than males ordered by the difference descending.
  `/mostlyFemale`
  ex: `localhost:8080/api/v1/census/mostlyFemale`
