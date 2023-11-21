package br.org.rentalcarapi.application.usecases;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UpdateUserInteractorTest {

    @InjectMocks
    private UpdateUserInteractor updateUserInteractor;

    @Mock
    private UserGateway userGateway;

    @Test
    void testUpdateUserWithSuccess() throws UserNotFoundException {
        User user = new User();
        user.setId(1L);
        Mockito.when(this.userGateway.getUserById(Mockito.anyLong())).thenReturn(user);
        Mockito.when(this.userGateway.updateUser(Mockito.any())).thenReturn(user);
        User updatedUser = this.updateUserInteractor.updateUser(user);
        Assertions.assertNotNull(updatedUser);
    }

    @Test
    void testUpdateUserShouldThrowException() {
        Mockito.when(this.userGateway.getUserById(Mockito.anyLong())).thenReturn(null);
        User user = new User();
        user.setId(1L);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            this.updateUserInteractor.updateUser(user);
        });
    }
}
