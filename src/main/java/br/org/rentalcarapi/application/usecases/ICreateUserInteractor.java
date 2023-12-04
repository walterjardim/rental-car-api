package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;

public interface ICreateUserInteractor {

    public User createUser(User user) throws UserAlreadyExistsException;
}
