package com.chillmo.developer.tournamentApp.user.repository;


import com.chillmo.developer.tournamentApp.user.domain.Token;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository to interact with the Token-Objects stored in the DB.
 */
public interface TokenRepository extends CrudRepository<Token, String> {

     Token findTokenByTokenContent(String tokenContent);

}
