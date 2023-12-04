package br.org.rentalcarapi.application.usecases.impl;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.application.usecases.impl.DeleteUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteUserInteractorTest {

    @InjectMocks
    private DeleteUserInteractor deleteUserInteractor;

    @Mock
    private UserGateway userGateway;

    @Test
    void testDeleteUserWithSuccess() throws UserAlreadyExistsException, UserNotFoundException {
        Mockito.when(this.userGateway.getUserById(Mockito.anyLong())).thenReturn(new User());
        Mockito.doNothing().when(this.userGateway).deleteUser(Mockito.anyLong());

        this.deleteUserInteractor.deleteUser(1L);
        Mockito.verify(this.userGateway, Mockito.times(1)).deleteUser(1L);
    }
}
