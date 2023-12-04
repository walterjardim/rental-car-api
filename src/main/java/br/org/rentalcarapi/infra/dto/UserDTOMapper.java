package br.org.rentalcarapi.infra.dto;

import java.util.ArrayList;
import java.util.List;

import br.org.rentalcarapi.domain.entity.Car;
import br.org.rentalcarapi.domain.entity.User;

public class UserDTOMapper {
    public UserResponseDTO toResponse(User user) {
        List<CarDTO> carsDTO = new ArrayList<>();
        if (user.getCars() != null) {
            for (Car car : user.getCars()) {
                carsDTO.add(new CarDTO(car.getManufactureYear(), car.getLicensePlate(), car.getModel(), car.getColor()));
            }
        }
        return new UserResponseDTO(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getBirthday(),
            user.getLogin(),
            user.getPhone(),
            carsDTO);
    }

    public User toUser(UserRequestDTO userRequestDTO) {
        List<Car> cars = getCars(userRequestDTO.cars());
        
        return new User(
            userRequestDTO.id(),
            userRequestDTO.firstName(),
            userRequestDTO.lastName(),
            userRequestDTO.email(),
            userRequestDTO.birthday(),
            userRequestDTO.login(),
            userRequestDTO.password(),
            userRequestDTO.phone(),
            cars);
    }

    private List<Car> getCars(List<CarDTO> carsDTO) {
        List<Car> cars = new ArrayList<>();
        if (carsDTO != null) {
            for (CarDTO carDTO : carsDTO) {
                cars.add(new Car(carDTO.manufactureYear(), carDTO.licensePlate(), carDTO.model(), carDTO.color()));
            }

        }
        return cars;
    }

    public List<UserResponseDTO> toResponseList(List<User> users) {
        List<UserResponseDTO> responseList = new ArrayList<>();
        for (User user : users) {
            responseList.add(this.toResponse(user));
        }
        return responseList;
    }
}
