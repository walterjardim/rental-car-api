package br.org.rentalcarapi.infra.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.rentalcarapi.application.usecases.CreateUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
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
    public CreateUserResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        User createdUser = this.createUserInteractor.createUser(this.userDTOMapper.toUser(createUserRequest));
        return this.userDTOMapper.toResponse(createdUser);
    }
}
