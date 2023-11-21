package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;

public class UpdateUserInteractor {

    private final UserGateway userGateway;

    public UpdateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User updateUser(User user) throws UserNotFoundException {
        User userFromDb = this.userGateway.getUserById(user.getId());

        if (userFromDb == null) {
            throw new UserNotFoundException("User not found with id " + user.getId());
        }

        return this.userGateway.updateUser(user);
    }
}
