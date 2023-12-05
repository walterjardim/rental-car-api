package br.org.rentalcarapi.infra.dto;

import br.org.rentalcarapi.domain.entity.Car;
import br.org.rentalcarapi.domain.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserDTOMapperTest {

    @InjectMocks
    private UserDTOMapper userDTOMapper;

    @Test
    void testToResponseWithSuccess() {
        List<Car> cars = Collections.singletonList(
                new Car(2023, "112", "model", "color"));
        User userDomain = new User(
                "First",
                "Last",
                "email@test.com",
                new Date(),
                "login",
                "password",
                "81988888888",
                cars
        );

        UserResponseDTO userResponseDTO = this.userDTOMapper.toResponse(userDomain);

        Assertions.assertNotNull(userResponseDTO);
    }

    @Test
    void testToUserWithSuccess() {
        List<CarDTO> cars = Collections.singletonList(
                new CarDTO(2023, "123", "model", "color")
        );
        UserRequestDTO userRequestDTO = new UserRequestDTO(
                1L,
                "First",
                "Last",
                "email@test.com",
                new Date(),
                "login",
                "password",
                "81988888888",
                cars
        );

        User user = this.userDTOMapper.toUser(userRequestDTO);
        Assertions.assertNotNull(user);
    }

    @Test
    void testToResponseListWithSuccess() {
        List<User> users = Collections.singletonList(new User(
                "First",
                "Last",
                "email@test.com",
                new Date(),
                "login",
                "password",
                "81988888888",
                new ArrayList<>()
        ));
        List<UserResponseDTO> responseList = this.userDTOMapper.toResponseList(users);

        Assertions.assertFalse(responseList.isEmpty());
    }
}
