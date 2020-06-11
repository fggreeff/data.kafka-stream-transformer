# data.kafka-stream-transformer

Consume basic data from a Kafka in-topic, apply data transformation and finally produce to a Kafka out-topic 

## Architecture

The architecture diagram for the application, ensure to seperate current from proposed / target diagrams.

## Getting started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

`mvn clean install`

## Prerequisites

You require the following in order to run this project locally:

```
Java 8
Maven
Docker
Docker-compose
```

## Installing

Step by step instructions on how to get a working version of the project on your local machine, such as

```
$ git clone <repo>
$ cd ./<repo>
```

Build the docker image and stand-up the infrastructure by running docker-compose:

```
docker-compose up --build -d kafka-stream-transformer
```

## Running the tests

An explanation on how to run any automated tests that relate to the project.

To run the unit tests:
```
mvn test -Ddependency-check.skip=true
```

# Resources

Links to the original source or idea that aided in the development or idea. 
* [SpringBoot](https://spring.io/projects/spring-boot) - The framework used
* [Kafka](https://kafka.apache.org/intro) - Message Queue
* [Kafka Streams](https://kafka.apache.org/documentation/streams/) - Near Real Time Messaging and Transformations
* [Maven](https://maven.apache.org/) - Dependency Management
* [Kafka Test Utils](https://kafka.apache.org/22/documentation/streams/developer-guide/testing.html) - KStreams Testing
