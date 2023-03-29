package com.chillmo.developer.tournamentApp.competition.h2h;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadToHeadRepository extends CrudRepository<HeadToHead,Long> {
}
