
## Mars Rover refactoring kata

![example workflow](https://github.com/ateixivi14/rover-api/actions/workflows/rover-api-ci.yml/badge.svg
)

### How to run

This application is dockerized so to create the image execute the following command:

````
docker build -t "rover-api" -f Dockerfile .
````
To run the container:
````
docker run --rm -p 8080:8080 --name rover-api -d rover-api
````
To check databases:
````
http://localhost:8080/h2
````
### Project structure and definition

This project is a REST api to controll MARS-ROVER position. 

The endpoint to control the Mars rover position is the following one:
We pass the command the id of the rover which the position will be updated, 
the command that we want to apply and also the map size.
````
curl --location --request POST 'localhost:8080/mars-rover/move' \
--header 'Content-Type: application/json' \
--data-raw '{   "id": 1,
    "sizex": 10,
    "sizey": 10,
    "command": "FORWARD"
} '
````
`RoverService` class is the one that handles the move action. In one site, we retrieve the rover data from database (rover table) 
and we call `MovementEngine` class which encapsulates the logic of the movements.
If an obstacle is found or the size of the map is not sufficient an exception is thrown. 

Also it has been added an endpoint to save the obstacles into a database.
```
curl --location --request POST 'localhost:8080/mars-rover/obstacles' \
--header 'Content-Type: application/json' \
--data-raw '{
    "obstacles": [
        {
            "id":1,
            "coordinate_X":3,
            "coordinate_Y":4
        },
         {
            "id":2,
            "coordinate_X":5,
            "coordinate_Y":7
        }

    ]
}'
````

This project is divided in several packages (in a major project they could be different modules):
- Controller
- DTO
- Entity
- Repository
- Service

### Technologies

The main technology is `Java` with `Spring boot`, with H2 as relational database.

In resources it has been added a script `data.sql` to automatically fill the rover table with one register. When the application runs, it's automatically inserted.

It's also has been added a `Dockerfile` to dockerize the application 

Also it has been defined a github workflow pipeline (`.github/workflows/rover-api-ci.yml`) to automatically perfom build action everytime there's a commit in branch main

### Possible improvements

It could be possible to use other relational databases as mysql or postgres. 
For that, it would be necessary to add a docker-compose and use flyway to migrate data to the databases and create the tables.  