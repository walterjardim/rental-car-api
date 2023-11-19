package br.org.rentalcarapi.application.gateways;

import br.org.rentalcarapi.domain.entity.User;

public interface UserGateway {
    User createUser(User user);
}
