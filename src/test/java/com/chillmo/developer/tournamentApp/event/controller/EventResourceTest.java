package com.chillmo.developer.tournamentApp.event.controller;
import com.chillmo.developer.tournamentApp.event.domain.Event;
import com.chillmo.developer.tournamentApp.event.request.RegistrationEventRequest;

import com.chillmo.developer.tournamentApp.event.service.EventService;
import com.chillmo.developer.tournamentApp.user.domain.User;
import com.chillmo.developer.tournamentApp.user.response.RegistrationResponse;
import com.chillmo.developer.tournamentApp.user.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import static org.junit.jupiter.api.Assertions.*;

public class EventResourceTest{
    @Mock
    private EventService eventService;

    @Mock
    private UserService userService;

    private EventResource eventResource;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        eventResource = new EventResource(eventService, userService);
    }
    @Test
    public void testGetAllEvents() {
        // create a list of events to return
        List<Event> events = new ArrayList<>();
        events.add(new Event("Test Event 1", new Date(), new Date(), new Date(), true, 100));
        events.add(new Event("Test Event 2", new Date(), new Date(), new Date(), false, 50));
        events.add(new Event("Test Event 3", new Date(), new Date(), new Date(), true, 200));

        // mock the event service to return the list of events
        Mockito.when(eventService.findAllEvents()).thenReturn(events);

        // call the getAllEvents() method on the event resource
        ResponseEntity<List<Event>> response = eventResource.getAllEvents();

        // assert that the response contains the expected events and status code
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(events, response.getBody());
    }



}