package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

@SpringBootTest
public class ListUsersInteractorTest {

    @Autowired
    private ListUsersInteractor listUsersInteractor;

    @MockBean
    private UserGateway userGateway;

    @Test
    void testFindAllUsersWithSuccess() {
        List<User> users = List.of(new User());
        Mockito.when(this.userGateway.findAll()).thenReturn(users);
        List<User> usersFromDb = this.listUsersInteractor.findAll();
        Assertions.assertNotNull(usersFromDb);
        Assertions.assertFalse(usersFromDb.isEmpty());
    }

    @Test
    void testFindByIdWithSuccess() throws UserNotFoundException {
        Mockito.when(this.userGateway.getUserById(Mockito.anyLong())).thenReturn(new User());
        Assertions.assertNotNull(this.listUsersInteractor.findById(1L));
    }

    @Test
    void testFindByIdShouldThrowException() {
        Mockito.when(this.userGateway.getUserById(Mockito.anyLong())).thenReturn(null);

        Assertions.assertThrows(UserNotFoundException.class, () -> {
            this.listUsersInteractor.findById(1L);
        });
    }
}
