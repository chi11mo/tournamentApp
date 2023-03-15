package com.chillmo.developer.tournamentApp.user.registration;

import com.chillmo.developer.tournamentApp.user.domain.Token;
import com.chillmo.developer.tournamentApp.user.domain.User;
import com.chillmo.developer.tournamentApp.user.service.TokenService;
import com.chillmo.developer.tournamentApp.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistrationService {
    private EmailValidator emailValidator;
    private UserService userService;

    private TokenService tokenService;
    public ResponseEntity<User> register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEMail());



        //logger.info("Adding user with mail {}", user.getEMail());

        if (isValidEmail) {
           return new ResponseEntity<>(userService.addUser(new User(
                    request.getTwitch(),
                    request.getEMail(),
                    request.getPassword()
            )),HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    public String confirmToken(String token){
        // Find the token in the repository by its content
        Token confirmationToken = tokenService.findTokenByContent(token);

        // Check if the token exists and is not expired
        if (confirmationToken != null && confirmationToken.getExpiresAt().isAfter(LocalDateTime.now())) {
            // Get the associated user's email address
           // String userEmail = confirmationToken.getUser().getEmail();

            // Delete the token from the repository
           // tokenService.deleteToken(confirmationToken);

            // Return the user's email address
            return "confirmed";
        } else if(confirmationToken != null){
            // If the token is invalid, return null
            return "Token not exist";
        } else{
            return "Token expired";
        }
    }
}
