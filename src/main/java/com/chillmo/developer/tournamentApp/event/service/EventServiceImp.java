package com.chillmo.developer.tournamentApp.event.service;

import com.chillmo.developer.tournamentApp.competition.Competition;
import com.chillmo.developer.tournamentApp.competition.CompetitionRepository;
import com.chillmo.developer.tournamentApp.competition.CompetitionService;
import com.chillmo.developer.tournamentApp.competition.CompetitionType;
import com.chillmo.developer.tournamentApp.competition.h2h.HeadToHead;
import com.chillmo.developer.tournamentApp.event.domain.Event;
import com.chillmo.developer.tournamentApp.event.repository.EventRepository;
import com.chillmo.developer.tournamentApp.user.domain.User;
import com.chillmo.developer.tournamentApp.user.repository.UserRepository;
import com.chillmo.developer.tournamentApp.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EventServiceImp implements EventService {
    @Autowired
    private final EventRepository eventRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CompetitionRepository competitionRepository;

    @Autowired
    private final CompetitionService competitionService;
    @Autowired
    private UserService userService;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Event addEvent(Event event) {
        if (event.getMaxParticipants() == 2) {

            Optional<HeadToHead> headToHead = competitionService.createHeadToHeadCompetition();

            if (headToHead.isPresent()) {
                HeadToHead headToHeadSaved = headToHead.get();
                Competition competition = (Competition) headToHeadSaved;
                event.setCompetition(competition);
            }
        }
        return eventRepository.save(event);
    }

    @Override
    public void closeRegisterToEvent(final Event closedEvent) {
        if (closedEvent.getCompetition().getCompetitionType() == CompetitionType.HEADTOHEAD
                && closedEvent.getRegisteredUser().size() == 2) {
            competitionService.addUserToHeadToHead(closedEvent, closedEvent.getRegisteredUser().iterator().next(),
                    closedEvent.getRegisteredUser().iterator().next());
        }
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
    public Event updateEvent(Event event, Event updatedEvent) {
        if (updatedEvent.getEventName() != null) {
            event.setEventName(updatedEvent.getEventName());
        }
        if (updatedEvent.getBeginDate() != null) {
            event.setBeginDate(updatedEvent.getBeginDate());
        }
        if (updatedEvent.getEndDate() != null) {
            event.setEndDate(updatedEvent.getEndDate());
        }
        if (updatedEvent.getEndRegisterDate() != null) {
            event.setEndRegisterDate(updatedEvent.getEndRegisterDate());
        }
        if (updatedEvent.isRegisterActive() != event.isRegisterActive()) {
            event.setRegisterActive(updatedEvent.isRegisterActive());
        }
        if (updatedEvent.getMaxParticipants() != event.getMaxParticipants()) {
            event.setMaxParticipants(updatedEvent.getMaxParticipants());
        }

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

    @Override
    @Transactional
    public boolean deleteEvent(Event event) {
        if (event == null) {
            return false;
        }
        if (event == null) {
            return false;
        }

        for (User user : event.getParticipants()) {
            userService.removeEventFromParticipatingEvents(event);
        }
        eventRepository.delete(event);
        return true;

    }
}
