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
![alt tag](/help/ER-Diagram.PNG)


## Restrictions and Flow
#### User restrictions: 
+ Read, search, create and edit only his/her orders.<br />
+ Read, search, create and deactivate all customers.<br />
+ Read and search all products.<br />
+ User cannot see or get any action on other users.<br />

#### Admin restrictions:
+ Read, search, create and edit only all orders.<br />
+ Read, search, create and deactivate all customers.<br />
+ Read and search all products.<br />
+ See all actions from all users and admins.<br />

#### Flow:
+ User must login with given of admin credentials. <br />
+ After successful login User is redirected to orders page.<br />
+ Every User (user, admin) can edit his/her own credentials. <br />

