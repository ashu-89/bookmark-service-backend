package com.moneyhop.bookmark.service;

import com.moneyhop.bookmark.entity.Users;
import com.moneyhop.bookmark.exception.EmailAlreadyRegisteredException;
import com.moneyhop.bookmark.exception.UserNameExistsException;
import com.moneyhop.bookmark.model.request.Registration;
import com.moneyhop.bookmark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    public Users register(Registration registration) throws EmailAlreadyRegisteredException, UserNameExistsException {

        //check if email already registered
        Users registeredUsers = userRepository.findUserByEmail(registration.getEmail());
        if( null != registeredUsers)
            throw new EmailAlreadyRegisteredException("REG-001", "This email is already registered with us. Please sign in.");

        //check if user name is taken
        registeredUsers = userRepository.findUserByUserName(registration.getUserName());
        if( null != registeredUsers)
            throw new UserNameExistsException("REG-002", "Username is already taken. Please choose a different username");

        //Instantiate registered user
        registeredUsers = new Users();

        //Populate all fields of registeredUser from request model and persist in db
        registeredUsers.setFirstName(registration.getFirstName());
        registeredUsers.setLastName(registration.getLastName());
        registeredUsers.setUserName(registration.getUserName());
        registeredUsers.setEmail(registration.getEmail());
        registeredUsers.setPassword(registration.getPassword());
        registeredUsers.setRole("USER");

        //return the saved user
        return userRepository.save(registeredUsers);

    }
}
