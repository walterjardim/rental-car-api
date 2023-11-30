package br.org.rentalcarapi.application.gateways;

import java.util.List;

import br.org.rentalcarapi.domain.entity.User;

public interface UserGateway {
    User createUser(User user);
    User getUserByEmail(String email);
    User getUserByLogin(String login);
    User getUserById(Long id);
    List<User> findAll();
    void deleteUser(Long id);
    User updateUser(User user);
}
