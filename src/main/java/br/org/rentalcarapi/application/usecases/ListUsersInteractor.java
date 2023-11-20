package br.org.rentalcarapi.application.usecases;

import java.util.List;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;

public class ListUsersInteractor {
    private final UserGateway userGateway;

    public ListUsersInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public List<User> findAll() {
        return this.userGateway.findAll();
    }
}
