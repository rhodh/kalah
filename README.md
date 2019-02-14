# kalah
Microservice game Kalah using springboot

## Prerequisites
* Java Development Kit (JDK)
* Maven 

## Setup

1) Build jar using Maven `mvn package`
2) Run registration server `java -jar target/kalah-0.0.1-SNAPSHOT.jar registration` wait for the script to finish running
3) In a separate shell run logic server `java -jar target/kalah-0.0.1-SNAPSHOT.jar logic`
4) In a separate shell run repository server `java -jar target/kalah-0.0.1-SNAPSHOT.jar repository`
5) In a separate shell run game server `java -jar target/kalah-0.0.1-SNAPSHOT.jar game`
6) Play game and enjoy :)



## Improvements

* mongdb database for the repository.
* Cucumber integration test for end to end testing.
* Improve error handling so we communicate illegal requests by the client rather than just fail ungracefully.


## Architecture
Using Eureka for Micro-service registration.

GameService that orchestrates the game and feedbacks to the client. 

RepositoryService that can Create/Get/Update GameInstances.

LogicService, which given a game model and pit id, will return an updated model wrt the rules of Kalah.
