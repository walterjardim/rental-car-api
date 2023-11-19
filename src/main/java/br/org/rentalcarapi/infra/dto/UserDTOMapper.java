package br.org.rentalcarapi.infra.dto;

import br.org.rentalcarapi.domain.entity.User;

public class UserDTOMapper {
    public CreateUserResponse toResponse(User user) {
        return new CreateUserResponse(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getBirthday(),
            user.getLogin(),
            user.getPhone(),
            user.getCars());
    }

    public User toUser(CreateUserRequest createUserRequest) {
        return new User(
            createUserRequest.firstName(),
            createUserRequest.lastName(),
            createUserRequest.email(),
            createUserRequest.birthday(),
            createUserRequest.login(),
            createUserRequest.password(),
            createUserRequest.phone(),
            createUserRequest.cars());
    }
}
