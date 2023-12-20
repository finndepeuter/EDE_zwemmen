# Swimming Registrations (Enterprise Development Experience)
This project consists of 3 microservices that interact with each other. The subject of the project is swimmingcompetitions. So you have the 3 microservices:
- event-service: for storing and retrieving the event information
- swimmer-service: for storing and retrieving the swimmer information
- race-service: for storing, retrieving and creating race information

Above this lays an api-gateway layer, to ensure that not every endpoint is freely available to the public and authentication can be performed easily with Google oauth2.

## Schema in draw.io
The point is that you can register yourself for a race. To do this, you have to give a name for the race, a date, a swimmercode and an eventcode. With the eventcode, the name of the event will be retrieved, and there will be checked if there is still places available to register. With the swimmercode the service will go check in the swimmer-service if this swimmer is available. Here the eventcode is also used to retrieve the besttime of this swimmer for that event. 
## Endpoints in Postman
Through the gateway these are the endpoints that are available:
- GET all swimmers 
- GET specific swimmer by swimmercode
- PUT swimmer
- GET all races (public)
- GET specific race
- POST race
- DELETE race
- GET all events

The only endpoint that is public is the get all races one. For the others you have to be logged in.

### GET swimmers
![Alt postman get all swimmers](/assets/getAllSwimmers.png)
### GET specific swimmer
![Alt postman get swimmer](/assets/getSwimmerBySwimmerCode.png)
### PUT swimmer
![Alt postman put swimmer](/assets/putSwimmer.png)
### GET all races
![Alt postman get all races](/assets/getRaces.png)
### GET specific race
![Alt postman get race](/assets/getRace.png)
### POST race
![Alt postman post race](/assets/postrace.png)
### DELETE race
![Alt postman delete race](/assets/deleteRace.png)
### GET events

## Hosting
The microservices are hosted with Okteto at these links:
- [GET all swimmers](https://api-gateway-finndepeuter.cloud.okteto.net/swimmers/all) (needs authentication)
- [Other swimmer related requests](https://api-gateway-finndepeuter.cloud.okteto.net/swimmers) (needs authentication)
- [GET all races](https://api-gateway-finndepeuter.cloud.okteto.net/races/all) (public)
- [Other races related requests](https://api-gateway-finndepeuter.cloud.okteto.net/races) (needs authentication)
- [GET all events](https://api-gateway-finndepeuter.cloud.okteto.net/events) (needs authentication)

## Frontend
I decided to make a front-end to accompany the microservices. I made this in React. The front-end isn't hosted anywhere.
