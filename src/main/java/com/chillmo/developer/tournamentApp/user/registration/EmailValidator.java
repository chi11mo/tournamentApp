package com.chillmo.developer.tournamentApp.user.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String email) {
        // Create a pattern to match against email addresses
        Pattern pattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(email);

        // Return whether the input string matches the email address pattern
        return matcher.matches();
    }
}
