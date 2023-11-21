package br.org.rentalcarapi.infra.controllers;

import java.util.List;

import br.org.rentalcarapi.application.usecases.DeleteUserInteractor;
import br.org.rentalcarapi.application.usecases.UpdateUserInteractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.org.rentalcarapi.application.usecases.CreateUserInteractor;
import br.org.rentalcarapi.application.usecases.ListUsersInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;
import br.org.rentalcarapi.infra.dto.UserRequestDTO;
import br.org.rentalcarapi.infra.dto.UserDTOMapper;
import br.org.rentalcarapi.infra.dto.UserResponseDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    
    @Autowired
    private CreateUserInteractor createUserInteractor;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    private ListUsersInteractor listUsersInteractor;

    @Autowired
    private DeleteUserInteractor deleteUserInteractor;

    @Autowired
    private UpdateUserInteractor updateUserInteractor;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) throws UserAlreadyExistsException {
        User createdUser = this.createUserInteractor.createUser(this.userDTOMapper.toUser(userRequestDTO));
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

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws UserNotFoundException {
        this.deleteUserInteractor.deleteUser(id);
        return ResponseEntity.ok("User deleted");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO userRequestDTO) throws UserNotFoundException {
        userRequestDTO.setId(id);
        User updatedUser = this.updateUserInteractor.updateUser(this.userDTOMapper.toUser(userRequestDTO));
        return ResponseEntity.ok(this.userDTOMapper.toResponse(updatedUser));
    }
}
