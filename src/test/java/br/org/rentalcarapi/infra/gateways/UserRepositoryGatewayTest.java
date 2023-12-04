package br.org.rentalcarapi.infra.gateways;

import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.infra.persistence.entity.UserEntity;
import br.org.rentalcarapi.infra.persistence.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryGatewayTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserRepositoryGateway userRepositoryGateway;

    @Test
    void testCreateUserWithSuccess() {
        Mockito.when(this.userEntityMapper.toEntity(Mockito.any())).thenReturn(new UserEntity());
        Mockito.when(this.userRepository.save(Mockito.any())).thenReturn(new UserEntity());
        User userDomainObject = new User();
        userDomainObject.setPassword("password");
        Mockito.when(this.userEntityMapper.toDomainObject(Mockito.any())).thenReturn(userDomainObject);
        Mockito.when(this.bCryptPasswordEncoder.encode(Mockito.anyString())).thenReturn("encoded");

        User user = this.userRepositoryGateway.createUser(userDomainObject);

        Assertions.assertNotNull(user);
    }

    @Test
    void testGetUserByEmailWithSuccess() {
        Mockito.when(this.userRepository.findByEmail(Mockito.anyString())).thenReturn(new UserEntity());
        Mockito.when(this.userEntityMapper.toDomainObject(Mockito.any())).thenReturn(new User());
        User user = this.userRepositoryGateway.getUserByEmail("test@test.com");
        Assertions.assertNotNull(user);
    }

    @Test
    void testGetUserByEmailReturnNull() {
        Mockito.when(this.userRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        User user = this.userRepositoryGateway.getUserByEmail("");
        Assertions.assertNull(user);
    }

    @Test
    void testGetUserByLoginWithSuccess() {
        Mockito.when(this.userRepository.findByLogin(Mockito.anyString())).thenReturn(new UserEntity());
        Mockito.when(this.userEntityMapper.toDomainObject(Mockito.any())).thenReturn(new User());
        User user = this.userRepositoryGateway.getUserByLogin("login");
        Assertions.assertNotNull(user);
    }

    @Test
    void testGetUserByLoginReturnNull() {
        Mockito.when(this.userRepository.findByLogin(Mockito.anyString())).thenReturn(null);
        User user = this.userRepositoryGateway.getUserByLogin("");
        Assertions.assertNull(user);
    }

    @Test
    void testFindAllWithSuccess() {
        Mockito.when(this.userRepository.findAll()).thenReturn(List.of(new UserEntity()));
        Mockito.when(this.userEntityMapper.toDomainObjectList(Mockito.any())).thenReturn(List.of(new User()));
        List<User> users = this.userRepositoryGateway.findAll();
        Assertions.assertFalse(users.isEmpty());
    }

    @Test
    void testGetUserByIdWithSuccess() {
        Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new UserEntity()));
        Mockito.when(this.userEntityMapper.toDomainObject(Mockito.any())).thenReturn(new User());
        User user = this.userRepositoryGateway.getUserById(1L);
        Assertions.assertNotNull(user);
    }

    @Test
    void testGetUserByIdReturnNull() {
        Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        User user = this.userRepositoryGateway.getUserById(1L);
        Assertions.assertNull(user);
    }

    @Test
    void testDeleteUserByWithSuccess() {
        Mockito.when(this.userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new UserEntity()));
        Mockito.doNothing().when(this.userRepository).delete(Mockito.any());
        this.userRepositoryGateway.deleteUser(1L);
        Mockito.verify(this.userRepository, Mockito.times(1)).delete(Mockito.any());
    }

    @Test
    void testUpdateUserWithSuccess() {
        Mockito.when(this.userEntityMapper.toEntity(Mockito.any())).thenReturn(new UserEntity());
        Mockito.when(this.userRepository.save(Mockito.any())).thenReturn(new UserEntity());
        Mockito.when(this.userEntityMapper.toDomainObject(Mockito.any())).thenReturn(new User());
        User user = new User();
        user.setId(1L);

        User updatedUser = this.userRepositoryGateway.updateUser(user);
        Assertions.assertNotNull(updatedUser);
    }
}
