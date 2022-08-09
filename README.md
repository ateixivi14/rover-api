
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
### Technologies


### Possible improvements

