package br.org.rentalcarapi.infra.dto;

import java.util.Date;
import java.util.List;

import br.org.rentalcarapi.domain.entity.Car;

public record CreateUserRequest(
    String firstName,
    String lastName,
    String email,
    Date birthday,
    String login,
    String password,
    String phone,
    List<Car> cars) {
}
