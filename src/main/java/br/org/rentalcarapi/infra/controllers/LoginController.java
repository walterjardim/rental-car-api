package br.org.rentalcarapi.infra.controllers;

import br.org.rentalcarapi.application.gateways.UserGateway;
import br.org.rentalcarapi.infra.dto.LoginRequestDTO;
import br.org.rentalcarapi.infra.dto.LoginResponseDTO;
import br.org.rentalcarapi.infra.persistence.entity.UserEntity;
import br.org.rentalcarapi.infra.persistence.repository.UserRepository;
import br.org.rentalcarapi.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signin")
@Validated
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken userToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.login(), loginRequestDTO.password());

        Authentication authentication = null;
        try {
            authentication = this.authenticationManager.authenticate(userToken);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        var token = this.tokenService.generateToken((UserEntity)authentication.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}
