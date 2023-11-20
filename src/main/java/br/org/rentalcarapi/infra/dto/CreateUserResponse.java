package br.org.rentalcarapi.infra.dto;

import java.util.Date;
import java.util.List;

import br.org.rentalcarapi.domain.entity.Car;

public record CreateUserResponse (
    String firstName,
    String lastName,
    String email,
    Date birthday,
    String login,
    String phone,
    List<Car> cars) {
}
