# Recruitment-Application
This is a recruitment application created for the course IV1201. 
(Only functionality to fulfill architectural solutions for the course will be implmented)

# Tools
  * Database(Postgres)
  * Project management (Maven)
  * Cloud runtime (Heroku)

# Frameworks
  * Spring core
  * Spring MVC
  * Spring Boot
  * Spring Security
  * Spring Data
  * Thymeleaf

# Installation  
Below follows requirements and instructions on how to run tha application.
### Requirements:
* Maven
* Postgres
* JDK 1.8+

### Instructions:
1. Download or clone the project from github

2. Change the following lines in the application.properties 
to match your database and credentials for your local postgres 
    ```
    spring.datasource.url=jdbc:postgresql://localhost:5432/<database>   
    spring.datasource.username=<username>
    spring.datasource.password=<password>
    ```

3. Run the following maven command from the project folder 
to package the application to a jar
    ```
    $ mvn clean install
    ```
    
4. Run the following java command to run the application
    ```
    $ java -jar appserv-spring-2.0.jar
    ```  

### Deploy on Heroku
1. Create an account.

2. Creat a project and link it to the application on GitHub.

3. Configure the postgres database as instructed on Heroku.