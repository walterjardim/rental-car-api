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
        List<Car> cars = getCars(userRequestDTO.getCars());
        
        return new User(
            userRequestDTO.getId(),
            userRequestDTO.getFirstName(),
            userRequestDTO.getLastName(),
            userRequestDTO.getEmail(),
            userRequestDTO.getBirthday(),
            userRequestDTO.getLogin(),
            userRequestDTO.getPassword(),
            userRequestDTO.getPhone(),
            cars);
    }

    private List<Car> getCars(List<CarDTO> carsDTO) {
        List<Car> cars = new ArrayList<>();
        if (carsDTO != null) {
            for (CarDTO carDTO : carsDTO) {
                cars.add(new Car(carDTO.getManufactureYear(), carDTO.getLicensePlate(), carDTO.getModel(), carDTO.getColor()));
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
