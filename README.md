# Spring Drone Manager

Spring Boot API for Managing Drones


## PreRequisites:<br>
- JAVA Version 19 (openjdk:19)
- Maven Version 3.8.4

## To deploy the project (Docker):<br>
- Install Docker (get it from [HERE](https://docs.docker.com/get-docker/):  and make sure its added to the PATH
- Navigate to the project's root directory
- Open a terminal session using Windows Terminal, GitBash or any other CLI tool
- Run the command:<br>
  ```
  docker-compose up
  ``` 
- Wait for the image to build and run on port 8080

## To deploy the project (Locally):<br>
- Clone the repository
- Navigate to the project's root directory
- Open a terminal session using Windows Terminal, GitBash or any other CLI tool
- Run the command:<br>
  ```
  mvn spring-boot:run
  ``` 
- Wait for the image to build and run on port 8080 (Note, you need to have jdk 19 installed and added to the path)


## Database :<br>
- The application uses a h2-database instance
- On startup, a local instance will be created on root application path (/data/db)
- To access the console, visit:
 ```
  http://localhost:8080/h2-console
  ```
  
## Documentation:<br>
- The application has an embedded swagger web page showcasing all the endpoints
- To access the endpoint visit: 
 ```
  http://localhost:8080/swagger-ui/index.html
  ```
- For the spring doc alternative visit
```
  http://localhost:8080/api-docs
  ```
- For the PostMan Collection Look under
  [Collection.json](/Documentation/Drone%20Collection.postman_collection.json)

## Tests <br>
- To run all the unit tests Run the command:<br>
  ```
  mvn clean test
  ``` 



