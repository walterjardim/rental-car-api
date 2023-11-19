package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;

public class CreateUserInteractor {

    private UserGateway userGateway;
    
    public CreateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) {
        return this.userGateway.createUser(user);
    }
}
