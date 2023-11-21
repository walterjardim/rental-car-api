package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;

public class DeleteUserInteractor {

    private UserGateway userGateway;

    public DeleteUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public void deleteUser(Long id) throws UserNotFoundException {

        User userToDelete = this.userGateway.getUserById(id);
        if (userToDelete == null) {
            throw new UserNotFoundException("User to delete not found with id " + id);
        }

        this.userGateway.deleteUser(id);
    }
}
