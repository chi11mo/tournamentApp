package com.chillmo.developer.tournamentApp.competition;

import com.chillmo.developer.tournamentApp.event.domain.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends CrudRepository<Event,Long> {
}
