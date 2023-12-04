package br.org.rentalcarapi.application.usecases.impl;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.application.usecases.IDeleteUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;

public class DeleteUserInteractor implements IDeleteUserInteractor {

    private UserGateway userGateway;

    public DeleteUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {

        User userToDelete = this.userGateway.getUserById(id);
        if (userToDelete == null) {
            throw new UserNotFoundException("User to delete not found with id " + id);
        }

        this.userGateway.deleteUser(id);
    }
}
