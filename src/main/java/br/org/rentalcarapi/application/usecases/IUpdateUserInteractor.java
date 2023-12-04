package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;

public interface IUpdateUserInteractor {

    public User updateUser(User user) throws UserNotFoundException;

}
