# kalah
Microservice game Kalah using springboot

## Prerequists
* Java Development Kit (JDK)
* Maven 

## Setup

1) Build jar using Maven `mvn package`
2) Run registration server `java -jar target/kalah-0.0.1-SNAPSHOT.jar registration` wait for the script to finish running
3) In a separate shell run logic server `java -jar target/kalah-0.0.1-SNAPSHOT.jar logic`
3) In a separate shell run repository server `java -jar target/kalah-0.0.1-SNAPSHOT.jar repository`