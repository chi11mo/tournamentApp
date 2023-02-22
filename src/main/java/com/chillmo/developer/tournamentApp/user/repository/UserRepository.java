package com.chillmo.developer.tournamentApp.user.repository;

import com.chillmo.developer.tournamentApp.user.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends CrudRepository<User,Long> {

    void deleteById(Long id);

    User findUserById(Long id);

    User findUserByeMail(@Param("user_eMail")String eMail);


    /**
     * Finds a user by the given mail adress.
     *
     * @param eMail the mail of the searched {@link User}
     * @return an Optional with the requested {@link User} if found
     */



}
