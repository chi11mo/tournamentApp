package com.chillmo.developer.tournamentApp.user.registration;

import com.chillmo.developer.tournamentApp.user.response.RegistrationResponse;
import com.chillmo.developer.tournamentApp.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/registration")
@AllArgsConstructor
public class RegistrationController {
    private UserService userService;
    private RegistrationService registrationService;

    /**
     * Registers a new user with the specified registration data.
     *
     * @param request the registration data for the new user
     * @return a ResponseEntity containing either the newly created user data and a 201 Created status code,
     * or an ErrorResponse with a 409 Conflict status code if a user with the same email already exists
     */
    @PostMapping
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {
        if (userService.findUserByeMail(request.getEMail()) == null) {
           if(registrationService.register(request).getStatusCode()==HttpStatus.OK) {
               return ResponseEntity.status(HttpStatus.CREATED)
                       .body(new RegistrationResponse("User with email " + request.getEMail() + " is SignUp."));
           }
           else{
               return ResponseEntity.status(HttpStatus.CONFLICT)
                       .body(new RegistrationResponse("User with email " + request.getEMail() + " is not a valid Email."));
           }
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new RegistrationResponse("User with email " + request.getEMail() + " already exists."));
        }

    }
}
