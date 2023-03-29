# Project Title: Football E-Sport Tournament App

Introduction:
The Football E-Sport Tournament App is a web-based platform that allows users to create and manage their own E-Sports events for football games.
The platform is designed to provide an easy-to-use interface for users who want to organize and host E-sports events, and also allows participants to sign up and participate in events.

## Features:

- User Authentication: The platform provides user authentication using Twitch, which allows users to sign in to the platform and create events.

- Create an Event: Users can create an E-sports event, select the game, date, time, location, and set the number of participants, rules, and prizes.

- Participate in an Event: Users can browse through the list of events, view the details of the event, and register to participate in the event.

- Manage an Event: The platform allows the event organizer to manage the event, view the list of participants, assign teams, and track the progress of the event.

- Communication: The platform provides a communication interface between the event organizer and participants. This feature allows the organizer to communicate with the participants, provide updates, and answer questions.

## Technology Stack:
The platform is built using the following technology stack:

- Backend: Java Spring Boot
- Frontend: React.js, Bootstrap
- Database: H2 Database Engine


## Roadmap
### Database
- Event Feature Head2Head

- Event Feature Tournament

- Event Feature League Session
## Api-Endpoints
| Endpoint         | Method | Description |
|------------------| --- | --- |
| `/user`          | `GET` | Get a list of all users |
| `/users/:id`     | `GET` | Get details for a specific user |
| `/user/register` | `POST` | Create a new user |
|