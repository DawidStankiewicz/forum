package com.github.dawidstankiewicz.forum.controller.rest;

import com.github.dawidstankiewicz.forum.facade.UserCreationServiceFacade;
import com.github.dawidstankiewicz.forum.form.UserCreationForm;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dawid Stankiewicz on 08.08.2017.
 */

@RestController
@RequestMapping(value = "/api")
public class UserCreationRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreationRestController.class);

    @Autowired
    private UserCreationServiceFacade userCreationServiceFacade;

    @PostMapping("/users")

    public ResponseEntity create(@Valid @RequestBody UserCreationForm userCreationForm,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.info(" bad request err for fields: {}", bindingResult.getSuppressedFields().length );
            // TODO global handler exception
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        LOGGER.info("CREATE NEW USER: {}", userCreationForm.getUsername());
        userCreationServiceFacade.create(userCreationForm.getNewUserEntity());
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
