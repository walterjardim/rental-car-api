package br.org.rentalcarapi.infra.gateways;

import br.org.rentalcarapi.domain.entity.User;
import br.org.rentalcarapi.infra.persistence.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserEntityMapperTest {

    @Mock
    private CarEntityMapper carEntityMapper;

    @InjectMocks
    private UserEntityMapper userEntityMapper;

    @Test
    public void testToEntityWithSuccess() {
        User userDomain = new User(
                "First",
                "Last",
                "email@test.com",
                new Date(),
                "login",
                "password",
                "81988888888",
                new ArrayList<>()
        );
        Mockito.when(this.carEntityMapper.toEntityList(Mockito.anyList())).thenReturn(new ArrayList<>());

        UserEntity userEntity = this.userEntityMapper.toEntity(userDomain);

        Assertions.assertNotNull(userEntity);
        Assertions.assertEquals(userEntity.getLogin(), userDomain.getLogin());
    }

    @Test
    public void testToDomainObjectWithSuccess() {
        UserEntity userEntity = UserEntity
                .builder()
                .cars(new ArrayList<>())
                .email("email@test.com")
                .login("login")
                .phone("81988888888")
                .birthday(new Date())
                .firstName("First")
                .lastName("Last")
                .password("password")
                .cars(new ArrayList<>())
                .build();

        Mockito.when(this.carEntityMapper.toDomainObjectList(Mockito.anyList())).thenReturn(new ArrayList<>());

        User user = this.userEntityMapper.toDomainObject(userEntity);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getLogin(), userEntity.getLogin());
    }

    @Test
    public void testToDomainObjectListWithSuccess() {
        List<UserEntity> userEntities = Collections.singletonList(
                UserEntity
                        .builder()
                        .cars(new ArrayList<>())
                        .email("email@test.com")
                        .login("login")
                        .phone("81988888888")
                        .birthday(new Date())
                        .firstName("First")
                        .lastName("Last")
                        .password("password")
                        .cars(new ArrayList<>())
                        .build()
        );

        List<User> users = this.userEntityMapper.toDomainObjectList(userEntities);
        Assertions.assertFalse(users.isEmpty());
    }
}
