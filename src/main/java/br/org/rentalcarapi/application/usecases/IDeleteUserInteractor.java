package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;

public interface IDeleteUserInteractor {

    public void deleteUser(Long id) throws UserNotFoundException;
}
