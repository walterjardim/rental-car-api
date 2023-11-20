package br.org.rentalcarapi.infra.dto;

import java.util.Date;
import java.util.List;

public record UserResponseDTO (
    String firstName,
    String lastName,
    String email,
    Date birthday,
    String login,
    String phone,
    List<CarDTO> cars) {
}
