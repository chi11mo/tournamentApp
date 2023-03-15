package com.chillmo.developer.tournamentApp.event.service;

import com.chillmo.developer.tournamentApp.event.domain.Event;
import com.chillmo.developer.tournamentApp.event.repository.EventRepository;
import com.chillmo.developer.tournamentApp.user.domain.User;
import com.chillmo.developer.tournamentApp.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EventServiceImp implements EventService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public void addUser(Event event, User user) {

        event.getRegisteredUser().add(user);
        user.getParticipatingEvents().add(event);

        eventRepository.save(event);
        userRepository.save(user);

    }

    @Override
    public void removeUser(Event event, User user) {

        User foundUser = event.getRegisteredUser().stream().filter(u -> u.getId() == user.getId()).findFirst().orElse(null);
        if (user != null) {
            event.getRegisteredUser().remove(user);
            user.getParticipatingEvents().remove(this);
        }

        eventRepository.save(event);
        userRepository.save(foundUser);
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public List<Event> findAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }

    @Override
    public Event findEventById(long eventId) {
        return eventRepository.findEventById(eventId);
    }
}
