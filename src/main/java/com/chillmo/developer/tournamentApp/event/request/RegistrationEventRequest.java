package com.chillmo.developer.tournamentApp.event.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationEventRequest {

    private String eventName;
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date beginDate;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endDate;

    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Date endRegisterDate;

    private boolean isRegisterActive;

    private int maxParticipants;
}
