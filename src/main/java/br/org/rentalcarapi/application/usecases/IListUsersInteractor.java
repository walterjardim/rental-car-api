package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;

import java.util.List;

public interface IListUsersInteractor {

    public List<User> findAll();

    public User findById(Long id) throws UserNotFoundException;
}
