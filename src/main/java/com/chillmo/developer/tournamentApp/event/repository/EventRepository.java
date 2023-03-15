package com.chillmo.developer.tournamentApp.event.repository;

import com.chillmo.developer.tournamentApp.event.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
    void deleteById(long id);

    Event findEventById(long id);


}
