# BACKEND

### FILES THAT NEED TO BE UPDATED FOR EVERY NEW FEATURE

There are 3 main folders in backend, controller, model, and repo
#### Controller

 For each new feature add a new function to corresponding controller, create a new controller if needed.
 There are few ways to get the query params, [all listed here](https://www.baeldung.com/spring-requestmapping).
#### Model
Model consists of 2 parts, dto and the table entity definition.
DTO(data transfer object) refers to objects that you will use to in, say, your controllers, to make your code easier to read.
**Entity**(annotated with `@ENTITY`) are Java representation of actual table in the database, the name of table, column numbers, type of column and so on must be the **same as defined in database**. Use _pgAdmin_ to check the database, and add new table in the database if necessary, __**text me before**__ you want to do anything with the actual database.

#### Repo
Add new repo `interface` if necessary.
JpaRepository provide basic CRUD operations such as `findById(int id)=get item by it` and `save() = inesrt new item`. Use `@SQLQuery` annotation for more advanced sql call if needed

### *IMPORTANT FILES*
HEROKU periodically change the credential to connect to their postgres server, so if anytime the connection fails just update the **username** and **password** in `src\main\resources\application.properties`

```    
    spring.datasource.url=ec2-23-21-130-182.compute-1.amazonaws.com/d3pvjv0qor9898
    #update the user name and password from HEROKU if connection to database failed
    spring.datasource.username=  
    spring.datasource.password= 
```