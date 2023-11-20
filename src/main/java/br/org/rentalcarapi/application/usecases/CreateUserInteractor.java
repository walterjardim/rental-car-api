package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.EmailAlreadyExistsException;

public class CreateUserInteractor {

    private UserGateway userGateway;
    
    public CreateUserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) throws EmailAlreadyExistsException {
        User existentUser = this.userGateway.getUserByEmail(user.getEmail());
        if (existentUser != null) {
            throw new EmailAlreadyExistsException("Email already exists!");
        }

        return this.userGateway.createUser(user);
    }
}
