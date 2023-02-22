package com.chillmo.developer.tournamentApp.user.service;


import com.chillmo.developer.tournamentApp.user.domain.Token;
import com.chillmo.developer.tournamentApp.user.domain.User;

/**
 * Interface for the TokenService Layer.
 */
public interface TokenService {

    /**
     * method used to generate Token.
     *
     * @param user The User for who the token is supposed to be created
     * @return The created Token
     */
    Token generateToken(User user);

    /**
     * method used to find a Token by its unique content.
     *
     * @param content the conent of the wanted Token
     * @return the Token which has the provided content
     */
    Token findTokenByContent(String content);

    /**
     * method used to increase the Time of when the Token is expired.
     *
     * @param token the token of which the Time is supposed to get increased
     **/
    void increaseExpiredTime(Token token);

    /**
     * method used to get The User of a Token.
     *
     * @param tokenContent the String corresponding to the token of which the user is to be found
     * @return the User
     */
    User getUserByToken(String tokenContent);
}
