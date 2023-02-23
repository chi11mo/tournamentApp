package com.chillmo.developer.tournamentApp.user.repository;


import com.chillmo.developer.tournamentApp.user.domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to interact with the Token-Objects stored in the DB.
 */
@Repository
public interface TokenRepository extends CrudRepository<Token, String> {

     Token findTokenByTokenContent(String tokenContent);

}
