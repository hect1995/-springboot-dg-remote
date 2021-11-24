# Embedded Spring Boot DG example

This repository contains an application in Spring Boot that incorporates embedded Data Grid.

To build run the following command:
```
$ mvn clean package spring-boot:repackage
```
Once the _Target_ directory is created do:
```
// The first server
$ java -jar -Dserver.port=8090 target/spring-boot-complete-0.0.1-SNAPSHOT.jar

// The second server
$ java -jar -Dserver.port=8091 target/spring-boot-complete-0.0.1-SNAPSHOT.jar
```

To add variables to the index from another terminal do:
```
$ curl -X GET localhost:8090/api/infinispan
```