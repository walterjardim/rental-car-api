package br.org.rentalcarapi.application.usecases.impl;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.application.usecases.ICreateUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;

public class CreateUserInteractor implements ICreateUserInteractor {

    private UserGateway userGateway;

    public CreateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        User userByEmail = this.userGateway.getUserByEmail(user.getEmail());
        if (userByEmail != null) {
            throw new UserAlreadyExistsException("Email already exists!");
        }

        User userByLogin = this.userGateway.getUserByLogin(user.getLogin());
        if (userByLogin != null) {
            throw new UserAlreadyExistsException("Login already exists!");
        }

        return this.userGateway.createUser(user);
    }
}
