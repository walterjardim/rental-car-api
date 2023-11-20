package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserAlreadyExistsException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class CreateUserInteractorTest {

    @Autowired
    private CreateUserInteractor createUserInteractor;

    @MockBean
    private UserGateway userGateway;

    @Test
    void testCreateUserWithSuccess() throws UserAlreadyExistsException {
        User user = new User();
        Mockito.when(this.userGateway.getUserByEmail(Mockito.anyString())).thenReturn(null);
        Mockito.when(this.userGateway.getUserByLogin(Mockito.anyString())).thenReturn(null);
        Mockito.when(this.userGateway.createUser(Mockito.any())).thenReturn(user);

        User createdUser = this.createUserInteractor.createUser(user);

        Assertions.assertNotNull(createdUser);
    }
}
