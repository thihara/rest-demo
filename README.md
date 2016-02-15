# rest-demo
A Spring-Boot application to demo Restful web services using Spring MVC, and an Angular JS client.

Deployed demo URL : http://fierce-retreat-62806.herokuapp.com/

##Running the application

You need JDK 1.8 installed to run this application.

Once that basic requirement is filled, you can run the application using maven by running this simple command *`mvn spring-boot:run`*

##Server
Server side rest operations can be accessed by the `/company` namespace.

###Functionality
The application uses a simple in-memory datastore instead of connencting to a database to store the data. This would of course mean that once you restart the application all data is wiped clean.

####Model

The application manipulated a *Company* object. A company has the following attributes.
* Company ID
* Name
* Address
* City
* Country
* E­mail (Optional)
* Phone Number (Optional)
* One or more beneficial owner(s)

####Rest operations

The application allows 5 operations.

1. Add a company.
  All the mandatory fields must be present for the request to succeed.
  * HTTP Method : `POST`
  * Rest URL : `/company`
  * cURL example using _localhost_: `curl -H "Content-Type: application/json" -X POST -d '{"id":null,"name":"ISA","address":"175A, Greenpath","city":"Bambalapitiya","country":"Sri Lanka","email":"thihara@yahoo.com","phoneNumber":"0094722262377","owners":["James Bond","Eric Foreman","Gregory House"]}' fierce-retreat-62806.herokuapp.com/company`
  
2. Edit a company.
  Only the properties that are present will be updated. Optional values however will be overriden to `null` of not present in the edit request. Owners attribute will be entirely _replaced_ instead of being appended in this operation.
  * HTTP Method : `PUT`
  * Rest URL : `/company/<company-id>`
  * cURL example : `curl -H "Content-Type: application/json" -X PUT -d '{"country":"Japan","email":"thihara@gmail.com","phoneNumber":"0094722262377"}' fierce-retreat-62806.herokuapp.com/company/1`

3. Add a owner to a company
  A new owner will be appended to the existing owner list in the company.
  * HTTP Method : `PUT`
  * Rest URL : `/company/<company-id>/owner`
  * cURL example using _localhost_: `curl -H "Content-Type: application/json" -X PUT -d "Dean Winchester" fierce-retreat-62806.herokuapp.com/company/1/owner`

4. List a company.
  List company details for a given ID.
  * HTTP Method : `GET`
  * Rest URL : `/company/<company-id>`
  * cURL example using _localhost_: `curl fierce-retreat-62806.herokuapp.com/company/1`

5. List all companies.
  Lists all the companies present in the application.
  * HTTP Method : `GET`
  * Rest URL : `/company`
  * cURL example using _localhost_: `curl fierce-retreat-62806.herokuapp.com/company`

##Client
The client is written in Angular JS. It can be accessed from the `/index.html` or by simply visiting the applications root context `/`

