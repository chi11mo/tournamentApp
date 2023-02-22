package com.chillmo.developer.tournamentApp.user.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final String twitch;
    private final String eMail;
    private final String discord;
    private  final String psn;
    private final String steam;
    private final String password;
}
