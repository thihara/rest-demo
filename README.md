# rest-demo
A Spring-Boot application to demo Restful web services using Spring MVC.

##Running the application

You need JDK 1.8 installed to run this application.

Once that basic requirement is filled, you can run the application using maven by running this simple command *`mvn spring-boot:run`*

##Functionality

The application uses a simple in-memory datastore instead of connencting to a database to store the data. This would of course mean that once you restart the application all data is wiped clean.

###Model

The application manipulated a *Company* object. A company has the following attributes.
* Company ID
* Name
* Address
* City
* Country
* E­mail (Optional)
* Phone Number (Optional)
* One or more beneficial owner(s)

###Rest operations

The application allows 5 operations.

1. Add a company.
  All the mandatory fields must be present for the request to succeed.
  * HTTP Method : `POST`
  * Rest URL : `/company`
  * cURL example using _localhost_: `curl -X POST -d name="FCID" -d address="202,Mahason Road" -d city=“Kollupitiya” -d country="Sri Lanka" -d owners="Json Smith" -d owners="Jackson Smith" localhost:8080/company`
  
2. Edit a company.
  Only the properties that are present will be updated. Optional values however will be overriden to `null` of not present in the edit request. Owners attribute will be entirely _replaced_ instead of being appended in this operation.
  * HTTP Method : `PUT`
  * Rest URL : `/company/<company-id>`
  * cURL example using _localhost_: `curl -X PUT -d address="203,Mahason Road" -d city=“Kollupitiya” -d country="Sri Lanka" -d owners="Margarette Smith" -d owners="Johnny Smith" localhost:8080/company/1`

3. Add a owner to a company
  A new owner will be appended to the existing owner list in the company.
  * HTTP Method : `PUT`
  * Rest URL : `/company/<company-id>/owner`
  * cURL example using _localhost_: `curl -X PUT -d owner="Jeremy Smith" localhost:8080/company/1/owner`

4. List a company.
  List company details for a given ID.
  * HTTP Method : `GET`
  * Rest URL : `/company/<company-id>`
  * cURL example using _localhost_: `curl localhost:8080/company/1`

5. List all companies.
  Lists all the companies present in the application.
  * HTTP Method : `GET`
  * Rest URL : `/company`
  * cURL example using _localhost_: `curl localhost:8080/company`


