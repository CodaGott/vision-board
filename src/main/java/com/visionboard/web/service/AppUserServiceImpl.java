package com.visionboard.web.service;

import com.visionboard.data.dto.registration.SignUpForm;
import com.visionboard.data.model.Role;
import com.visionboard.data.model.User;
import com.visionboard.data.repository.UserRepository;
import com.visionboard.exceptions.UserException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements AppUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(SignUpForm signUpForm){
        User user = new User();

        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()){
            throw new UserException("Email already exist sign in or register with another email");
        }else {
            modelMapper.map(signUpForm, user);
            user.setRoles(Role.USER);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    @Override
    public User registerAdmin(SignUpForm signUpForm) throws UserException {
        User user = new User();

        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()){
            throw new UserException("Email already exist sign in or register with another email");
        }else {
            modelMapper.map(signUpForm, user);
            user.setRoles(Role.ADMIN);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

}
