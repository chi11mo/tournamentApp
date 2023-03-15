package com.chillmo.developer.tournamentApp.event.domain;


import java.util.Date;
import java.util.Set;


import com.chillmo.developer.tournamentApp.user.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String eventName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date beginDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date endRegisterDate;

    private boolean isRegisterActive;

    private int maxParticipants;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "event_user",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    @JsonIgnore
    Set<User> registeredUser;


    public Event(String eventName, Date beginDate, Date endDate, Date endRegisterDate, boolean isRegisterActive, int maxParticipants) {
        this.eventName = eventName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.endRegisterDate = endRegisterDate;
        this.isRegisterActive = isRegisterActive;
        this.maxParticipants = maxParticipants;
    }

    public Event() {

    }

}
