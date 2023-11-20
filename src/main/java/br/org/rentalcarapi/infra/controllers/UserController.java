package br.org.rentalcarapi.infra.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.rentalcarapi.application.usecases.CreateUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import br.org.rentalcarapi.infra.dto.CreateUserRequest;
import br.org.rentalcarapi.infra.dto.CreateUserResponse;
import br.org.rentalcarapi.infra.dto.UserDTOMapper;
import jakarta.validation.Valid;

@RestController
@RequestMapping("users")
@Validated
public class UserController {
    
    private final CreateUserInteractor createUserInteractor;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUserInteractor createUserInteractor, UserDTOMapper userDTOMapper) {
        this.createUserInteractor = createUserInteractor;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws UserAlreadyExistsException {
        User createdUser = null;
        createdUser = this.createUserInteractor.createUser(this.userDTOMapper.toUser(createUserRequest));
        return ResponseEntity.ok(this.userDTOMapper.toResponse(createdUser));
    }
}
