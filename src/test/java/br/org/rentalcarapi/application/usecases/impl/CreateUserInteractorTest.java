package br.org.rentalcarapi.application.usecases.impl;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.application.usecases.impl.CreateUserInteractor;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateUserInteractorTest {

    @InjectMocks
    private CreateUserInteractor createUserInteractor;

    @Mock
    private UserGateway userGateway;

    @Test
    void testCreateUserWithSuccess() throws UserAlreadyExistsException {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("login");

        Mockito.when(this.userGateway.getUserByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(this.userGateway.getUserByLogin(Mockito.anyString())).thenReturn(null);
        Mockito.when(this.userGateway.createUser(Mockito.any())).thenReturn(user);

        User createdUser = this.createUserInteractor.createUser(user);

        Assertions.assertNotNull(createdUser);
    }

    @Test
    void testCreateUserWithSameEmailShouldThrowUserAlreadyExistsException() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("login");

        Mockito.when(this.userGateway.getUserByEmail(Mockito.anyString())).thenReturn(user);

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            this.createUserInteractor.createUser(user);
        });
    }

    @Test
    void testCreateUserWithSameLoginShouldThrowUserAlreadyExistsException() {
        User user = new User();
        user.setEmail("test@test.com");
        user.setLogin("login");

        Mockito.when(this.userGateway.getUserByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(this.userGateway.getUserByLogin(Mockito.anyString())).thenReturn(user);

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            this.createUserInteractor.createUser(user);
        });
    }
}
