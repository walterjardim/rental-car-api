package br.org.rentalcarapi.infra.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank(message = "Login is required") String login,
        @NotBlank(message = "Password is required") String password) {
}
