# goose-game

This is an implementation of the [Goose Game Kata](https://github.com/xpeppers/goose-game-kata) invented by [Matteo Vaccari](https://github.com/xpmatteo)
This implementation is based on Spring Boot framework creating a simplified web backend application. The commands *add player* and *move player* are deployed as rest (http) controllers hosted on a Tomcat web server.
An additional feature (not requested in the original kata) in this implementation is the support for multiple concurrent game sessions (each session host a maximum of 8 players). A goose game is started once the maximum number of players has joined the session or a player start moving on the board.

## Requirements
Java 8 (no specific Java 8 features are used in this implementation).

## Build and Test
Clone the repository.
Build with the following command (using maven wrapper - make sure to have JAVA_HOME set in your command line interface or as a system-wide environment variable):

    mvnw package

Unit and integration tests are executed. The tests focus on the specific requirements described in the game kata (i.e. moving, bridge jump, goose jumps, bounce, win condition etc.).

## Run

Application can be executed with the following command:

    mvnw spring-boot:run

The web server will be answering requests on http://localhost:8080
The exposed endpoints are:

**POST** /add?name=*player name*

**POST** /move?name=*player name*

The */move* endpoint can also have an (optional) request body in the form of an application/json object that models the Roll.java object such as:

    {
		"first":1, 
		"second":2
	}

where first and second represents the number from dice rolls. If the body is not present, the dice rolls are randomized.

## Deployment
Spring Boot allows the deployment in several ways, the simplest one is fat jar that can be built simply by running:

    mvnw clean install

The jar artifact will be in the /target folder as goose-\<version\>-snapshot.jar and can be executed as a standalone application with java -jar (including Tomcat server).

## Demo
A simple "demo" with three players (Pippo, Pluto and Paperino) can be executed by running the special (randomized) test DemoGame.java using the following command:

    mvnw -Dtest=DemoGame test

