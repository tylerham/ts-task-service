# ts-task-service

## Prerequisites

- JDK 8 on your PATH and JAVA_HOME set
- maven on your PATH

## Setup

    $ mvn dependency:resolve
    $ mvn clean compile install

### Setting up the database

    $ java -jar target/ts-task-service-1.0-SNAPSHOT.jar db migrate default.yml

## Running the project

    $ java -jar target/ts-task-service-1.0-SNAPSHOT.jar server default.yml

It runs at http://localhost:8080

## Endpoints:

`POST /tasks/` with `JSON` like:

      {
        "taskName": "First Task",
        "assignedUser": "John Smith",
        "completed": false
      }

`GET /tasks?user=Inital+User` returns `JSON` like:

      {
        "id": 1
        "taskName": "First Task",
        "assignedUser": "John Smith",
        "completed": false
      }

`PATCH /tasks/1` with `JSON` like:

      {
        "id": 1
        "taskName": "First Task",
        "assignedUser": "Jane Doe",
        "completed": true
      }

and the response will be the same if it's accepted.