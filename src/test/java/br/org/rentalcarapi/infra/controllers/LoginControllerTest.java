package br.org.rentalcarapi.infra.controllers;

import br.org.rentalcarapi.infra.dto.LoginRequestDTO;
import br.org.rentalcarapi.infra.persistence.entity.UserEntity;
import br.org.rentalcarapi.infra.persistence.repository.UserRepository;
import br.org.rentalcarapi.infra.security.TokenService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TokenService tokenService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLoginWithSuccess() throws Exception {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO("login", "password");

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(this.authenticationManager.authenticate(Mockito.any()))
                .thenReturn(authentication);
        Mockito.when(this.tokenService.generateToken(Mockito.any()))
                .thenReturn("token");

        this.mockMvc.perform(
                post("/api/signin").
                        contentType(MediaType.APPLICATION_JSON).
                        content(this.objectMapper.writeValueAsString(loginRequestDTO))
        ).andExpect(status().isOk());
    }
}
