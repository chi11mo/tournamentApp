package com.chillmo.developer.tournamentApp.competition;

import com.chillmo.developer.tournamentApp.competition.h2h.HeadToHead;
import com.chillmo.developer.tournamentApp.event.domain.Event;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "competition")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING)
public abstract class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "competition_type")
    private CompetitionType competitionType;



    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Event event;

    public Competition(CompetitionType competitionType, String description, Event event) {
        this.competitionType = competitionType;
        this.description = description;
        this.event = event;
    }
    public Competition(CompetitionType competitionType) {
        this.competitionType = competitionType;
    }
}
