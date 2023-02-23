package com.chillmo.developer.tournamentApp.user.controller;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.helix.domain.UserList;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class is a twitch information wrapper.
 */
@RestController
public class TwitchInteraction {
    private final TwitchClient twitchClient;

    public TwitchInteraction() {
        String client_id = "b1n6qld9ymtnh2hprpf8c35b7q4wti";
        String client_secret = "lbb2ray32qednb30muf8jgkrbbyqjq";
        twitchClient = TwitchClientBuilder.builder()
            .withEnableHelix(true)
            .withClientId(client_id)
            .withClientSecret(client_secret)
            .build();

    }

    /**
     * This Method get User information from the Twitch Api.
     *
     * @param channelName get information from specific channel Name;
     * @return a user information.
     */
    public List<User> getUser(final String channelName) {

        UserList resultList = twitchClient.getHelix().getUsers(null, null, List.of(channelName)).execute();
        return new ArrayList<>(resultList.getUsers());
    }

    public boolean checkTwitchExists(final String channelName) {
        UserList resultList = twitchClient.getHelix().getUsers(null, null, List.of(channelName)).execute();
        if (resultList.getUsers().size() == 0)
            return false;
        else
            return true;
    }


}
