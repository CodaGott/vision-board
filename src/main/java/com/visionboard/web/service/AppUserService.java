package com.visionboard.web.service;

import com.visionboard.data.dto.registration.SignUpForm;
import com.visionboard.data.model.User;
import com.visionboard.exceptions.UserException;

public interface AppUserService {
    User registerUser(SignUpForm signUpForm) throws UserException;
    User registerAdmin(SignUpForm signUpForm) throws UserException;
}
