# WebStore
Working Web Store example build with:
- Spring Framework
- Hibernate
- Oracle Database
- Maven
- Spring Security

## Install
```
$ mvn clean install 
```

## Database Fill
Export SQL from ```/help/export.sql``` to your Oracle Database.

## Run
Run with Tomacat Server.

## ER Diagram
![alt tag](https://github.com/TeodorKolev/Java-Spring-WebStrore/blob/master/help/ER-Diagram.PNG)


# Restrictions and Flow
Read, search, create and edit only his/her orders.
Read, search, create and deactivate all customers.
Read and search ALL products.
Cannot see or get any action on other users.
User must login with given of admin credentials. 
Every User (user, admin) can edit his/her own credentials. 
After successful login User is redirected to orders page.
