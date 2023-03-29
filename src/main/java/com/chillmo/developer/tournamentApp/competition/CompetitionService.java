package com.chillmo.developer.tournamentApp.competition;


import com.chillmo.developer.tournamentApp.competition.h2h.HeadToHead;
import com.chillmo.developer.tournamentApp.competition.h2h.HeadToHeadRepository;
import com.chillmo.developer.tournamentApp.event.domain.Event;
import com.chillmo.developer.tournamentApp.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompetitionService {

    @Autowired
    private HeadToHeadRepository headToHeadRepository;

    public Optional<HeadToHead> createHeadToHeadCompetition() {
        HeadToHead headToHead = new HeadToHead();
        headToHead.setDescription("This Competition is for 1v1");

        headToHeadRepository.save(headToHead);

        Optional<HeadToHead> headToHeadSaved = headToHeadRepository.findById(headToHead.getId());
        return headToHeadSaved;
    }

    public void addUserToHeadToHead(final Event event, final User userOne, final User userTwo){
        if (event.getCompetition() instanceof HeadToHead) {
            ((HeadToHead) event.getCompetition()).setUserOne(userOne);
            ((HeadToHead) event.getCompetition()).setUserTwo(userTwo);
        }
        headToHeadRepository.save((HeadToHead) event.getCompetition());
    }

    // other methods
}

