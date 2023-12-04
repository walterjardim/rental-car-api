package br.org.rentalcarapi.infra.controllers;

import br.org.rentalcarapi.application.usecases.ICreateUserInteractor;
import br.org.rentalcarapi.application.usecases.IDeleteUserInteractor;
import br.org.rentalcarapi.application.usecases.IListUsersInteractor;
import br.org.rentalcarapi.application.usecases.IUpdateUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;
import br.org.rentalcarapi.infra.dto.UserDTOMapper;
import br.org.rentalcarapi.infra.dto.UserRequestDTO;
import br.org.rentalcarapi.infra.dto.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private ICreateUserInteractor createUserInteractor;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Autowired
    private IListUsersInteractor listUsersInteractor;

    @Autowired
    private IDeleteUserInteractor deleteUserInteractor;

    @Autowired
    private IUpdateUserInteractor updateUserInteractor;

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
