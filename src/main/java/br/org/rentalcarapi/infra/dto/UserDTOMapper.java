package br.org.rentalcarapi.infra.dto;

import java.util.ArrayList;
import java.util.List;

import br.org.rentalcarapi.domain.entity.Car;
import br.org.rentalcarapi.domain.entity.User;

public class UserDTOMapper {
    public CreateUserResponse toResponse(User user) {
        return new CreateUserResponse(
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getBirthday(),
            user.getLogin(),
            user.getPhone(),
            user.getCars());
    }

    public User toUser(CreateUserRequest createUserRequest) {
        List<Car> cars = getCars(createUserRequest);
        
        return new User(
            createUserRequest.getFirstName(),
            createUserRequest.getLastName(),
            createUserRequest.getEmail(),
            createUserRequest.getBirthday(),
            createUserRequest.getLogin(),
            createUserRequest.getPassword(),
            createUserRequest.getPhone(),
            cars);
    }

    private List<Car> getCars(CreateUserRequest createUserRequest) {
        List<Car> cars = new ArrayList<>();
        if (createUserRequest.getCars() != null) {
            for (CarDTO carDTO : createUserRequest.getCars()) {
                cars.add(new Car(carDTO.getManufactureYear(), carDTO.getLicensePlate(), carDTO.getModel(), carDTO.getColor()));
            }

        }
        return cars;
    }
}
