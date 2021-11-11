package com.moneyhop.bookmark.controller;

import com.moneyhop.bookmark.entity.Users;
import com.moneyhop.bookmark.exception.EmailAlreadyRegisteredException;
import com.moneyhop.bookmark.exception.UserNameExistsException;
import com.moneyhop.bookmark.model.request.RegistrationRequest;
import com.moneyhop.bookmark.model.response.RegistrationResponse;
import com.moneyhop.bookmark.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


    @RequestMapping(path="/register", method = RequestMethod.POST)
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest) throws EmailAlreadyRegisteredException, UserNameExistsException {
        //We have checked that none of the fields are null or empty
        //and relevant fields are well-formed and of suitable length using @Valid before calling service method
        Users registeredUser = registrationService.register(registrationRequest);
        RegistrationResponse response = new RegistrationResponse();
        response.setId(registeredUser.getId());
        response.setUserName(registeredUser.getUserName());
        response.setEmail(registeredUser.getEmail());
        response.setRole(registeredUser.getRole());
        return new ResponseEntity<RegistrationResponse>(response, HttpStatus.OK);

    }
}
