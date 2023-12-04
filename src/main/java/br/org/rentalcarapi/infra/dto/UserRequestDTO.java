package br.org.rentalcarapi.infra.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.With;

import java.util.Date;
import java.util.List;


public record UserRequestDTO(
        @With
        Long id,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Email is invalid")
        String email,

        @NotNull(message = "Birthday is required")
        @Past(message = "The birthday must be in the past")
        Date birthday,

        @NotBlank(message = "Login is required")
        String login,

        @NotBlank(message = "Password is required")
        String password,

        @NotBlank(message = "Phone is required")
        String phone,

        @Valid
        List<CarDTO> cars) {
}
