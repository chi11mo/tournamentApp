package com.chillmo.developer.tournamentApp.competition.h2h;
import com.chillmo.developer.tournamentApp.competition.CompetitionType;
import com.chillmo.developer.tournamentApp.competition.Competition;
import com.chillmo.developer.tournamentApp.event.domain.Event;
import com.chillmo.developer.tournamentApp.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "head_to_head")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorValue("HEAD2HEAD")
public class HeadToHead extends Competition{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userOne_id")
    private User userOne;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userTwo_id")
    private User userTwo;


    public HeadToHead() {
        super(CompetitionType.HEADTOHEAD);
    }





    public HeadToHead(Competition competition, User userOne, User userTwo) {
        this.competition = competition;
        this.userOne = userOne;
        this.userTwo = userTwo;
    }
}
