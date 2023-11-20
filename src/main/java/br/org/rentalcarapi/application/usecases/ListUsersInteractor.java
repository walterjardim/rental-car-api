package br.org.rentalcarapi.application.usecases;

import java.util.List;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;

public class ListUsersInteractor {
    private final UserGateway userGateway;

    public ListUsersInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public List<User> findAll() {
        return this.userGateway.findAll();
    }

    public User findById(Long id) throws UserNotFoundException {
        User user = this.userGateway.getUserById(id);
        if (user == null) {
            throw new UserNotFoundException("User not found with id " + id);
        }
        return user;
    }
}
