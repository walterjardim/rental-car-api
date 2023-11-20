package br.org.rentalcarapi.infra.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.rentalcarapi.application.usecases.CreateUserInteractor;
import br.org.rentalcarapi.application.usecases.ListUsersInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;
import br.org.rentalcarapi.infra.dto.CreateUserRequest;
import br.org.rentalcarapi.infra.dto.UserResponseDTO;
import br.org.rentalcarapi.infra.dto.UserDTOMapper;
import jakarta.validation.Valid;
import jakarta.ws.rs.PathParam;

@RestController
@RequestMapping("users")
@Validated
public class UserController {
    
    private final CreateUserInteractor createUserInteractor;
    private final UserDTOMapper userDTOMapper;
    private final ListUsersInteractor listUsersInteractor;

    public UserController(CreateUserInteractor createUserInteractor, UserDTOMapper userDTOMapper,
        ListUsersInteractor listUsersInteractor) {
        this.createUserInteractor = createUserInteractor;
        this.userDTOMapper = userDTOMapper;
        this.listUsersInteractor = listUsersInteractor;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) throws UserAlreadyExistsException {
        User createdUser = this.createUserInteractor.createUser(this.userDTOMapper.toUser(createUserRequest));
        return ResponseEntity.ok(this.userDTOMapper.toResponse(createdUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        List<User> users = this.listUsersInteractor.findAll();
        return ResponseEntity.ok(this.userDTOMapper.toResponseList(users));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) throws UserNotFoundException {
        User user = this.listUsersInteractor.findById(id);
        return ResponseEntity.ok(this.userDTOMapper.toResponse(user));
    }
}
