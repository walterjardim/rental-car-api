package br.org.rentalcarapi.infra.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.org.rentalcarapi.application.usecases.CreateUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.EmailAlreadyExistsException;
import br.org.rentalcarapi.infra.dto.CreateUserRequest;
import br.org.rentalcarapi.infra.dto.CreateUserResponse;
import br.org.rentalcarapi.infra.dto.UserDTOMapper;

@RestController
@RequestMapping("users")
public class UserController {
    
    private final CreateUserInteractor createUserInteractor;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUserInteractor createUserInteractor, UserDTOMapper userDTOMapper) {
        this.createUserInteractor = createUserInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) throws EmailAlreadyExistsException {
        User createdUser = null;
        createdUser = this.createUserInteractor.createUser(this.userDTOMapper.toUser(createUserRequest));
        return ResponseEntity.ok(this.userDTOMapper.toResponse(createdUser));
    }
}
