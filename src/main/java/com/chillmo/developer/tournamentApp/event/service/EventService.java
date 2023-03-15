package com.chillmo.developer.tournamentApp.event.service;

import com.chillmo.developer.tournamentApp.event.domain.Event;
import com.chillmo.developer.tournamentApp.user.domain.User;

import java.util.List;

public interface EventService {
    /**
     * This method adds an new  {@link Event} to repository
     *
     * @param event who will be added.
     */
    Event addEvent(Event event);

    /**
     * This method add a {@link User} to the participants list.
     *
     * @param user  who will be added.
     * @param event to find the {@link Event}.
     */
    void addUser(Event event, User user);

    /**
     * This method remove a  {@link User} to the participants list.
     *
     * @param user  who will be removed.
     * @param event to find the event.
     */
    void removeUser(Event event, User user);

    /**
     * This method update given {@link Event};
     *
     * @param event who will be updated.
     * @return the updated event.
     */
    Event updateEvent(Event event);

    /**
     * give a list of all current {@link Event}.
     *
     * @return the list of {@link Event}.
     */
    List<Event> findAllEvents();

    Event findEventById(long eventId);
}
