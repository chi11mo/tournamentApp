package com.chillmo.developer.tournamentApp.user.service;


import com.chillmo.developer.tournamentApp.user.controller.TwitchInteraction;
import com.chillmo.developer.tournamentApp.user.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@AllArgsConstructor
public class TwitchService {

    private final TwitchInteraction twitchInteraction;


    public boolean checkTwitchExists(final User user){
        System.out.println(twitchInteraction.getUser(user.getTwitch()).get(0).getProfileImageUrl());
        return true;
    }

    public User setTwitchImg(final User user){
        user.setImgUrl(twitchInteraction.getUser(user.getTwitch()).get(0).getProfileImageUrl());
        return user;
    }
}
