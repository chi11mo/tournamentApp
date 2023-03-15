package com.chillmo.developer.tournamentApp.event.controller;

import com.chillmo.developer.tournamentApp.event.domain.Event;
import com.chillmo.developer.tournamentApp.event.request.RegistrationEventRequest;
import com.chillmo.developer.tournamentApp.event.service.EventService;
import com.chillmo.developer.tournamentApp.user.domain.User;
import com.chillmo.developer.tournamentApp.user.response.RegistrationResponse;
import com.chillmo.developer.tournamentApp.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventResource {
    private final EventService eventService;

    private final UserService userService;

    public EventResource(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    /**
     * This method is the Api adress to get all {@link Event}.
     *
     * @return a list of all saved {@link Event}.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> event = eventService.findAllEvents();
        return new ResponseEntity<List<Event>>(event, HttpStatus.OK);
    }


    /**
     * Registers a new user with the specified registration data.
     *
     * @param request the registration data for the new event
     * @return a ResponseEntity containing either the newly created user data and a 201 Created status code,
     */
    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationEventRequest request) {
        Event newEvent = new Event(
                request.getEventName(),
                request.getBeginDate(),
                request.getEndDate(),
                request.getEndRegisterDate(),
                request.isRegisterActive(),
                request.getMaxParticipants()
        );
        eventService.addEvent(newEvent);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new RegistrationResponse("Event " + request.getEventName() + " is registered."));


    }

    @PostMapping("/addUser")
    public ResponseEntity<RegistrationResponse> registerUserToEvent(long userId, long eventId) {
        Event event = eventService.findEventById(eventId);
        User user = userService.findUserByID(userId);
        if (event == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RegistrationResponse("Event :" + eventId + " not Found."));
        }
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RegistrationResponse("User :" + userId + " not Found."));
        }

        if (event.getRegisteredUser().contains(user)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RegistrationResponse("User " + user.getTwitch() + " is already registered."));
        } else {
            eventService.addUser(event, user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new RegistrationResponse("User " + user.getTwitch() + " is registered."));

        }
    }
}



